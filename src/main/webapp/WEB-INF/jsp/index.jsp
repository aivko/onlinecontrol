<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Online control</title>
    <sec:authorize access="isAuthenticated()">
        <sec:authentication property="principal.id" var="userId"/>
        <sec:authentication property="principal.email" var="email"/>
    </sec:authorize>
    <spring:url value="/resources/frontend/css/reset.css" var="ResetCss"/>
    <link href="${ResetCss}" rel="stylesheet"/>
    <spring:url value="/resources/frontend/css/style.css" var="MainCss"/>
    <link href="${MainCss}" rel="stylesheet"/>

</head>
<body>
    <header class="index-header">
        <div class="header-line clearfix">
            <img src="resources/frontend/image/logo.jpg" alt="logo" class="image" />
            <div class="header-button">
                <div class="auth-button">
                    <sec:authorize access="isAuthenticated()">
                        <a href='<spring:url value="/users/${userId}/account" htmlEscape="true"/>' role="button">Account</a>
                    </sec:authorize>
                    <sec:authorize access="!isAuthenticated()">
                        <a href='<spring:url value="/login" htmlEscape="true"/>' role="button" class="button">Войти</a>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <p>Ваш логин: ${email}</p>

                        <%--<p><a href='<spring:url value="/logout" htmlEscape="true"/>' role="button">Выйти</a></p>--%>
                        <c:url var="logoutUrl" value="/logout"/>
                        <form class="logout" action="${logoutUrl}" method="post">
                            <input class="buttonLogout" type="submit" value="Выход" />
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                    </sec:authorize>
                </div>
                <ul class="header-list">
                    <li class="header-item">
                        <a href="#">О нас</a>
                    </li>
                    <li class="header-item">
                        <a href="#">Информация для родителей</a>
                    </li>
                    <li class="header-item">
                        <a href="#">Технические характеристики</a>
                    </li>
                </ul>
            </div>
       </div>
    </header>
<div>
    <h1>Start page</h1>
</div>

</body>
</html>
