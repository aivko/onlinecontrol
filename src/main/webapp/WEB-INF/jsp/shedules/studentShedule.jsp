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

    <spring:url value="/shedules/studentsReport" htmlEscape="true" var="studentsReport"/>

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
                        var text = "";
                        for (var key in data.shedules) {
                            text = text + "<tr>" +
                                    "<td>" + data.shedules[key].date + "</td>" +
                                    "<td>" + data.shedules[key].period.startTime + " - " + data.shedules[key].period.endTime + "</td>" +
                                    "<td>" + data.shedules[key].subject.name + "</td>" +
                                    "<td>" + data.shedules[key].clazz.number + " - " + data.shedules[key].clazz.letter + "</td>" +
                                    "<td>" + data.shedules[key].teacher.lastName + " " + data.shedules[key].teacher.firstName + " " + data.shedules[key].teacher.middleName + "</td>" +
                                    "<td>" + data.shedules[key].job + "</td>" +
                                    "</tr>"
                        };

                        $("#studentsResult > tbody").append(text);

                    }
                    else {
                        $("#result").text("");
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
            <table id="studentsResult" class="display" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th>Дата</th>
                    <th>Период</th>
                    <th>Предмет</th>
                    <th>Класс</th>
                    <th>Преподаватель</th>
                    <th>Задание</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>

        </div>
    </div>

</div>

</body>
</html>
