<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html lang="en">
<head>

    <spring:url value="/webjars/jquery/2.1.3/jquery.js" var="jQuery"/>
    <script src="${jQuery}"></script>

    <jsp:include page="../fragments/datatablesLib.jsp"/>

    <script>
        $(document).ready(function() {
            var table = $('#classes').DataTable();
        } );
    </script>

</head>
<body>
<div>

    <h2>Список классов</h2>

    <table id="classes" class="display" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>Имя</th>
            <th>Редактировать</th>
            <th>Удалить</th>
            <th>Детали</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="clazz" items="${clazzes}">
            <tr>
                <td>
                    <c:out value="${clazz}"/>
                </td>
                <td>
                    <a href='<spring:url value="/classes/${clazz.id}/edit" htmlEscape="true"/>' role="button">Редактировать класс</a></p>
                </td>
                <td>
                    <spring:url value="/classes/${clazz.id}/delete" htmlEscape="true" var="action"/>
                    <form:form method="delete" action="${action}">
                        <p class="submit"><input type="submit" value="Удалить класс"/></p>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form:form>
                </td>
                <td>
                    <a href='<spring:url value="/classes/${clazz.id}" htmlEscape="true"/>'>Детальное описание</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>

</body>

</html>