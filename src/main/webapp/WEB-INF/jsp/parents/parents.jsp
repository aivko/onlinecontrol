<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="en">
<head>

    <spring:url value="/webjars/jquery/2.1.3/jquery.js" var="jQuery"/>
    <script src="${jQuery}"></script>

    <jsp:include page="../fragments/datatablesLib.jsp"/>

    <script>
        $(document).ready(function() {
            var table = $('#users').DataTable();
        } );
    </script>

</head>
<body>
<div>

    <h2>Список родителей</h2>

    <table id="users" class="display" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>Фамилия</th>
            <th>Имя</th>
            <th>Отчество</th>
            <th>Дата рождения</th>
            <th>Пол</th>
            <th>Пользователь</th>
            <th>Студенты</th>
            <th>Редактировать</th>
            <th>Детали</th>
            <th>Удалить</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="parent" items="${parents}">
            <tr>
                <td>
                    <c:out value="${parent.lastName}"/>
                </td>
                <td>
                    <c:out value="${parent.firstName}"/>
                </td>
                <td>
                    <c:out value="${parent.middleName}"/>
                </td>
                <td>
                    <fmt:formatDate pattern="dd.MM.yyyy" value="${parent.dateOfBirth}"/>
                </td>
                <td>
                    <c:out value="${parent.gender}"/>
                </td>
                <td>
                    <c:out value="${parent.user.email}"/>
                </td>
                <td>
                    <c:forEach var="student" items="${parent.students}">
                        <c:out value="${student.firstName} ${student.middleName} ${student.lastName}"/><br>
                    </c:forEach>
                </td>

                <td>
                    <a href='<spring:url value="/parents/${parent.id}/edit" htmlEscape="true"/>' role="button">Редактировать родителя</a></p>
                </td>
                <td>
                    <a href='<spring:url value="/parents/${parent.id}" htmlEscape="true"/>' role="button">Детальное описание</a></p>
                </td>
                <td>
                    <spring:url value="/parents/${parent.id}/delete" htmlEscape="true" var="action"/>
                    <form:form method="delete" action="${action}">
                        <p class="submit"><input type="submit" value="Удалить родителя"/></p>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form:form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>

</body>

</html>