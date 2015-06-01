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
            var tableStudents = $('#students').DataTable();
            var tableRoles = $('#roles').DataTable();
        } );
    </script>

</head>
<body>
<div class="container">

    <h2>Информация пользователя</h2>

    <table>
        <tr>
            <td>Email:</td>
            <td><c:out value="${user.email}"/></td>
        </tr>

    </table>

    <h4>Роли</h4>

    <table id="roles" class="display compact cell-border" cellspacing="0" width="100%">

        <thead>
        <tr class="ui-widget-header ">
            <th>Имя</th>
            <th>Описание</th>
            <th>Удалить роль</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${user.roles}" var="role">
            <tr>
                <td>${role.name}</td>
                <td>${role.description}</td>

                <td>
                    <spring:url value="/users/${user.id}/roles/${role.id}/delete" htmlEscape="true" var="action"/>
                    <form:form method="delete" action="${action}">
                        <p class="submit"><input type="submit" value="Удалить"/></p>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form:form>
                </td>

            </tr>
        </c:forEach>
        </tbody>

    </table>
    <a href='<spring:url value="/users/${user.id}/roles/add" htmlEscape="true"/>' role="button">Добавить роль</a>

</div>

</body>

</html>
