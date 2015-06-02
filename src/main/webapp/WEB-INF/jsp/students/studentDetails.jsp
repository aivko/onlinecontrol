<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>

    <spring:url value="/webjars/jquery/2.1.3/jquery.js" var="jQuery"/>
    <script src="${jQuery}"></script>

    <jsp:include page="../fragments/datatablesLib.jsp"/>

    <script>
        $(document).ready(function() {
            var table = $('#parents').DataTable();
        } );
    </script>

</head>

<body>
<div class="container">

    <h2>Информация о студенте</h2>

    <table class="table table-striped" style="width:600px;">
        <tr>
            <th>ФИО</th>
            <td><b><c:out value="${student.lastName} ${student.firstName} ${student.lastName}"/></b></td>
        </tr>
        <tr>
            <th>Дата рождения</th>
            <td><fmt:formatDate pattern="dd.MM.yyyy" value="${student.dateOfBirth}"/></td>
        </tr>
        <tr>
            <th>Пользователь</th>
            <td><c:out value="${student.user.email}"/></td>
        </tr>
        <tr>
            <th>Класс</th>
            <td><c:out value="${student.clazz.name}"/></td>
        </tr>
        <tr>
            <th>Пол</th>
            <td><c:out value="${student.gender}"/></td>
        </tr>
    </table>

    <h2>Родители</h2>

    <table id="parents" class="display compact" cellspacing="0" width="100%">

        <thead>
        <tr class="ui-widget-header ">
            <th>Last name</th>
            <th>First name</th>
            <th>Middle name</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${student.parents}" var="parent">
            <tr>
                <td>${parent.lastName}</td>
                <td>${parent.firstName}</td>
                <td>${parent.middleName}</td>
            </tr>
        </c:forEach>
        </tbody>

    </table>

</div>

</body>

</html>
