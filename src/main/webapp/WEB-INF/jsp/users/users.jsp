<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">

<body>
<div>

    <h2>Users Information</h2>

    <table border="1">
        <tr>
            <th>Login</th>
            <th>First name</th>
            <th>Middle name</th>
            <th>Last name</th>
            <th>Students</th>
            <th>Roles</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
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

            </tr>
        </c:forEach>
    </table>

</div>

</body>

</html>