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

    <spring:url value="/shedules/criteriaShedule" htmlEscape="true" var="action"/>

    <jsp:include page="../fragments/jQueryLib.jsp"/>

    <script type="text/javascript">

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

        function onSubmit(f) {

            var valid = true;
            valid = valid && validStartDate();
            valid = valid && validEndDate();
            valid = valid && validStudent();

            if (!valid) return false;
            f.submit();
        }

        function validStartDate() {
            var dateReg = /^\d{2}[.]\d{2}[.]\d{4}$/;
            var startDate = $("#startDate").val();
            if(!dateReg.test(startDate)){
                $("#errorStartDate").text("Введите дату начала в формате DD.MM.YYYY");
                return false;
            }
            $("#errorStartDate").text("");
            return true;
        };

        function validEndDate() {
            var dateReg = /^\d{2}[.]\d{2}[.]\d{4}$/;
            var endDate = $("#endDate").val();
            if(!dateReg.test(endDate)){
                $("#errorEndDate").text("Введите дату окончания в формате DD.MM.YYYY");
                return false;
            }
            $("#errorEndDate").text("");
            return true;
        };

        function validStudent() {
            var student = $("#student").val();
            if(student == "" || student == "0" || student == undefined || student == null){
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

    <h1>Отбор:</h1>

    <form:form action="${action}" method="post" onsubmit="return onSubmit(this)">
        <form:errors path="" cssStyle="color:red;" cssclass="error"/>
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
                    <option value="${varstudent.id}"><c:out value="${varstudent.lastName} ${varstudent.firstName} ${varstudent.middleName}"/></option>
                </c:forEach>
            </select>
            <span style="color:red;" id="errorStudent"></span>
        </div>
        <br/>
        <input id="submit" type="submit" value="Сформировать"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form:form>

</div>

</body>
</html>
