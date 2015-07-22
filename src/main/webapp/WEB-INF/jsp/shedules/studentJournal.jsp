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

    <!-- Include jQuery booklet plugin -->
    <spring:url value="/resources/booklet/jquery.booklet.latest.css" var="bookletCss1"/>
    <link href="${bookletCss1}" rel="stylesheet" media="screen"/>

    <spring:url value="/resources/booklet/jquery.booklet.1.1.0.css" var="bookletCss"/>
    <link href="${bookletCss}" rel="stylesheet" media="screen"/>

    <spring:url value="/resources/booklet/jquery.booklet.1.1.0.js" var="bookletJs"/>
    <script src="${bookletJs}"></script>

    <spring:url value="/resources/booklet/jquery.easing.1.3.js" var="bookletJs1"/>
    <script src="${bookletJs1}"></script>

    <spring:url value="/resources/booklet/jquery.booklet.latest.js" var="bookletJs2"/>
    <script src="${bookletJs2}"></script>
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

    </div>

</div>

</body>
</html>
