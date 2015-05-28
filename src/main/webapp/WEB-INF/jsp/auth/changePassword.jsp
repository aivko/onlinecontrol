<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Изменение пароля</title>
    <spring:url value="/users/${user.id}/changePassword" htmlEscape="true" var="action"/>
</head>

<body>

<form:form action="${action}" method="post" modelAttribute="changePassword">

    <div>
        <form:label path="passwordOld">Старый пароль:</form:label>
        <form:password path="passwordOld"/>
        <form:errors path="passwordOld" cssStyle="color:red;" cssclass="error"/>
    </div>
    <div>
        <form:label path="password">Новый пароль:</form:label>
        <form:password path="password"/>
        <form:errors path="password" cssStyle="color:red;" cssclass="error"/>
    </div>
    <div>
        <form:label path="passwordConfirm">Повторите новый пароль:</form:label>
        <form:password path="passwordConfirm"/>
        <form:errors path="passwordConfirm" cssStyle="color:red;" cssclass="error"/>
    </div>

    <input type="submit" name="submit" value="Сохранить"/>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

</form:form>

</body>
</html>