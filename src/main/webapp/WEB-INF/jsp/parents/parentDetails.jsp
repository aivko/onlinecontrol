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
        } );
    </script>

</head>
<body>
<div class="container">

    <h2>Информация о родителе</h2>

    <table class="table table-striped" style="width:600px;">
        <tr>
            <th>ФИО</th>
            <td><b><c:out value="${parent.lastName} ${parent.firstName} ${parent.lastName}"/></b></td>
        </tr>
        <tr>
            <th>Дата рождения</th>
            <td><fmt:formatDate pattern="dd.MM.yyyy" value="${parent.dateOfBirth}"/></td>
        </tr>
        <tr>
            <th>Пользователь</th>
            <td><c:out value="${parent.user.email}"/></td>
        </tr>
        <tr>
            <th>Пол</th>
            <td><c:out value="${parent.gender}"/></td>
        </tr>
    </table>

    <h4>Студенты</h4>

    <table id="students" class="display compact cell-border" cellspacing="0" width="100%">

        <thead>
        <tr class="ui-widget-header ">
            <th>Last name</th>
            <th>First name</th>
            <th>Middle name</th>
            <th>Удалить роль</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${parent.students}" var="student">
            <tr>
                <td>${student.lastName}</td>
                <td>${student.firstName}</td>
                <td>${student.middleName}</td>

                <td>
                    <spring:url value="/parents/${parent.id}/students/${student.id}/delete" htmlEscape="true" var="action"/>
                    <form:form method="delete" action="${action}">
                        <p class="submit"><input type="submit" value="Удалить"/></p>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form:form>
                </td>

            </tr>
        </c:forEach>
        </tbody>

    </table>
    <a href='<spring:url value="/parents/${parent.id}/students/add" htmlEscape="true"/>' role="button">Добавить студента</a>

</div>

</body>

</html>
