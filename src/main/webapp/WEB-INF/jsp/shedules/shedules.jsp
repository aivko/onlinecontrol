<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html lang="en">
<head>

    <spring:url value="/webjars/jquery/2.1.3/jquery.js" var="jQuery"/>
    <script src="${jQuery}"></script>

    <jsp:include page="../fragments/datatablesLib.jsp"/>

    <script>
        $(document).ready(function() {
            var table = $('#shedules').DataTable();
        } );
    </script>

</head>
<body>
<div>

    <h2>Расписание</h2>

    <table id="shedules" class="display compact" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>Дата</th>
            <th>Период</th>
            <th>Предмет</th>
            <th>Класс</th>
            <th>Учитель</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="shedule" items="${shedules}">
            <tr>
                <td>
                    <fmt:formatDate pattern="dd.MM.yyyy" value="${shedule.date}"/>
                </td>
                <td>
                    <c:out value="${shedule.period}"/>
                </td>
                <td>
                    <c:out value="${shedule.subject.name}"/>
                </td>
                <td>
                    <c:out value="${shedule.clazz}"/>
                </td>
                <td>
                    <c:out value="${shedule.teacher.lastName} ${shedule.teacher.firstName} ${shedule.teacher.middleName}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>

</body>

</html>