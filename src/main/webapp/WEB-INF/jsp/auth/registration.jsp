<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <spring:url value="/registration" htmlEscape="true" var="registration"/>
</head>
<body>

<div id="wrapper-login">

    <h1>Register New User</h1>

    <form:form modelAttribute="userAttribute" method="POST" action="${registration}">
        <table class="horiz">
            <tr>
                <td><form:label path="login">Login:</form:label></td>
                <td><form:input path="login"/></td>
            </tr>

            <tr>
                <td><form:label path="firstName">First name:</form:label></td>
                <td><form:input path="firstName"/></td>
            </tr>

            <tr>
                <td><form:label path="middleName">Middle name:</form:label></td>
                <td><form:input path="middleName"/></td>
            </tr>

            <tr>
                <td><form:label path="lastName">Last name:</form:label></td>
                <td><form:input path="lastName"/></td>
            </tr>

            <tr>
                <td><label path="password">Password:</label></td>
                <td><form:password path="password"/></td>
            </tr>

            <tr>
                <td><label path="roles" for="roles">Roles:</label></td>
                <td>
                    <%--<form:select path="roles" id="roles">--%>
                        <%--<c:forEach items="${roles}" var="role" varStatus="vs">--%>
                            <%--<form:option value="${role}">${role}</form:option>--%>
                        <%--</c:forEach>--%>
                    <%--</form:select>--%>
                    <form:select path="roles">
                        <form:options items="${roles}" itemValue="roleId" itemLabel="name"/>;
                    </form:select>
                </td>
            </tr>

        <%--<tr>--%>
                <%--<td><form:label path="roles">Roles:</form:label></td>--%>
                <%--<td>--%>
                    <%--<form:select path="roles">--%>
                        <%--<form:options items="${roles}" itemValue="roleId" itemLabel="name"></form:options>;--%>
                    <%--</form:select>--%>
                <%--</td>--%>
            <%--</tr>--%>

            <tr>
                <td><label for="students">Students:</label></td>
                <td>
                    <form:select path="students">
                        <form:options items="${students}" itemValue="studentId" itemLabel="lastName"/>;
                    </form:select>
                </td>
            </tr>

        </table>

        <input type="submit" value="Save"/>
    </form:form>

</div>

</body>
</html>
