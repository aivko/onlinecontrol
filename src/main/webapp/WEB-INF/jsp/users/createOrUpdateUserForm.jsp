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
    <sec:authorize access="isAuthenticated()">
        <sec:authentication property="principal.userId" var="userId"/>
        <sec:authentication property="principal.login" var="login"/>
    </sec:authorize>
    <c:choose>
        <c:when test="${user['new']}">
            <c:set var="method" value="post"/>
            <spring:url value="/registration" htmlEscape="true" var="action"/>
        </c:when>
        <c:otherwise>
            <c:set var="method" value="put"/>
            <spring:url value="/users/${userId}/edit" htmlEscape="true" var="action"/>
        </c:otherwise>
    </c:choose>

    <jsp:include page="../fragments/jQueryLib.jsp"/>

    <spring:url value="/resources/css/onlinecontrol.css" var="onlinecontrolCss"/>
    <link href="${onlinecontrolCss}" rel="stylesheet"/>

    <spring:url value="/resources/css/onlinecontrolDialog.css" var="onlinecontrolDialogCss"/>
    <link href="${onlinecontrolDialogCss}" rel="stylesheet"/>

    <spring:url value="/resources/js/onlinecontrolDialog.js" var="onlinecontrolDialogJs"/>
    <script src="${onlinecontrolDialogJs}"></script>

</head>
<body>

<div id="wrapper-login">

    <h1>Register New User</h1>

    <form:form modelAttribute="user" method="${method}" action="${action}">
        <table class="horiz">
            <tr>
                <td><form:label path="login">Login:</form:label></td>
                <td><form:input path="login"/><form:errors path="login" cssStyle="color:red;" cssclass="error"/></td>
            </tr>

            <tr>
                <td><form:label path="firstName">First name:</form:label></td>
                <td><form:input path="firstName"/><form:errors path="firstName" cssStyle="color:red;"
                                                               cssclass="error"/></td>
            </tr>

            <tr>
                <td><form:label path="middleName">Middle name:</form:label></td>
                <td><form:input path="middleName"/><form:errors path="middleName" cssStyle="color:red;"
                                                                cssclass="error"/></td>
            </tr>

            <tr>
                <td><form:label path="lastName">Last name:</form:label></td>
                <td><form:input path="lastName"/><form:errors path="lastName" cssStyle="color:red;"
                                                              cssclass="error"/></td>
            </tr>

            <tr>
                <td><form:label path="password">Password:</form:label></td>
                <td><form:password path="password"/><form:errors path="password" cssStyle="color:red;"
                                                                 cssclass="error"/></td>
            </tr>

        </table>

        <hr>

        <div id="users-contain" class="ui-widget">
            <h1>Roles:</h1>
            <table id="roles" class="ui-widget ui-widget-content">
                <thead>
                <tr class="ui-widget-header ">
                    <th>Name</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${user.roles}" varStatus="vs">
                    <tr>
                        <td>${user.roles[vs.index].name}</td>
                        <form:hidden path="roles[${vs.index}].roleId"/>
                        <form:hidden path="roles[${vs.index}].name"/>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <button class="add-role">Add role</button>

        <hr>

        <div id="users-contain" class="ui-widget">
            <h1>Students:</h1>
            <table id="students" class="ui-widget ui-widget-content">
                <thead>
                <tr class="ui-widget-header">
                    <th>Last name</th>
                    <th>First name</th>
                    <th>Middle name</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${user.students}" varStatus="vs">
                    <tr>
                        <td>${user.students[vs.index].lastName}</td>
                        <td>${user.students[vs.index].firstName}</td>
                        <td>${user.students[vs.index].middleName}</td>
                        <form:hidden path="students[${vs.index}].studentId"/>
                        <form:hidden path="students[${vs.index}].lastName"/>
                        <form:hidden path="students[${vs.index}].firstName"/>
                        <form:hidden path="students[${vs.index}].middleName"/>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <button class="create-student">Add student</button>

        <hr>
        <br>

        <input type="submit" value="Save"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form:form>

</div>

</body>
</html>
