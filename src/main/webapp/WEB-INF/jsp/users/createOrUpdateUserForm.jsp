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
        <c:when test="${user['new']}">
            <c:set var="method" value="post"/>
            <spring:url value="/users/new" htmlEscape="true" var="action"/>
        </c:when>
        <c:otherwise>
            <c:set var="method" value="put"/>
            <spring:url value="/users/${user.userId}/edit" htmlEscape="true" var="action"/>
        </c:otherwise>
    </c:choose>

    <jsp:include page="../fragments/jQueryLib.jsp"/>

</head>
<body>

<div id="wrapper-login">

    <h1>Пользователь</h1>

    <form:form modelAttribute="user" method="${method}" action="${action}">
        <table class="horiz">
            <tr>
                <td><form:label path="email">Email:</form:label></td>
                <td><form:input path="email"/><form:errors path="email" cssStyle="color:red;" cssclass="error"/></td>
            </tr>

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

            <c:choose>
                <c:when test="${user['new']}">
                    <tr>
                        <td><form:label path="password">Пароль:</form:label></td>
                        <td><form:password path="password"/><form:errors path="password" cssStyle="color:red;"
                                                                         cssclass="error"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="passwordConfirm">Повторите пароль:</form:label></td>
                        <td><form:password path="passwordConfirm"/><form:errors path="passwordConfirm"
                                                                                cssStyle="color:red;"
                                                                                cssclass="error"/></td>
                    </tr>
                </c:when>
            </c:choose>

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

        <hr>
        <br>

        <input type="submit" value="Сохранить"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form:form>

</div>

</body>
</html>
