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
                    var resultText = "";
                    if (data.result == "true") {

                        for (var keyDate in data.shedule) {

                            var arrayDate = keyDate.split("/");
                            resultText = resultText +
                                    "<div>" + arrayDate[0] + "/" + arrayDate[1].charAt(0).toUpperCase() + arrayDate[1].substr(1).toLowerCase() +
                                    "</div>";


                            for (var keySubject in data.shedule[keyDate]) {

                                for (var keyTeacher in data.shedule[keyDate][keySubject]) {

                                    for (var keyPeriod in data.shedule[keyDate][keySubject][keyTeacher]) {

                                        var detailsShedule = "";
                                        var homeWork = "";
                                        for (var keyShedule in data.shedule[keyDate][keySubject][keyTeacher][keyPeriod]) {

                                            var currentShedule = data.shedule[keyDate][keySubject][keyTeacher][keyPeriod][keyShedule];

                                            var extraHome = "";
                                            var markJob = "";
                                            for (var keyGrade in currentShedule.grades) {
                                                var grade = currentShedule.grades[keyGrade];
                                                if (grade == null) {
                                                    continue;
                                                }

                                                var tempEditGrade = "<a href=/onlinecontrol/shedules/" + currentShedule.sheduleId + "/students/" + currentShedule.student.id + "/grades/" + grade.id + "/edit>";

                                                if (grade.mark == null & grade.task != null) {
                                                    extraHome = extraHome + tempEditGrade + grade.task + "<br/></a>";
                                                } else if (grade.mark != null & grade.task == null) {
                                                    markJob = markJob + tempEditGrade + "Оценка: " + grade.mark + "<br/></a>";
                                                } else {
                                                    markJob = markJob + tempEditGrade + grade.task + ": " + grade.mark + "<br/></a>";
                                                }
                                            }

                                            homeWork = currentShedule.job;
                                            if (homeWork == "" || homeWork == null) {
                                                homeWork = "<a href=/onlinecontrol/shedules/" + currentShedule.sheduleId + "/edit>\<ввести домашнее задание\></a>";
                                            } else {
                                                homeWork = "<a href=/onlinecontrol/shedules/" + currentShedule.sheduleId + "/edit>" + homeWork + "</a><br/>";
                                            }

                                            extraHome = extraHome +
                                                    "<a href=/onlinecontrol/shedules/" + currentShedule.sheduleId + "/students/" + currentShedule.student.id + "/grades/new>\<добавить задание\></a>";

                                            markJob = markJob +
                                                    "<a href=/onlinecontrol/shedules/" + currentShedule.sheduleId + "/students/" + currentShedule.student.id + "/grades/new>\<добавить оценку\></a>";

                                            detailsShedule = detailsShedule +
                                                    "<tr>" +
                                                    "<td>" + currentShedule.student.lastName + " " + currentShedule.student.firstName + " " + currentShedule.student.middleName + "</td>" +
                                                    "<td>" + extraHome + "</td>" +
                                                    "<td>" + markJob + "</td>" +
                                                    "</tr>";


                                        }

                                        resultText = resultText +
                                                "<div>"
                                                + " | " + currentShedule.subject.name + " | "
                                                + currentShedule.teacher.lastName + " " + currentShedule.teacher.firstName + " " + currentShedule.teacher.middleName + " | "
                                                + "<br/>" + currentShedule.period.startTime + " - " + currentShedule.period.endTime +
                                                "<br/>" + " Домашнее задание: " + homeWork
                                                "</div>";

                                        resultText = resultText +
                                                "<table cellspacing='0' border='1' width='50%'>" +
                                                "<thead>" +
                                                "<tr>" +
                                                "<th>Студент</th>" +
                                                "<th>Дополнительное задание</th>" +
                                                "<th>Оценка</th>" +
                                                "</tr>" +
                                                "</thead>" +
                                                "<tbody>";

                                        resultText = resultText + detailsShedule;

                                        resultText = resultText +
                                                "</tbody>" +
                                                "</table>" +
                                                "<br>";


                                    }

                                }

                            }

                        }


                        var $result = $('.flipbook');
                        $result.text("");
                        $result.append(resultText); //delete old data and add new

                    }
                    else {
                        $('.flipbook').text("Нет данных");
                    }
                    ;

                },
                error: function (data, status, er) {
                    alert("Не удалось сформировать отчет");
                }
            });
        }

        function validStartDate() {
            var dateReg = /^\d{2}[.]\d{2}[.]\d{4}$/;
            var startDate = $("#startDate").val();
            if (!dateReg.test(startDate)) {
                $("#errorStartDate").text("Введите дату начала в формате DD.MM.YYYY");
                return false;
            }
            $("#errorStartDate").text("");
            return true;
        }
        ;

        function validEndDate() {
            var dateReg = /^\d{2}[.]\d{2}[.]\d{4}$/;
            var endDate = $("#endDate").val();
            if (!dateReg.test(endDate)) {
                $("#errorEndDate").text("Введите дату окончания в формате DD.MM.YYYY");
                return false;
            }
            $("#errorEndDate").text("");
            return true;
        }
        ;

    </script>
</head>
<body>

<div>

    <h1>Просмотр журнала</h1>

    <div id="content">
        <div>
            <label for="startDate">Дата начала</label>
            <input type="text" id="startDate" name="startDate" onchange="validStartDate()"/>
            <label for="endDate">Дата окончания</label>
            <input type="text" id="endDate" name="endDate" onchange="validEndDate()"/>

            <div style="color:red;" id="errorStartDate"></div>
            <div style="color:red;" id="errorEndDate"></div>
        </div>
        <button onclick="studentReport()">Сформировать</button>
        <br/>

        <div class="flipbook">

        </div>
    </div>

</div>

</body>
</html>
