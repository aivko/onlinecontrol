<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Online control</title>
    <sec:authorize access="isAuthenticated()">
        <sec:authentication property="principal.userId" var="userId"/>
    </sec:authorize>
</head>
<body>
<div>
    <h1>Start page</h1>

    <sec:authorize access="isAuthenticated()">
        <p><a href='<spring:url value="/users/${userId}/account" htmlEscape="true"/>' role="button">Account</a></p>
    </sec:authorize>
    <sec:authorize access="!isAuthenticated()">
        <p><a href='<spring:url value="/login" htmlEscape="true"/>' role="button">Войти</a></p>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <p>Ваш логин: ${userName}</p>

        <%--<p><a href='<spring:url value="/logout" htmlEscape="true"/>' role="button">Выйти</a></p>--%>
        <c:url var="logoutUrl" value="/logout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Log out" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </sec:authorize>

</div>

</body>
</html>
