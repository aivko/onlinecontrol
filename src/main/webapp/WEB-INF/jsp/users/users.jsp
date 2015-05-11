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

    <h2>Пользователи</h2>

    <table id="users" class="display" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>Email</th>
            <th>Password</th>
            <th>Фамилия</th>
            <th>Имя</th>
            <th>Отчество</th>
            <th>Студенты</th>
            <th>Роли</th>
            <th>Редактировать</th>
            <th>Детали</th>
            <th>Удалить</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>
                    <c:out value="${user.email}"/>
                </td>
                <td>
                    <c:out value="${user.password}"/>
                </td>
                <td>
                    <c:out value="${user.lastName}"/>
                </td>
                <td>
                    <c:out value="${user.firstName}"/>
                </td>
                <td>
                    <c:out value="${user.middleName}"/>
                </td>
                <td>
                    <c:forEach var="student" items="${user.students}">
                        <c:out value="${student.firstName} ${student.middleName} ${student.lastName}"/><br>
                    </c:forEach>
                </td>

                <td>
                    <c:forEach var="role" items="${user.roles}">
                        <c:out value="${role.name}"/><br>
                    </c:forEach>
                </td>
                <td>
                    <a href='<spring:url value="/users/${user.userId}/edit" htmlEscape="true"/>' role="button">Редактировать пользователя</a></p>
                </td>
                <td>
                    <a href='<spring:url value="/users/${user.userId}" htmlEscape="true"/>' role="button">Детальное описание</a></p>
                </td>
                <td>
                    <spring:url value="/users/${user.userId}/delete" htmlEscape="true" var="action"/>
                    <form:form method="delete" action="${action}">
                        <p class="submit"><input type="submit" value="Удалить пользователя"/></p>
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