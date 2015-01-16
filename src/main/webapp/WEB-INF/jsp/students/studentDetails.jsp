<!DOCTYPE html> 

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">

<body>
<div class="container">

    <h2>Student Information</h2>

    <table class="table table-striped" style="width:600px;">
        <tr>
            <th>Name</th>
            <td><b><c:out value="${student.firstName} ${student.lastName}"/></b></td>
        </tr>
        <tr>
            <th>Date of Birth</th>
            <td><c:out value="${student.dateOfBirth}"/></td>
        </tr>
        <tr>
            <th>Class</th>
            <td><c:out value="${student.clazz}"/></td>
        </tr>
    </table>

    <h2>Parents</h2>

    <c:forEach var="user" items="${student.users}">
        <table class="table" style="width:600px;">
            <tr>
                <td valign="top" style="width: 120px;">
                    <dl class="dl-horizontal">
                        <dt>Name</dt>
                        <dd><c:out value="${user.firstName}"/></dd>
                        <dt>Middle name</dt>
                        <dd><c:out value="${user.middleName}"/></dd>
                        <dt>Type</dt>
                        <dd><c:out value="${user.lastName}"/></dd>
                    </dl>
                </td>
            </tr>
        </table>
    </c:forEach>

</div>

</body>

</html>
