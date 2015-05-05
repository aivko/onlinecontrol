<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="en">

<body>
<div class="container">

    <h2>User Information</h2>

    <table class="horiz">
        <tr>
            <td>Login:</td>
            <td><c:out value="${user.login}"/></td>
        </tr>

        <tr>
            <td>First name:</td>
            <td><c:out value="${user.firstName}"/></td>
        </tr>

        <tr>
            <td>Middle name:</td>
            <td><c:out value="${user.middleName}"/></td>
        </tr>

        <tr>
            <td>Last name:</td>
            <td><c:out value="${user.lastName}"/></td>
        </tr>

    </table>

    <h4>Roles</h4>

    <table class="horiz">

        <thead>
        <tr class="ui-widget-header ">
            <th>Name</th>
            <th>Description</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${user.roles}" var="role">
            <tr>
                <td>${role.name}</td>
                <td>${role.description}</td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
    <a href='<spring:url value="/users/${user.userId}/roles/" htmlEscape="true"/>' role="button">Add role</a>

    <h4>Students</h4>

    <table class="horiz">

        <thead>
        <tr class="ui-widget-header ">
            <th>Last name</th>
            <th>First name</th>
            <th>Middle name</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${user.students}" var="student">
            <tr>
                <td>${student.lastName}</td>
                <td>${student.firstName}</td>
                <td>${student.middleName}</td>
            </tr>
        </c:forEach>
        </tbody>

    </table>

</div>

</body>

</html>
