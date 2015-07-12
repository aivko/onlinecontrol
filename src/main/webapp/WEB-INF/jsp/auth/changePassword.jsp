<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Изменение пароля</title>
    <spring:url value="/users/${user.id}/changePassword" htmlEscape="true" var="action"/>
    <spring:url value="/resources/frontend/css/reset.css" var="ResetCss"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="${ResetCss}" rel="stylesheet"/>
    <spring:url value="/resources/frontend/css/style.css" var="MainCss"/>
    <link href="${MainCss}" rel="stylesheet"/>
</head>
<body>
    <div class="main-content clearfix">
        <div class="wrapper">
            <header class="index-header">
                <div class="container">
                    <div class="header-line clearfix row">
                        <img src="resources/frontend/image/logo.jpg" alt="logo" class="image" />
                    </div>
                </div>
            </header>
        <form:form action="${action}" method="post" modelAttribute="changePassword" class="form-login">
            <h2 class="autor-login">Смена пароля</h2>
            <div class="data-author">
                <form:label path="passwordOld" class="data-user">Старый пароль:</form:label>
                <form:password path="passwordOld" class="data-info" />
                <form:errors path="passwordOld" cssStyle="color:red;" cssclass="error"/>
            </div>
            <div class="data-author">
                <form:label path="password" class="data-user">Новый пароль:</form:label>
                <form:password path="password" class="data-info" />
                <form:errors path="password" cssStyle="color:red;" cssclass="error"/>
            </div>
            <div class="data-author">
                <form:label path="passwordConfirm" class="data-user">Повторите новый пароль:</form:label>
                <form:password path="passwordConfirm" class="data-info" />
                <form:errors path="passwordConfirm" cssStyle="color:red;" cssclass="error"/>
            </div>

            <input class="submit-button" type="submit" name="submit" value="Сохранить"/>

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        </form:form>
        </div>
    </div>
    <div class="footer-index clearfix">
        <p class="footer-mainPage">work project &copy; 2015</p>
    </div>
</body>
</html>