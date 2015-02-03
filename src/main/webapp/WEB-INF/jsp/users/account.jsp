<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <spring:url value="/students/find" var="findStudents" htmlEscape="true"/>
</head>
<body>
<div>

    <h2>User account</h2>

    <a href="${findStudents}">Найти студента</a>

    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <p><a href='<spring:url value="/registration" htmlEscape="true"/>' role="button">Зарегистрировать пользователя</a></p>
        <p><a href='<spring:url value="/users" htmlEscape="true"/>' role="button">Список пользователей</a></p>
    </sec:authorize>


</div>

</body>

</html>