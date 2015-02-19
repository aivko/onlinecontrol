<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<body>
<div class="container">
    <h1>${exception.errorCode}</h1>

    <h2>${exception.errorMsg}</h2>

    <div><a href='<spring:url value="/" htmlEscape="true"/>' role="button">Home</a></div>

</div>
</body>

</html>
