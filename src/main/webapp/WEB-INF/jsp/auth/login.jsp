<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Авторизация</title>
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
                        <div class="cols col-2">
                            <img src="resources/frontend/image/logo.jpg" alt="logo" class="image" />
                        </div>
                    </div>
                </div>
            </header>
            <form:form action="j_spring_security_check" method="post" class="form-login">
                <h2 class="autor-login">Авторизация</h2>

                <div class="data-author">
                    <label for="user" class="icon-user data-user">Email address:</label>
                    <input class="user data-info" id="user" name="j_username"/>
                </div>
                <div class="data-author">
                    <label for="password" class="icon-lock data-user">Password:</label>
                    <input type="password" class="password data-info" id="password" name="j_password"/>
                </div>
                <div class="data-author">
                    <label for="remember" class="data-user"><input type="checkbox" id="remember" name="_spring_security_remember_me"/>
                        <span class="remember"/> Запомнить меня</label>
                </div class="data-author">
                <input class="submit-button" type="submit" name="submit" value="Войти"/>
                <div class="extra data-author">
                    <a href="#" class="forgetPassword">Забыл пароль</a>
                </div>
                <div class="data-author">
                    <a href='<spring:url value="/registration/facebook" htmlEscape="true"/>'
                       class="facebook icon-facebook"><img src="resources/frontend/image/facebook-logo.png" alt="facebook-icon" />&nbsp;</a>
                    <a href='<spring:url value="/registration/google" htmlEscape="true"/>'
                       class="googlePlus icon-google-plus-sign"><img src="resources/frontend/image/google-logo.png" alt="google-icon" />&nbsp;</a>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form:form>
         </div>
    </div>
    <div class="footer-index clearfix">
        <p class="footer-mainPage">work project &copy; 2015</p>
    </div>
</body>
</html>