<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">

<body>
<div>

    <h2>Students Information</h2>

    <table border="1">
        <tr>
            <th>First name</th>
            <th>Middle name</th>
            <th>Last name</th>
            <th>Gender</th>
            <th>Details</th>
        </tr>
        <c:forEach var="student" items="${selections}">
            <tr>
                <td>
                    <c:out value="${student.firstName}"/>
                </td>
                <td>
                    <c:out value="${student.middleName}"/>
                </td>
                <td>
                    <c:out value="${student.lastName}"/>
                </td>
                <td>
                    <c:out value="${student.gender}"/>
                </td>
                <td>
                    <a href='<spring:url value="/students/${student.studentId}" htmlEscape="true"/>'>Detail</a>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>

</body>

</html>