<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">

<body>
<div class="container">

    <h2>Информация о новости</h2>

    <table class="horiz">
        <tr>
            <td>Дата:</td>
            <td><fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${news.date}"/></td>
        </tr>

        <tr>
            <td>Описание новости:</td>
            <td><c:out value="${news.topic}"/></td>
        </tr>

        <tr>
            <td>Детальное описание новости:</td>
            <td><c:out value="${news.text}"/></td>
        </tr>

    </table>

</div>

</body>

</html>
