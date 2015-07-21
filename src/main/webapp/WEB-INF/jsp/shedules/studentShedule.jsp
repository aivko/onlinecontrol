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
                        var dataShedule = data.shedule;
                        for (var keyClazz in dataShedule) {

                            text = text +
                                    "<div>" + keyClazz
                            "</div>";

                            for (var keyStudent in dataShedule[keyClazz]) {

                                text = text +
                                        "<div>" + keyStudent
                                "</div>";

                                for (var keyDate in dataShedule[keyClazz][keyStudent]) {

                                    text = text +
                                            "<div>" + keyDate
                                    "</div>";

                                    text = text +
                                            "<table id='studentsResult' class='display' cellspacing='0' border='1' width='50%'>" +
                                            "<thead>" +
                                            "<tr>" +
                                            "<th>Период</th>" +
                                            "<th>Предмет</th>" +
                                            "<th>Задание</th>" +
                                            "<th>Оценка</th>" +
                                            "<th>Преподаватель</th>" +
                                            "</tr>" +
                                            "</thead>" +
                                            "<tbody>";
                                    for (var keyPeriod in dataShedule[keyClazz][keyStudent][keyDate]) {

                                        for (var keySubject in dataShedule[keyClazz][keyStudent][keyDate][keyPeriod]) {

                                            for (var keyTeacher in dataShedule[keyClazz][keyStudent][keyDate][keyPeriod][keySubject]) {

                                                var sheduleData = dataShedule[keyClazz][keyStudent][keyDate][keyPeriod][keySubject][keyTeacher];
                                                var extraHome = "";
                                                var markJob = "";

                                                if (sheduleData.length > 0) {

                                                    var countMark = sheduleData.length;
                                                    for (var journalView in sheduleData) {

                                                        if (sheduleData[journalView].grade == null) {
                                                            countMark--;
                                                            continue;
                                                        }

                                                        if (sheduleData[journalView].grade.mark == null & sheduleData[journalView].grade.task != null) {
                                                            extraHome = extraHome + sheduleData[journalView].grade.task + (countMark == 0 ? "" : "<br/>");
                                                        } else if (sheduleData[journalView].grade.mark != null & sheduleData[journalView].grade.task == null) {
                                                            markJob = markJob + sheduleData[journalView].grade.mark + (countMark == 0 ? "" : "<br/>");
                                                        } else {
                                                            markJob = markJob +
                                                                    sheduleData[journalView].grade.task + ": " + sheduleData[journalView].grade.mark +
                                                                    (countMark == 0 ? "" : "<br/>");
                                                        }
                                                        countMark--;
                                                    }
                                                    ;

                                                }
                                                text = text +
                                                        "<tr>" +
                                                        "<td>" + sheduleData[0].period.startTime + " - " + sheduleData[0].period.endTime + "</td>" +
                                                        "<td>" + sheduleData[0].subject.name + "</td>" +
                                                        "<td>" + (sheduleData[0].job == null ? "" : sheduleData[0].job) + (extraHome == "" ? "" : "<br/>" + extraHome) +
                                                        "</td>" +
                                                        "<td>" +
                                                        markJob +
                                                        "</td>" +
                                                        "<td>" + sheduleData[0].teacher.lastName + " " + sheduleData[0].teacher.firstName + " " + sheduleData[0].teacher.middleName + "</td>" +
                                                        "</tr>";

                                            }
                                            ;
                                        }
                                        ;
                                    }
                                    ;
                                    text = text +
                                            "</tbody>" +
                                            "</table>" +
                                            "<br>";
                                }
                                ;
                            }
                            ;
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
