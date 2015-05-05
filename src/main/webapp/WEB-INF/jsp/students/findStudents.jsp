<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">

<head>
    <spring:url value="/students/find" htmlEscape="true" var="action"/>
</head>

<body>
<div>

    <h2>Find students</h2>

</div>

<form:form modelAttribute="student" action="${action}" method="post">

    <div>
        <form:label path="lastName">Введите фамилию:</form:label>
        <form:input path="lastName"/>
    </div>

    <input type="submit" name="submit" value="Найти"/>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form:form>

</body>

</html>