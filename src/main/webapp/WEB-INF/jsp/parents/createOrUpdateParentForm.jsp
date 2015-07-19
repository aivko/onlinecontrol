<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Добавление родителя</title>
    <spring:url value="/resources/frontend/css/reset.css" var="ResetCss"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="${ResetCss}" rel="stylesheet"/>
    <spring:url value="/resources/frontend/css/style.css" var="MainCss"/>
    <link href="${MainCss}" rel="stylesheet"/>
    <c:choose>
        <c:when test="${parent['new']}">
            <c:set var="method" value="post"/>
            <spring:url value="/parents/new" htmlEscape="true" var="action"/>
        </c:when>
        <c:otherwise>
            <c:set var="method" value="put"/>
            <spring:url value="/parents/${parent.id}/edit" htmlEscape="true" var="action"/>
        </c:otherwise>
    </c:choose>
    <jsp:include page="../fragments/jQueryLib.jsp"/>
    <script>
        $(function () {
            $("#birthDate").datepicker({dateFormat: 'dd.mm.yy'});
        });
    </script>
</head>
<body>
    <div class="main-content clearfix">
        <div class="wrapper">
            <header class="index-header">
                <div class="container">
                    <div class="header-line clearfix row">
                        <div class="cols col-2">
                            <img src="<spring:url value="/resources/frontend/image/logo.jpg"/>" alt="logo" class="image" />
                        </div>
                        <div class="header-button row">
                            <ul class="header-list cols col-8">
                                <li class="header-item">
                                    <a href="#">О нас</a>
                                </li>
                                <li class="header-item">
                                    <a href="#">Информация для родителей</a>
                                </li>
                                <li class="header-item">
                                    <a href="#">Технические характеристики</a>
                                </li>
                            </ul>
                            <div class="auth-button cols col-2">
                                <sec:authorize access="isAuthenticated()">
                                    <a href='<spring:url value="/users/${userId}/account" htmlEscape="true"/>' role="button" class="account-name">Account</a>
                                </sec:authorize>
                                <sec:authorize access="isAuthenticated()">
                                    <p class="login-name">Ваш логин: ${email}</p>

                                    <%--<p><a href='<spring:url value="/logout" htmlEscape="true"/>' role="button">Выйти</a></p>--%>
                                    <spring:url var="logoutUrl" value="/logout"/>
                                    <form class="logout" action="${logoutUrl}" method="post">
                                        <input class="buttonLogout" type="submit" value="Выход" />
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    </form>
                                </sec:authorize>
                            </div>
                        </div>
                    </div>
                </div>
            </header>
            <div id="wrapper-login">
               <form:form modelAttribute="parent" method="${method}" action="${action}" class="form-login">
                    <h2 class="autor-login">Родитель</h2>
                        <div class="data-author">
                            <form:label path="lastName" class="data-user">Фамилия:</form:label>
                            <form:input path="lastName" class="data-info"/>
                            <form:errors path="lastName" cssStyle="color:red;" cssclass="error"/>
                        </div>
                        <div class="data-author">
                           <form:label path="firstName" class="data-user">Имя:</form:label></td>
                           <form:input path="firstName" class="data-info"/>
                            <form:errors path="firstName" cssStyle="color:red;" cssclass="error"/>
                        </div>
                        <div class="data-author">
                           <form:label path="middleName" class="data-user">Отчество:</form:label>
                           <form:input path="middleName" class="data-info"/>
                            <form:errors path="middleName" cssStyle="color:red;" cssclass="error"/>
                        </div>
                        <div class="data-author">
                            <form:label path="dateOfBirth" class="data-user">Дата рождения:</form:label>
                            <form:input path="dateOfBirth" id="birthDate" class="data-info"/>
                            <form:errors path="dateOfBirth" cssStyle="color:red;" cssclass="error"/>
                        </div>
                        <div class="data-author">
                            <p><form:label path="gender">Пол:</form:label></p>
                            <form:radiobutton path="gender" value="MALE"/><span>Мужской</span>
                            <form:radiobutton path="gender" value="FEMALE"/><span>Женский</span>
                            <form:errors path="gender" cssStyle="color:red;" cssclass="error"/>
                        </div>
                        <div class="data-author">
                            <form:label path="user">Пользователь:</form:label>
                                <form:select path="user" class="data-info">
                                    <form:option value="0" label="Выбор пользователя" />
                                    <form:options items="${users}" itemValue="id" itemLabel="email"/>
                                </form:select>
                                <form:errors path="user" cssStyle="color:red;" cssclass="error"/>
                        </div>
                    <%--<div id="users-contain" class="ui-widget">--%>
                        <%--<h1>Roles:</h1>--%>
                        <%--<table id="roles" class="ui-widget ui-widget-content">--%>
                            <%--<thead>--%>
                            <%--<tr class="ui-widget-header ">--%>
                                <%--<th>Name</th>--%>
                                <%--<th>Description</th>--%>
                            <%--</tr>--%>
                            <%--</thead>--%>
                            <%--<tbody>--%>
                            <%--<c:forEach items="${user.roles}" varStatus="vs">--%>
                                <%--<tr>--%>
                                    <%--<td>${user.roles[vs.index].name}</td>--%>
                                    <%--<td>${user.roles[vs.index].description}</td>--%>
                                    <%--<form:hidden path="roles[${vs.index}].roleId"/>--%>
                                    <%--<form:hidden path="roles[${vs.index}].name"/>--%>
                                    <%--<form:hidden path="roles[${vs.index}].description"/>--%>
                                <%--</tr>--%>
                            <%--</c:forEach>--%>
                            <%--</tbody>--%>
                        <%--</table>--%>
                    <%--</div>--%>
                    <%--<button id="add-role">Add role</button>--%>

                    <%--<hr>--%>

                    <%--<div id="users-contain" class="ui-widget">--%>
                    <%--<h1>Students:</h1>--%>
                    <%--<table id="students" class="ui-widget ui-widget-content">--%>
                    <%--<thead>--%>
                    <%--<tr class="ui-widget-header">--%>
                    <%--<th>Last name</th>--%>
                    <%--<th>First name</th>--%>
                    <%--<th>Middle name</th>--%>
                    <%--</tr>--%>
                    <%--</thead>--%>
                    <%--<tbody>--%>
                    <%--<c:forEach items="${user.students}" varStatus="vs">--%>
                    <%--<tr>--%>
                    <%--<td>${user.students[vs.index].lastName}</td>--%>
                    <%--<td>${user.students[vs.index].firstName}</td>--%>
                    <%--<td>${user.students[vs.index].middleName}</td>--%>
                    <%--<form:hidden path="students[${vs.index}].studentId"/>--%>
                    <%--<form:hidden path="students[${vs.index}].lastName"/>--%>
                    <%--<form:hidden path="students[${vs.index}].firstName"/>--%>
                    <%--<form:hidden path="students[${vs.index}].middleName"/>--%>
                    <%--</tr>--%>
                    <%--</c:forEach>--%>
                    <%--</tbody>--%>
                    <%--</table>--%>
                    <%--</div>--%>
                    <%--<button class="add-student">Add student</button>--%>
                    <input type="submit" value="Сохранить" class="submit-button"/>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form:form>
            </div>
        </div>
    </div>
    <div class="footer-index clearfix">
            <p class="footer-mainPage">work project &copy; 2015</p>
    </div>
</body>
</html>
