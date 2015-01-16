<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Online control</title>
</head>
<body>

<div>
    <h1>Start page</h1>
    <h2>Find students</h2>
    <a href='<spring:url value="/students/find" htmlEscape="true"/>'>Find</a>
</div>

</body>
</html>
