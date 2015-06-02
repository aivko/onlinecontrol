<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>

</head>
<body>
<div>

    <h2>User account</h2>

    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <p><a href='<spring:url value="/users/new" htmlEscape="true"/>' role="button">Добавить пользователя</a></p>
        <p><a href='<spring:url value="/users" htmlEscape="true"/>' role="button">Список пользователей</a></p>
    </sec:authorize>
    <hr/>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <p><a href='<spring:url value="/parents/new" htmlEscape="true"/>' role="button">Добавить родителя</a></p>
        <p><a href='<spring:url value="/parents" htmlEscape="true"/>' role="button">Список родителей</a></p>
    </sec:authorize>
    <hr/>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <p><a href='<spring:url value="/roles/new" htmlEscape="true"/>' role="button">Добавить роль</a></p>
        <p><a href='<spring:url value="/roles" htmlEscape="true"/>' role="button">Список ролей</a></p>
    </sec:authorize>
    <hr/>
    <p><a href='<spring:url value="/students/find" htmlEscape="true"/>'>Найти студента</a></p>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <p><a href='<spring:url value="/students/new" htmlEscape="true"/>' role="button">Добавить студента</a></p>
        <p><a href='<spring:url value="/students" htmlEscape="true"/>' role="button">Список студентов</a></p>
    </sec:authorize>
    <hr/>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <p><a href='<spring:url value="/classes/new" htmlEscape="true"/>' role="button">Добавить класс</a></p>
        <p><a href='<spring:url value="/classes" htmlEscape="true"/>' role="button">Список классов</a></p>
    </sec:authorize>
    <hr/>
    <p><a href='<spring:url value="/users/${user.id}/changePassword" htmlEscape="true"/>'>Изменить пароль</a></p>

</div>

</body>

</html>