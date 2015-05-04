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
    <sec:authorize access="isAuthenticated()">
        <sec:authentication property="principal.userId" var="userId"/>
    </sec:authorize>
    <c:choose>
        <c:when test="${role['new']}">
            <c:set var="method" value="post"/>
            <spring:url value="/roles/new" htmlEscape="true" var="action"/>
        </c:when>
        <c:otherwise>
            <c:set var="method" value="put"/>
            <spring:url value="/roles/${roleId}/edit" htmlEscape="true" var="action"/>
        </c:otherwise>
    </c:choose>

</head>
<body>

<div id="wrapper-login">

    <h1>Role</h1>

    <form:form modelAttribute="role" method="${method}" action="${action}">
        <table class="horiz">
            <tr>
                <td><form:label path="name">Name:</form:label></td>
                <td><form:input path="name"/><form:errors path="name" cssStyle="color:red;" cssclass="error"/></td>
            </tr>

            <tr>
                <td><form:label path="description">Description:</form:label></td>
                <td><form:input path="description"/><form:errors path="description" cssStyle="color:red;"
                                                               cssclass="error"/></td>
            </tr>

        </table>

        <hr>

        <input type="submit" value="Save"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form:form>

</div>

</body>
</html>
