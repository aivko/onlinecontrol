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

        function expandTree(key, data, level) {

            for (var varKey in data) {

                if (data[varKey] instanceof Array) {

                    if (data[varKey].length <= 0) {
                        //not understand data
                        continue;
                    }
                    var extraHome = "";
                    var markJob = "";
                    var countMark = data[varKey].length;
                    for (var journalView in data[varKey]) {

                        if (data[varKey][journalView].grade == null) {
                            countMark--;
                            continue;
                        }

                        if (data[varKey][journalView].grade.mark == null & data[varKey][journalView].grade.task != null) {
                            extraHome = extraHome + data[varKey][journalView].grade.task + (countMark == 0 ? "" : "<br/>");
                        } else if (data[varKey][journalView].grade.mark != null & data[varKey][journalView].grade.task == null) {
                            markJob = markJob + data[varKey][journalView].grade.mark + (countMark == 0 ? "" : "<br/>");
                        } else {
                            markJob = markJob +
                                    data[varKey][journalView].grade.task + ": " + data[varKey][journalView].grade.mark +
                                    (countMark == 0 ? "" : "<br/>");
                        }
                        countMark--;
                    }
                    ;

                    tempClazz = "" +
                            "<div>" + data[varKey][0].clazz.number + " - " + data[varKey][0].clazz.letter +
                            "</div>";
                    tempStudent = "" +
                            "<div>" + data[varKey][0].student.lastName + " " + data[varKey][0].student.firstName + " " + data[varKey][0].student.middleName +
                            "</div>";

                    var arrayDate = data[varKey][0].date.split("/");
                    weekNumber = data[varKey][0].dayOfWeek;
                    tempDate = "" +
                            "<div>" + arrayDate[0] + "/" + arrayDate[1].charAt(0).toUpperCase() + arrayDate[1].substr(1).toLowerCase() +
                            "</div>";

                    textBefore = "" +
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
                    text = "" +
                            "<tr>" +
                            "<td>" + data[varKey][0].period.startTime + " - " + data[varKey][0].period.endTime + "</td>" +
                            "<td>" + data[varKey][0].subject.name + "</td>" +
                            "<td>" + (data[varKey][0].job == null ? "" : data[varKey][0].job) + (extraHome == "" ? "" : "<br/>" + extraHome) +
                            "</td>" +
                            "<td>" +
                            markJob +
                            "</td>" +
                            "<td>" + data[varKey][0].teacher.lastName + " " + data[varKey][0].teacher.firstName + " " + data[varKey][0].teacher.middleName + "</td>" +
                            "</tr>";
                    textAfter = "" +
                            "</tbody>" +
                            "</table>" +
                            "<br>";

                } else {

                    level++;

                    expandTree(varKey, data[varKey], level);

                    if (level == 2) {
                        textStudent = textStudent + tempStudent + textDate;
                        textDate = "";
                    } else if (level == 3) {
                        var weekNumberInt = parseInt(weekNumber, 10);
                        var textColumnBegin = "";
                        var textColumnEnd = "";
//                        if (weekNumberInt == 1) {
//                            textColumnBegin = "<div class='leftColumn'>";
//                        } else if (weekNumberInt == 3) {
//                            textColumnEnd = "</div>";
//                        } else if (weekNumberInt == 4) {
//                            textColumnBegin = "<div class='rightColumn'>";
//                        } else if (weekNumberInt == 5) {
//                            textColumnEnd = "</div>";
//                        }

                        textDate = textDate + textColumnBegin + tempDate + textBefore + textTemp + textAfter + textColumnEnd;
                        textTemp = "";
                    } else if (level == 5) {
                        textTemp = textTemp + text;
                    }
                    level--;
                }
                ;
            }

        }

        var tempClazz = "";
        var textStudent = "";
        var tempStudent = "";
        var textDate = "";
        var tempDate = "";
        var weekNumber = "";
        var textBefore = "";
        var text = "";
        var textTemp = "";
        var textAfter = "";
        var resultText = "";

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
                        resultText = "<br>";
                        var level = 1;
                        for (var key in data.shedule) {
                            expandTree(key, data.shedule[key], level);
                            resultText = resultText + tempClazz + textStudent;
                            textStudent = "";
                        }
                        ;

                        var $result = $('.flipbook');
                        $result.text("");
                        $result.append(resultText); //delete old data and add new
                        yepnope({
                            test: Modernizr.csstransforms,
                            yep: ['/onlinecontrol/resources/turnjs4/lib/turn.min.js'],
                            nope: ['/onlinecontrol/resources/turnjs4/lib/turn.html4.min.js'],
                            both: ['/onlinecontrol/resources/turnjs4/samples/basic/css/basic.css'],
                            complete: loadApp
                        });

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
        <br>

        <div id="result">

        </div>
    </div>

    <div class="flipbook-viewport">
        <div class="container">
            <div class="flipbook">
                <%--<div>Первая</div>--%>
                <%--<div>Вторая</div>--%>
                <%--<div>Третья</div>--%>
                <%--<div>Четвертая</div>--%>
            </div>
        </div>
    </div>


</div>

<script type="text/javascript">

    function loadApp() {
        $('.flipbook').turn({
            width: 922,
            height: 600,
            elevation: 50,
            gradients: true,
            autoCenter: true
        });
    }

    // Load the HTML4 version if there's not CSS transform

//    yepnope({
//        test: Modernizr.csstransforms,
//        yep: ['/onlinecontrol/resources/turnjs4/lib/turn.min.js'],
//        nope: ['/onlinecontrol/resources/turnjs4/lib/turn.html4.min.js'],
//        both: ['/onlinecontrol/resources/turnjs4/samples/basic/css/basic.css'],
//        complete: loadApp
//    });

</script>

</body>
</html>
