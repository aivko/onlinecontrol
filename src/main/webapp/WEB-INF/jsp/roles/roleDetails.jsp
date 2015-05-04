<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">

<body>
<div class="container">

    <h2>Role Information</h2>

    <table class="horiz">
        <tr>
            <td>Name:</td>
            <td><c:out value="${role.name}"/></td>
        </tr>

        <tr>
            <td>Description:</td>
            <td><c:out value="${role.description}"/></td>
        </tr>

    </table>

</div>

</body>

</html>
