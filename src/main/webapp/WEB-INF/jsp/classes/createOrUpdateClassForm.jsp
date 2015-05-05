<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <c:choose>
        <c:when test="${clazz['new']}">
            <c:set var="method" value="post"/>
            <spring:url value="/classes/new" htmlEscape="true" var="action"/>
        </c:when>
        <c:otherwise>
            <c:set var="method" value="put"/>
            <spring:url value="/classes/${clazz.clazzId}/edit" htmlEscape="true" var="action"/>
        </c:otherwise>
    </c:choose>

</head>
<body>

<div id="wrapper-login">

    <h1>Class</h1>

    <form:form modelAttribute="clazz" method="${method}" action="${action}">
        <table class="horiz">
            <tr>
                <td><form:label path="name">Name:</form:label></td>
                <td><form:input path="name"/><form:errors path="name" cssStyle="color:red;" cssclass="error"/></td>
            </tr>
        </table>

        <hr>

        <input type="submit" value="Save"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form:form>

</div>

</body>
</html>
