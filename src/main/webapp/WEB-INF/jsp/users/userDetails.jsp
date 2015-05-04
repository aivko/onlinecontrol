<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">

<body>
<div class="container">

    <h2>User Information</h2>

    <table class="horiz">
        <tr>
            <td>Login:</td>
            <td><c:out value="${user.login}"/></td>
        </tr>

        <tr>
            <td>First name:</td>
            <td><c:out value="${user.firstName}"/></td>
        </tr>

        <tr>
            <td>Middle name:</td>
            <td><c:out value="${user.middleName}"/></td>
        </tr>

        <tr>
            <td>Last name:</td>
            <td><c:out value="${user.lastName}"/></td>
        </tr>

    </table>

    <h4>Roles</h4>

    <c:forEach var="role" items="${user.roles}">
        <table class="table" style="width:600px;">
            <tr>
                <td valign="top" style="width: 120px;">
                    <dl class="dl-horizontal">
                        <dt>Name</dt>
                        <dd><c:out value="${role.name}"/></dd>
                    </dl>
                </td>
            </tr>
        </table>
    </c:forEach>

</div>

</body>

</html>
