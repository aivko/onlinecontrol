<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<body>
<div class="container">
    <h2>Something happened...</h2>

    <p>${exception.message}</p>

    <div><a href='<spring:url value="/" htmlEscape="true"/>' role="button">Home</a></div>

</div>
</body>

</html>
