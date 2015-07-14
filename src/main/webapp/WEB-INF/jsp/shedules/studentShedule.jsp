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

    <spring:url value="/shedules/studentShedule" htmlEscape="true" var="studentShedule"/>

    <spring:url value="/shedules/" var="varShedule"/>

    <jsp:include page="../fragments/jQueryLib.jsp"/>

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
                url: "${studentsReport}",
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

                        $('#result').text(""); //delete old data
                        var text = "<br>";
                        for (var keyClazz in data) {

                            if (keyClazz == "result") continue;

                            text = text +
                                    "<div>" + keyClazz
                                    "</div>";

                            for (var keyDate in data[keyClazz]) {

                                text = text +
                                        "<div>" + keyDate
                                "</div>";

                                text = text +
                                        "<table id='studentsResult' class='display' cellspacing='0' border='1' width='35%'>" +
                                        "<thead>" +
                                        "<tr>" +
                                        "<th>Период</th>" +
                                        "<th>Предмет</th>" +
                                        "<th>Задание</th>" +
                                        "<th>Преподаватель</th>" +
                                        "</tr>" +
                                        "</thead>" +
                                        "<tbody>";
                                for (var keyShedule in data[keyClazz][keyDate]) {

                                    var currentShedule = data[keyClazz][keyDate][keyShedule];

                                    var editJob = "<a href='/onlinecontrol/shedules/" + currentShedule.id + "'>"  + (currentShedule.job == null ? "<--ввести задание-->" : currentShedule.job) + "</a>";

                                    text = text +
                                            "<tr>" +
                                            "<td>" + currentShedule.period.startTime + " - " + currentShedule.period.endTime + "</td>" +
                                            "<td>" + currentShedule.subject.name + "</td>" +
                                            "<td>" + editJob + "</td>" +
                                            "<td>" + currentShedule.teacher.lastName + " " + currentShedule.teacher.firstName + " " + currentShedule.teacher.middleName + "</td>" +
                                            "</tr>"

                                }

                                text = text +
                                        "</tbody>" +
                                        "</table>" +
                                        "<br>";


                            }

                        }
                        ;

                        $('#result').append(text);

                    }
                    else {
                        $('#result').text("Нет данных");
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
        <br>

        <div id="result">

        </div>
    </div>

</div>

</body>
</html>
