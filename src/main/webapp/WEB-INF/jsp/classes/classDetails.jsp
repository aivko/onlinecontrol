<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="en">
<head>

    <spring:url value="/webjars/jquery/2.1.3/jquery.js" var="jQuery"/>
    <script src="${jQuery}"></script>

    <jsp:include page="../fragments/datatablesLib.jsp"/>

    <script>
        $(document).ready(function() {
            var table = $('#students').DataTable();
        } );
    </script>

</head>
<body>
<div class="container">

    <h2>Информация о классе</h2>

    <table class="horiz">
        <tr>
            <td>Имя:</td>
            <td><c:out value="${clazz.name}"/></td>
        </tr>
        <tr>
            <td>Школа:</td>
            <td><c:out value="${clazz.school.name}"/></td>
        </tr>
    </table>

    <h2>Студенты</h2>

    <table id="students" class="display" cellspacing="0" width="100%">

        <thead>
        <tr>
            <th>Фамилия</th>
            <th>Имя</th>
            <th>Отчество</th>
            <th>Дата рождения</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${clazz.students}" var="student">
            <tr>
                <td>${student.lastName}</td>
                <td>${student.firstName}</td>
                <td>${student.middleName}</td>
                <td><fmt:formatDate pattern="dd.MM.yyyy" value="${student.dateOfBirth}"/></td>
            </tr>
        </c:forEach>
        </tbody>

    </table>

</div>

</body>

</html>
