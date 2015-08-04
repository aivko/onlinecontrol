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
                firstDay: 1,
                changeMonth: true,
                dateFormat: 'dd.mm.yy',
                numberOfMonths: 3,
                onClose: function (selectedDate) {
                    $("#endDate").datepicker("option", "minDate", selectedDate);
                }
            });
            $("#endDate").datepicker({
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

            var valid = true;
            valid = valid && validStartDate();
            valid = valid && validEndDate();
            valid = valid && validStudent();

            if (!valid) return false;

            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");

            var startDate = $("#startDate").val();
            var endDate = $("#endDate").val();
            var student = $("#student").val();

            var json = {"startDate": startDate, "endDate": endDate, "student": student};

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

                        for (var keyDate in data.shedule) {

                            var detailsShedule = "";
                            for (var keyShedule in data.shedule[keyDate]) {

                                var currentShedule = data.shedule[keyDate][keyShedule];

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

        function validStudent() {
            var student = $("#student").val();
            if (student == "" || student == "0" || student == undefined || student == null) {
                $("#errorStudent").text("Укажите студента");
                return false;
            }
            $("#errorStudent").text("");
            return true;
        }
        ;

    </script>

</head>
<body>

<div>

    <h1>Просмотр расписания</h1>

    <div>
        <label for="startDate">Дата начала</label>
        <input type="text" id="startDate" name="startDate" onchange="validStartDate()"/>
        <label for="endDate">Дата окончания</label>
        <input type="text" id="endDate" name="endDate" onchange="validEndDate()"/>

        <div style="color:red;" id="errorStartDate"></div>
        <div style="color:red;" id="errorEndDate"></div>
    </div>

    <br/>

    <div>
        <select id="student" name="student" onchange="validStudent()">
            <option value="">------Выберите студента------</option>
            <c:forEach var="varstudent" items="${students}">
                <option value="${varstudent.id}"><c:out
                        value="${varstudent.lastName} ${varstudent.firstName} ${varstudent.middleName}"/></option>
            </c:forEach>
        </select>
        <span style="color:red;" id="errorStudent"></span>
    </div>
    <br/>
    <button onclick="return studentReport()">Сформировать</button>

    <div class="flipbook">

    </div>

</div>

</body>
</html>
