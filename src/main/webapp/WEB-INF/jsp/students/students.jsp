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
            var table = $('#students').DataTable();
        } );
    </script>

</head>
<body>
<div>

    <h2>Список студентов</h2>

    <table id="students" class="display" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>Фамилия</th>
            <th>Имя</th>
            <th>Отчество</th>
            <th>Дата рождения</th>
            <th>Пол</th>
            <th>Пользователь</th>
            <th>Родители</th>
            <th>Класс</th>
            <th>Редактировать</th>
            <th>Детали</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="student" items="${students}">
            <tr>
                <td>
                    <c:out value="${student.lastName}"/>
                </td>
                <td>
                    <c:out value="${student.firstName}"/>
                </td>
                <td>
                    <c:out value="${student.middleName}"/>
                </td>
                <td>
                    <fmt:formatDate pattern="dd.MM.yyyy" value="${student.dateOfBirth}"/>
                </td>
                <td>
                    <c:out value="${student.gender}"/>
                </td>
                <td>
                    <c:out value="${student.user.email}"/>
                </td>
                <td>
                    <c:forEach var="parent" items="${student.parents}">
                        <c:out value="${parent.firstName} ${parent.middleName} ${parent.lastName}"/><br>
                    </c:forEach>
                </td>
                <td>
                    <c:out value="${student.clazz.name}"/>
                </td>
                <td>
                    <a href='<spring:url value="/students/${student.id}/edit" htmlEscape="true"/>' role="button">Редактировать студента</a></p>
                </td>
                <td>
                    <a href='<spring:url value="/students/${student.id}" htmlEscape="true"/>'>Детальное описание</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>

</body>

</html>