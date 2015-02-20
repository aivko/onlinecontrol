<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="en">
<head>
</head>
<body>
<div>

    <h2>Users Information</h2>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Login</th>
            <th>First name</th>
            <th>Middle name</th>
            <th>Last name</th>
            <th>Students</th>
            <th>Roles</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>
                    <c:out value="${user.userId}"/>
                </td>
                <td>
                    <c:out value="${user.login}"/>
                </td>
                <td>
                    <c:out value="${user.firstName}"/>
                </td>
                <td>
                    <c:out value="${user.middleName}"/>
                </td>
                <td>
                    <c:out value="${user.lastName}"/>
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
                    <a href='<spring:url value="/users/${user.userId}/edit" htmlEscape="true"/>' role="button">Edit</a></p>
                </td>
                <td>
                    <spring:url value="/users/${user.userId}/delete" htmlEscape="true" var="action"/>
                    <form:form method="delete" action="${action}">
                        <p class="submit"><input type="submit" value="Delete"/></p>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form:form>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>

</body>

</html>