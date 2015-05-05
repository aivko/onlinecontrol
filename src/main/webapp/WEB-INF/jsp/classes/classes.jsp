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
</head>
<body>
<div>

    <h2>Classes Information</h2>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Edit</th>
            <th>Delete</th>
            <th>Detail</th>
        </tr>
        <c:forEach var="clazz" items="${clazzes}">
            <tr>
                <td>
                    <c:out value="${clazz.clazzId}"/>
                </td>
                <td>
                    <c:out value="${clazz.name}"/>
                </td>
                <td>
                    <a href='<spring:url value="/classes/${clazz.clazzId}/edit" htmlEscape="true"/>' role="button">Edit</a></p>
                </td>
                <td>
                    <spring:url value="/classes/${clazz.clazzId}/delete" htmlEscape="true" var="action"/>
                    <form:form method="delete" action="${action}">
                        <p class="submit"><input type="submit" value="Delete"/></p>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form:form>
                </td>
                <td>
                    <a href='<spring:url value="/classes/${clazz.clazzId}" htmlEscape="true"/>'>Detail</a>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>

</body>

</html>