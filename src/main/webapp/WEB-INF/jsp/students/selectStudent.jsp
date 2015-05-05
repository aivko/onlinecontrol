<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>

    <spring:url value="/webjars/jquery/2.1.3/jquery.js" var="jQuery"/>
    <script src="${jQuery}"></script>

    <spring:url value="/webjars/datatables/1.10.7/js/jquery.dataTables.js" var="dataTablesJs"/>
    <script src="${dataTablesJs}"></script>

    <spring:url value="/webjars/datatables/1.10.7/css/jquery.dataTables.css" var="dataTablesCss"/>
    <link href="${dataTablesCss}" rel="stylesheet"/>

    <script>
        $(document).ready(function() {
            $('#example').DataTable();
        } );
    </script>

</head>
<body>
<div>

    <h2>Students Information</h2>

    <table id="example" class="display" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>ID</th>
            <th>First name</th>
            <th>Middle name</th>
            <th>Last name</th>
            <th>Gender</th>
            <th>Class</th>
            <th>Details</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="student" items="${students}">
            <tr>
                <td>
                    <c:out value="${student.studentId}"/>
                </td>
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
                    <c:out value="${student.clazz.name}"/>
                </td>
                <td>
                    <a href='<spring:url value="/students/${student.studentId}" htmlEscape="true"/>'>Detail</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
   </table>

</div>

</body>

</html>