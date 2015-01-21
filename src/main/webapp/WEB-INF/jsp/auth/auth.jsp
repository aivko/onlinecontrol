<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Authorization</title>
    <link rel='stylesheet prefetch' href='http://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css'>
    <spring:url value="/resources/js/auth.js" var="auth_js"/>
    <spring:url value="/resources/images/img_auth.jpg" var="auth_image"/>
    <spring:url value="/resources/css/style.css" var="auth_style"/>
    <link rel="stylesheet" href="${auth_style}" media="screen" type="text/css"/>
</head>

<body>

<form id="login" class="login">
    <h2>Авторизация</h2>
    <label for="user" class="icon-user">Логин:</label>
    <input class="user" id="user"/>
    <label for="password" class="icon-lock">Пароль:</label>
    <input type="password" class="password" id="password"/>
    <label for="remember"><input type="checkbox" id="remember"/><span class="remember"/> Запомнить меня</label>
    <input type="submit" value="Войти"/>

    <div class="extra">
        <a href="#" class="forgetPassword">Забыл пароль</a>

        <a href='<spring:url value="/registrate/facebook" htmlEscape="true"/>' class="facebook icon-facebook">Facebook</a>
        <a href='<spring:url value="/registrate/google" htmlEscape="true"/>' class="googlePlus icon-google-plus-sign">Google+</a>
        <%--<a href="/registrate/facebook" class="facebook icon-facebook">Facebook</a>--%>
        <%--<a href="/registrate/google" class="googlePlus icon-google-plus-sign">Google+</a>--%>
    </div>
</form>

<canvas class="blur" src="${auth_image}"></canvas>
<script src="http://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
<script src="${auth_js}"></script>
</body>
</html>