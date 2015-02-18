<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Авторизация</title>
</head>

<body>

<form:form action="j_spring_security_check" method="post">
    <h2>Авторизация</h2>

    <div>
        <label for="user" class="icon-user">Логин:</label>
        <input class="user" id="user" name="j_username"/>
    </div>
    <div>
        <label for="password" class="icon-lock">Пароль:</label>
        <input type="password" class="password" id="password" name="j_password"/>
    </div>
    <div>
        <label for="remember"><input type="checkbox" id="remember" name="_spring_security_remember_me"/><span
                class="remember"/> Запомнить
            меня</label>

    </div>

    <input type="submit" name="submit" value="Войти"/>

    <div class="extra">
        <a href="#" class="forgetPassword">Забыл пароль</a>
    </div>
    <div>
        <a href='<spring:url value="/registration/facebook" htmlEscape="true"/>'
           class="facebook icon-facebook">Facebook</a>
        <a href='<spring:url value="/registration/google" htmlEscape="true"/>'
           class="googlePlus icon-google-plus-sign">Google+</a>
    </div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form:form>

</body>
</html>