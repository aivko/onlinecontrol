<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Изменение пароля</title>
    <spring:url value="/users/${user.userId}/changePassword" htmlEscape="true" var="action"/>
</head>

<body>

<form action="${action}" method="post">

    <div>
        <label for="passwordOld">Старый пароль:</label>
        <input id="passwordOld" name="passwordOld" size="20" maxlength="50" type="password" />
    </div>
    <div>
        <label for="password">Новый пароль:</label>
        <input id="password" name="password" size="20" maxlength="50" type="password" />
    </div>
    <div>
        <label for="passwordConfirm">Повторите новый пароль:</label>
        <input id="passwordConfirm" name="passwordConfirm" size="20" maxlength="50" type="password" />
    </div>

    <input type="submit" name="submit" value="Сохранить"/>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

</body>
</html>