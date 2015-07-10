<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>

    <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <c:set var="method" value="post"/>
    <spring:url value="/shedules/constructor" htmlEscape="true" var="action"/>

    <jsp:include page="../fragments/jQueryLib.jsp"/>

    <jsp:include page="../fragments/datatablesLib.jsp"/>

    <style>
        table tbody tr.selected {
            background-color: #cd0a0a;
        }

        #addRow, #deleteRow, #addWeek {
            text-decoration: underline;
            color: blue;
            cursor: default
        }
    </style>

    <script>

        var lastRow = 1;
        var lastWeek = 1;

        $(document).ready(function () {
            addNewWeek();
        });

        function onAddWeek() {

            if (lastWeek == null || lastWeek == undefined) {
                return;
            }

            $('#weeks').append(
                    "<br>" +
                    "<div style='border:solid;width:32%' class=week_" + lastWeek + ">" +

                    "<div style='font-weight:bold; text-align:center'>Неделя № " + lastWeek + "</div>" +
                    "<input type='text' name=week_" + lastWeek + " value=" + lastWeek + " hidden>" +

                    "<div>" +
                    <c:forEach var="vardayOfWeek" items="${daysOfWeek}">

                    "<div>" +

                    "<hr>" +

                    "<div>" +
                    "<div style='font-weight:bold; text-align:center'>${vardayOfWeek.value}</div>" +
                    "<input type='text' name=dayOfTheWeek_" + lastWeek + "_${vardayOfWeek.key} value='${vardayOfWeek.key}' hidden>" +
                    "</div>" +

                    "<br>" +

                    "<div>" +
                    "<span id='addRow' onclick=onAddRow('_" + lastWeek + "_${vardayOfWeek.key}')>Добавить</span>" +
                    "<span id='deleteRow' onclick=onDeleteRow('_" + lastWeek + "_${vardayOfWeek.key}')> / Удалить</span>" +
                    "</div>" +

                    "<table id=shedule_" + lastWeek + "_${vardayOfWeek.key} class='display' cellspacing='0' border='1' width='35%'>" +
                    "<thead>" +
                    "<tr>" +
                    "<th>Ch</th>" +
                    "<th>Время урока</th>" +
                    "<th>Предмет</th>" +
                    "<th>Преподаватель</th>" +
                    "</tr>" +
                    "</thead>" +
                    "<tbody>" +
                    "</tbody>" +
                    "</table>" +

                    "</div>" +
                    "<br>" +
                    </c:forEach>

                    "</div>");

            lastWeek++;

        }
        ;

        function onAddRow(index) {

            if (index == null || index == undefined) {
                return;
            }

            var varIndex = index + "_" + lastRow;

            var selectorTable = "#shedule" + index + " > tbody";
            $(selectorTable).append(
                    "<tr>" +

                    "<td>" +
                    "<input type='checkbox' onclick='onSelectRow(this)'>" +
                    "</td>" +

                    "<td>" +
                    "<select name = " + "period" + varIndex + ">" +
                    "<option value = '0'>" + "-------Время урока------" + "</option>" +
                    <c:forEach var="varperiod" items="${periods}">
                    "<option value = '${varperiod.id}'>" + '${varperiod}' + "</option>" +
                    </c:forEach>
                    "</select>" +
                    "</td>" +

                    "<td>" +
                    "<select name = " + "subject" + varIndex + ">" +
                    "<option value = '0'>" + "-------Предмет------" + "</option>" +
                    <c:forEach var="varsubject" items="${subjects}">
                    "<option value = '${varsubject.id}'>" + '${varsubject.name}' + "</option>" +
                    </c:forEach>
                    "</select>" +
                    "</td>" +

                    "<td>" +
                    "<select name = " + "teacher" + varIndex + ">" +
                    "<option value = '0'>" + "-------Преподаватель------" + "</option>" +
                    <c:forEach var="varteacher" items="${teachers}">
                    "<option value='${varteacher.id}'>" + '${varteacher.lastName} ${varteacher.firstName} ${varteacher.middleName}' + "</option>" +
                    </c:forEach>
                    "</select>" +
                    "</td>" +

                    "</tr>");

            lastRow++;

        }
        ;

        function onDeleteRow(index) {
            if (index == null || index == undefined) {
                return;
            }

            var table = $("#shedule" + index);

            if ((table.find("tr").length - table.find("tr.selected").length) > 1) {
                table.find("tr.selected").remove();
            }
        }
        ;

        function onSelectRow(ob) {

            var row = $(ob).parents("tr");

            if (row.hasClass('selected')) {
                row.removeClass('selected');
            }
            else {
                row.addClass('selected');
            }

        }
        ;

        function addNewWeek() {
            onAddWeek();
            <c:forEach var="vardayOfWeek" items="${daysOfWeek}">
            onAddRow('_' + (lastWeek - 1) + '_${vardayOfWeek.key}')
            </c:forEach>
        }
        ;

        function onSubmit(f) {
            //TODO: correct data
            f.submit();
        }
        ;

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
            $('[id ^= "datePattern"]').datepicker({
                dateFormat: 'dd.mm.yy',
                firstDay: 1
            });
        });
    </script>

</head>
<body>

<div id="wrapper-login">

    <h1>Расписание</h1>

    <form:form method="${method}" action="${action}" enctype='application/json' onsubmit="onSubmit(this)">

        <div>
            <label for="startDate">Дата начала</label>
            <input type="text" id="startDate" name="startDate"/>
            <label for="endDate">Дата окончания</label>
            <input type="text" id="endDate" name="endDate"/>
        </div>

        <br>

        <div>
            <label>Класс</label>
            <select name="classSelect">
                <option value="0"><c:out value="------Select a class------"/></option>
                <c:forEach var="varclazz" items="${clazzes}">
                    <option value="${varclazz.id}"><c:out value="${varclazz.number} - ${varclazz.letter}"/></option>
                </c:forEach>
            </select>
        </div>

        <br>

        <div>
            <span id="addWeek" onclick="addNewWeek()">Добавить неделю</span>
        </div>

        <div id="weeks">

        </div>

        <br>

        <input id="submit" type="submit" value="Сохранить"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form:form>

</div>

</body>
</html>
