<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">

<body>
<div class="container">

    <h2>Class Information</h2>

    <table class="horiz">
        <tr>
            <td>Name:</td>
            <td><c:out value="${clazz.name}"/></td>
        </tr>
        <tr>
            <td>School:</td>
            <td><c:out value="${clazz.school}"/></td>
        </tr>
    </table>

    <h2>Students</h2>

    <table class="horiz">

        <thead>
        <tr class="ui-widget-header ">
            <th>Last name</th>
            <th>First name</th>
            <th>Middle name</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${clazz.students}" var="student">
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
