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

    <spring:url value="/resources/turnjs4/extras/jquery.mousewheel.min.js" var="mousewheelJs"/>
    <script src="${mousewheelJs}"></script>
    <spring:url value="/resources/turnjs4/extras/modernizr.2.5.3.min.js" var="modernizrJs"/>
    <script src="${modernizrJs}"></script>
    <spring:url value="/resources/turnjs4/lib/hash.js" var="hashJs"/>
    <script src="${hashJs}"></script>

    <script type="text/javascript">

        function loadApp() {
            $('.flipbook').turn({
                width: 1050,
                height: 600,
                elevation: 50,
                gradients: true,
                autoCenter: true
            });
        }

        $(function () {
            $("#startDate").datepicker({
                defaultDate: "-1w",
                firstDay: 1,
                changeMonth: true,
                dateFormat: 'dd.mm.yy',
                numberOfMonths: 3,
                onClose: function (selectedDate) {
                    $("#endDate").datepicker("option", "minDate", selectedDate);
                }
            });
            $("#endDate").datepicker({
                defaultDate: "-1w",
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
                    var resultText = "";
                    if (data.result == "true") {

                        var weekNumber = "";
                        var countPages = 0;
                        for (var keyClazz in data.shedule) {
                            resultText = resultText +
                                    "<div>" + keyClazz +
                                    "</div>";
                            countPages++;

                            for (var keyStudent in data.shedule[keyClazz]) {

                                resultText = resultText +
                                        "<div>" + keyStudent +
                                        "</div>";
                                countPages++;

                                var countDate = 1;
                                var beforeWeekNumber = null;
                                for (var keyDate in data.shedule[keyClazz][keyStudent]) {

                                    countPages++;
                                    var detailsShedule = "";
                                    for (var keyShedule in data.shedule[keyClazz][keyStudent][keyDate]) {

                                        var currentShedule = data.shedule[keyClazz][keyStudent][keyDate][keyShedule];

                                        weekNumber = parseInt(currentShedule.dayOfWeek, 10);

                                        var extraHome = "";
                                        var markJob = "";
                                        var countMark = currentShedule.grades.length;
                                        for (var keyGrade in currentShedule.grades) {
                                            var grade = currentShedule.grades[keyGrade];
                                            if (grade == null) {
                                                countMark--;
                                                continue;
                                            }

                                            if (grade.mark == null & grade.task != null) {
                                                extraHome = extraHome + grade.task + (countMark == 0 ? "" : "<br/>");
                                            } else if (grade.mark != null & grade.task == null) {
                                                markJob = markJob + grade.mark + (countMark == 0 ? "" : "<br/>");
                                            } else {
                                                markJob = markJob + grade.task + ": " + grade.mark + (countMark == 0 ? "" : "<br/>");
                                            }
                                            countMark--;
                                        }


                                        detailsShedule = detailsShedule +
                                                "<tr>" +
                                                "<td>" + currentShedule.period.startTime + " - " + currentShedule.period.endTime + "</td>" +
                                                "<td>" + currentShedule.subject.name + "</td>" +
                                                "<td>" + (currentShedule.job == null ? "" : currentShedule.job) + (extraHome == "" ? "" : "<br/>" + extraHome) + "</td>" +
                                                "<td>" + markJob + "</td>" +
                                                "<td>" + currentShedule.teacher.lastName + " " + currentShedule.teacher.firstName + " " + currentShedule.teacher.middleName + "</td>" +
                                                "</tr>";
                                    }

                                    var arrayDate = keyDate.split("/");

                                    if ((beforeWeekNumber == null & weekNumber <= 3)){
                                        if(countPages%2 != 0){
                                            resultText = resultText + "<div></div>";
                                        }
                                        resultText = resultText + "<div class='leftColumn'>";
                                    }else if((beforeWeekNumber == null & weekNumber >= 4)){
//                                        alert("beforeWeekNumber: " + beforeWeekNumber + " | weekNumber: " + weekNumber + " | countPages: " + countPages)
                                        if(countPages%2 == 0){
                                            resultText = resultText + "<div></div>";
                                        }
                                        resultText = resultText + "<div class='rightColumn'>";
                                    }else if(weekNumber == 1){
                                        if(countPages%2 != 0){
                                            resultText = resultText + "<div></div>";
                                        }
                                        resultText = resultText + "</div><div class='leftColumn'>";
                                    }else if(weekNumber == 4){
                                        if(countPages%2 == 0){
                                            resultText = resultText + "<div></div>";
                                        }
                                        resultText = resultText + "</div><div class='rightColumn'>";
                                    }

                                    resultText = resultText +
                                            "<div>" + arrayDate[0] + "/" + arrayDate[1].charAt(0).toUpperCase() + arrayDate[1].substr(1).toLowerCase() +
                                            "</div>";

                                    resultText = resultText +
                                            "<table cellspacing='0' border='1' width='50%'>" +
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

                                    resultText = resultText + detailsShedule;

                                    resultText = resultText +
                                            "</tbody>" +
                                            "</table>" +
                                            "<br>";

                                    beforeWeekNumber = weekNumber;
                                    countDate++;
                                }
                                resultText = resultText + "</div>";
                            }

                        }

                        var $result = $('.flipbook');
                        $result.text("");
                        $result.append(resultText); //delete old data and add new
//                        yepnope({
//                            test: Modernizr.csstransforms,
//                            yep: ['/onlinecontrol/resources/turnjs4/lib/turn.min.js'],
//                            nope: ['/onlinecontrol/resources/turnjs4/lib/turn.html4.min.js'],
//                            both: ['/onlinecontrol/resources/turnjs4/samples/basic/css/basic.css'],
//                            complete: loadApp
//                        });

                    }
                    else {
                        $('.flipbook').text("Нет данных");
                    }
                    ;

                },
                error: function (data, status, er) {
                    $('.flipbook').text("Не удалось сформировать отчет");
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

        <div class="flipbook-viewport">
            <div class="container">
                <div class="flipbook">

                </div>
            </div>
        </div>
    </div>

</div>

</body>
</html>
