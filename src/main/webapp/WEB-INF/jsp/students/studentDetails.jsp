<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">

<body>
<div class="container">

    <h2>Student Information</h2>

    <table class="table table-striped" style="width:600px;">
        <tr>
            <th>Name</th>
            <td><b><c:out value="${student.firstName} ${student.lastName}"/></b></td>
        </tr>
        <tr>
            <th>Date of Birth</th>
            <td><fmt:formatDate pattern="dd.MM.yyyy" value="${student.dateOfBirth}"/></td>
        </tr>
        <tr>
            <th>Class</th>
            <td><c:out value="${student.clazz.name}"/></td>
        </tr>
        <tr>
            <th>Gender</th>
            <td><c:out value="${student.gender}"/></td>
        </tr>
    </table>

    <h2>Parents</h2>

    <table class="horiz">

        <thead>
        <tr class="ui-widget-header ">
            <th>Last name</th>
            <th>First name</th>
            <th>Middle name</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${student.users}" var="user">
            <tr>
                <td>${user.lastName}</td>
                <td>${user.firstName}</td>
                <td>${user.middleName}</td>
            </tr>
        </c:forEach>
        </tbody>

    </table>

</div>

</body>

</html>
