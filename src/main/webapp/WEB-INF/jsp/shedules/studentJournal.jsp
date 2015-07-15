<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>

    <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <spring:url value="/shedules/studentJournal" htmlEscape="true" var="studentJournal"/>

    <jsp:include page="../fragments/jQueryLib.jsp"/>

    <!-- Include jQuery Cookie plugin -->
    <script src='//cdn.jsdelivr.net/jquery.cookie/1.4.0/jquery.cookie.js' type="text/javascript"></script>

    <!-- Include jQuery Cynteka Pivot plugin -->
    <spring:url value="/resources/pivot-0.9.1/css/jquery.cy-pivot.css" var="pivotCss"/>
    <link href="${pivotCss}" rel="stylesheet" media="screen"/>

    <spring:url value="/resources/pivot-0.9.1/js/jquery.cy-pivot.js" var="pivotJs"/>
    <script src="${pivotJs}"></script>

    <script type="text/javascript">

        $(function () {
            $("#startDate").datepicker({
                defaultDate: "+1w",
                firstDay: 1,
                changeMonth: true,
                dateFormat: 'dd.mm.yy',
                numberOfMonths: 3,
                onClose: function (selectedDate) {
                    $("#endDate").datepicker("option", "minDate", selectedDate);
                }
            });
            $("#endDate").datepicker({
                defaultDate: "+1w",
                firstDay: 1,
                changeMonth: true,
                dateFormat: 'dd.mm.yy',
                numberOfMonths: 3,
                onClose: function (selectedDate) {
                    $("#startDate").datepicker("option", "maxDate", selectedDate);
                }
            });
        });

        function studentReport() {

            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");

            var startDate = $("#startDate").val();
            var endDate = $("#endDate").val();

            var json = {"startDate": startDate, "endDate": endDate};

            $.ajax({
                url: "${studentJournal}",
                type: 'POST',
                dataType: 'json',
                data: JSON.stringify(json),
                contentType: 'application/json',
                mimeType: 'application/json',
                beforeSend: function (xhr) {
                    xhr.setRequestHeader(header, token);
                },
                success: function (data) {
                    if (data.result == "true") {
                            $.each(data.shedules, function(idx, value){
                                value.total = 1;
                            });
                            var dimensions = {
                                date : {
                                    label :'Дата',
                                },
                                period : {
                                    label :'Период',
                                },
                                subject : {
                                    label :'Предмет',
                                },
                                clazz : {
                                    label :'Класс',
                                },
                                teacher : {
                                    label :'Учитель',
                                },
                                job : {
                                    label :'Задание',
                                },
                            };
                            $("#pivot").cypivot({
                                data : data.shedules,
                                dimensions : dimensions,
                                verticalDimensions : ["period", "subject", "clazz", "teacher"],
                                horizontalDimensions : ["date", "job"],
                                resizable : true,
                                resizableWidth : true,
                                resizableHeight : false,
                            });
                    }
                    else {
                        $("#result").text("Нет данных");
                    }
                    ;
                },
                error: function (data, status, er) {
                    alert("Не удалось сформировать отчет");
                }
            });
        }

    </script>

</head>
<body>

<div>

    <h1>Просмотр расписания</h1>

    <div id="content">
        <div>
            <label for="startDate">Дата начала</label>
            <input type="text" id="startDate" name="startDate"/>
            <label for="endDate">Дата окончания</label>
            <input type="text" id="endDate" name="endDate"/>
        </div>
        <button onclick="studentReport()">Сформировать</button>
        <br/>

        <div id="pivot"></div>

    </div>

</div>

</body>
</html>
