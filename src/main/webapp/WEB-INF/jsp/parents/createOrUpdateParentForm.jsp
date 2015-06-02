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

    <c:choose>
        <c:when test="${parent['new']}">
            <c:set var="method" value="post"/>
            <spring:url value="/parents/new" htmlEscape="true" var="action"/>
        </c:when>
        <c:otherwise>
            <c:set var="method" value="put"/>
            <spring:url value="/parents/${parent.id}/edit" htmlEscape="true" var="action"/>
        </c:otherwise>
    </c:choose>

    <jsp:include page="../fragments/jQueryLib.jsp"/>

    <script>
        $(function () {
            $("#birthDate").datepicker({dateFormat: 'dd.mm.yy'});
        });
    </script>

</head>
<body>

<div id="wrapper-login">

    <h1>Родитель</h1>

    <form:form modelAttribute="parent" method="${method}" action="${action}">
        <table class="horiz">

            <tr>
                <td><form:label path="lastName">Фамилия:</form:label></td>
                <td><form:input path="lastName"/><form:errors path="lastName" cssStyle="color:red;"
                                                              cssclass="error"/></td>
            </tr>
            <tr>
                <td><form:label path="firstName">Имя:</form:label></td>
                <td><form:input path="firstName"/><form:errors path="firstName" cssStyle="color:red;"
                                                               cssclass="error"/></td>
            </tr>

            <tr>
                <td><form:label path="middleName">Отчество:</form:label></td>
                <td><form:input path="middleName"/><form:errors path="middleName" cssStyle="color:red;"
                                                                cssclass="error"/></td>
            </tr>
            <tr>
                <td><form:label path="dateOfBirth">Дата рождения:</form:label></td>
                <td><form:input path="dateOfBirth" id="birthDate"/><form:errors path="dateOfBirth" cssStyle="color:red;"
                                                                                cssclass="error"/></td>
            </tr>
            <tr>
                <td><form:label path="gender">Пол:</form:label></td>
                <td><form:radiobutton path="gender" value="MALE"/>Male
                    <form:radiobutton path="gender" value="FEMALE"/>Female
                    <form:errors path="gender" cssStyle="color:red;" cssclass="error"/></td>
            </tr>
            <tr>
                <td><form:label path="user">Пользователь:</form:label></td>
                <td>
                    <form:select path="user">
                        <form:option value="0" label="------Select a user------" />
                        <form:options items="${users}" itemValue="id" itemLabel="email"/>
                    </form:select>
                    <form:errors path="user" cssStyle="color:red;" cssclass="error"/>
                </td>
            </tr>

        </table>

        <hr>

        <%--<div id="users-contain" class="ui-widget">--%>
            <%--<h1>Roles:</h1>--%>
            <%--<table id="roles" class="ui-widget ui-widget-content">--%>
                <%--<thead>--%>
                <%--<tr class="ui-widget-header ">--%>
                    <%--<th>Name</th>--%>
                    <%--<th>Description</th>--%>
                <%--</tr>--%>
                <%--</thead>--%>
                <%--<tbody>--%>
                <%--<c:forEach items="${user.roles}" varStatus="vs">--%>
                    <%--<tr>--%>
                        <%--<td>${user.roles[vs.index].name}</td>--%>
                        <%--<td>${user.roles[vs.index].description}</td>--%>
                        <%--<form:hidden path="roles[${vs.index}].roleId"/>--%>
                        <%--<form:hidden path="roles[${vs.index}].name"/>--%>
                        <%--<form:hidden path="roles[${vs.index}].description"/>--%>
                    <%--</tr>--%>
                <%--</c:forEach>--%>
                <%--</tbody>--%>
            <%--</table>--%>
        <%--</div>--%>
        <%--<button id="add-role">Add role</button>--%>

        <%--<hr>--%>

        <%--<div id="users-contain" class="ui-widget">--%>
        <%--<h1>Students:</h1>--%>
        <%--<table id="students" class="ui-widget ui-widget-content">--%>
        <%--<thead>--%>
        <%--<tr class="ui-widget-header">--%>
        <%--<th>Last name</th>--%>
        <%--<th>First name</th>--%>
        <%--<th>Middle name</th>--%>
        <%--</tr>--%>
        <%--</thead>--%>
        <%--<tbody>--%>
        <%--<c:forEach items="${user.students}" varStatus="vs">--%>
        <%--<tr>--%>
        <%--<td>${user.students[vs.index].lastName}</td>--%>
        <%--<td>${user.students[vs.index].firstName}</td>--%>
        <%--<td>${user.students[vs.index].middleName}</td>--%>
        <%--<form:hidden path="students[${vs.index}].studentId"/>--%>
        <%--<form:hidden path="students[${vs.index}].lastName"/>--%>
        <%--<form:hidden path="students[${vs.index}].firstName"/>--%>
        <%--<form:hidden path="students[${vs.index}].middleName"/>--%>
        <%--</tr>--%>
        <%--</c:forEach>--%>
        <%--</tbody>--%>
        <%--</table>--%>
        <%--</div>--%>
        <%--<button class="add-student">Add student</button>--%>

        <br>

        <input type="submit" value="Сохранить"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form:form>

</div>

</body>
</html>
