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
        <sec:authentication property="principal.login" var="login"/>
    </sec:authorize>
    <c:choose>
        <c:when test="${user['new']}">
            <c:set var="method" value="post"/>
            <spring:url value="/registration" htmlEscape="true" var="action"/>
        </c:when>
        <c:otherwise>
            <c:set var="method" value="put"/>
            <spring:url value="/users/${userId}/edit" htmlEscape="true" var="action"/>
        </c:otherwise>
    </c:choose>

    <jsp:include page="../fragments/jQueryLib.jsp"/>

    <spring:url value="/resources/css/onlinecontrolDialog.css" var="onlinecontrolDialogCss"/>
    <link href="${onlinecontrolDialogCss}" rel="stylesheet"/>

    <spring:url value="/resources/js/onlinecontrolDialog.js" var="onlinecontrolDialog"/>
    <script src="${onlinecontrolDialog}"></script>

    <spring:url value="/resources/css/onlinecontrol.css" var="onlinecontrolCss"/>
    <link href="${onlinecontrolCss}" rel="stylesheet"/>


</head>
<body>

<div id="wrapper-login">

    <h1>Register New User</h1>

    <form:form modelAttribute="user" method="${method}" action="${action}">
        <table class="horiz">
            <tr>
                <td><form:label path="login">Login:</form:label></td>
                <td><form:input path="login"/><form:errors path="login" cssStyle="color:red;" cssclass="error"/></td>
            </tr>

            <tr>
                <td><form:label path="firstName">First name:</form:label></td>
                <td><form:input path="firstName"/><form:errors path="firstName" cssStyle="color:red;"
                                                               cssclass="error"/></td>
            </tr>

            <tr>
                <td><form:label path="middleName">Middle name:</form:label></td>
                <td><form:input path="middleName"/><form:errors path="middleName" cssStyle="color:red;"
                                                                cssclass="error"/></td>
            </tr>

            <tr>
                <td><form:label path="lastName">Last name:</form:label></td>
                <td><form:input path="lastName"/><form:errors path="lastName" cssStyle="color:red;"
                                                              cssclass="error"/></td>
            </tr>

            <tr>
                <td><form:label path="password">Password:</form:label></td>
                <td><form:password path="password"/><form:errors path="password" cssStyle="color:red;"
                                                                 cssclass="error"/></td>
            </tr>

        </table>

        <div><label path="roles">Roles:</label></div>
        <div>
            <%--<table class="fixed_headers">--%>
                <%--<thead class="thead">--%>
                <%--<tr class="tr">--%>
                    <%--<th class="th">Role</th>--%>
                <%--</tr>--%>
                <%--</thead>--%>
                <%--<tbody class="tbody">--%>
                <%--<c:forEach items="${roles}" var="role">--%>
                    <%--<tr class="tr">--%>
                        <%--<td class="td">${role.name}</td>--%>
                    <%--</tr>--%>
                <%--</c:forEach>--%>
                <%--</tbody>--%>
            <%--</table>--%>

                <%--<form:select path="roles">--%>
                <%--<c:forEach items="${roles}" var="role">--%>
                <%--<form:option value="${role.roleId}">${role.name}</form:option>--%>
                <%--</c:forEach>--%>
                <%--</form:select>--%>

                <<c:forEach items="${user.roles}" varStatus="vs">
                    <div class="field">
                        <div class="label required">
                            <form:label path="roles[${vs.index}].name" cssErrorClass="invalid">Role Name</form:label>
                        </div>
                        <div class="input">
                            <form:input path="roles[${vs.index}].name" cssErrorClass="invalid "/>
                            <form:label path="roles[${vs.index}].name" cssErrorClass="icon invalid"/>
                            <form:errors path="roles[${vs.index}].name" cssClass="inline_invalid"/>
                        </div>
                    </div>
                    <form:hidden path="roles[${vs.index}].roleId"/>
                    <hr/>
                </c:forEach>

        </div>
        <div><label>Students:</label></div>
        <div>
            <table class="fixed_headers">
                <thead class="thead">
                <tr class="tr">
                    <th class="th">Last name</th>
                    <th class="th">First name</th>
                    <th class="th">Middle name</th>
                </tr>
                </thead>
                <tbody class="tbody">
                <c:forEach items="${students}" var="student">
                    <tr class="tr">
                        <td class="td">${student.lastName}</td>
                        <td class="td">${student.firstName}</td>
                        <td class="td">${student.middleName}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
                <%--<form:select path="students">--%>
                <%--<c:forEach items="${students}" var="student">--%>
                <%--<form:option--%>
                <%--value="${student.studentId}">${student.lastName} ${student.firstName} ${student.middleName}</form:option>--%>
                <%--</c:forEach>--%>
                <%--</form:select>--%>
        </div>

        <input type="submit" value="Save"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form:form>

</div>

<%--<div id="dialog-form" title="Create new user">--%>
<%--<p class="validateTips">All form fields are required.</p>--%>

<%--<form>--%>
<%--<fieldset>--%>
<%--<label for="name">Name</label>--%>
<%--<input type="text" name="name" id="name" value="Jane Smith" class="text ui-widget-content ui-corner-all">--%>
<%--<label for="email">Email</label>--%>
<%--<input type="text" name="email" id="email" value="jane@smith.com"--%>
<%--class="text ui-widget-content ui-corner-all">--%>
<%--<label for="password">Password</label>--%>
<%--<input type="password" name="password" id="password" value="xxxxxxx"--%>
<%--class="text ui-widget-content ui-corner-all">--%>
<%--<!-- Allow form submission with keyboard without duplicating the dialog button -->--%>
<%--<input type="submit" tabindex="-1" style="position:absolute; top:-1000px">--%>
<%--</fieldset>--%>
<%--</form>--%>
<%--</div>--%>
<%--<div id="users-contain" class="ui-widget">--%>
<%--<h1>Existing Users:</h1>--%>
<%--<table id="users" class="ui-widget ui-widget-content">--%>
<%--<thead>--%>
<%--<tr class="ui-widget-header ">--%>
<%--<th>Name</th>--%>
<%--<th>Email</th>--%>
<%--<th>Password</th>--%>
<%--</tr>--%>
<%--</thead>--%>
<%--<tbody>--%>
<%--<tr>--%>
<%--<td>John Doe</td>--%>
<%--<td>john.doe@example.com</td>--%>
<%--<td>johndoe1</td>--%>
<%--</tr>--%>
<%--</tbody>--%>
<%--</table>--%>
<%--</div>--%>
<%--<button id="create-user">Create new user</button>--%>

</body>
</html>
