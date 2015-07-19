<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="en">
<head>
    <title>Список пользователей</title>
    <spring:url value="/resources/frontend/css/reset.css" var="ResetCss"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="${ResetCss}" rel="stylesheet"/>
    <spring:url value="/resources/frontend/css/style.css" var="MainCss"/>
    <link href="${MainCss}" rel="stylesheet"/>
    <spring:url value="/webjars/jquery/2.1.3/jquery.js" var="jQuery"/>
    <script src="${jQuery}"></script>

    <jsp:include page="../fragments/datatablesLib.jsp"/>

    <script>
        $(document).ready(function() {
            var table = $('#users').DataTable();
        } );
    </script>

</head>
<body>
    <div class="main-content clearfix">
        <div class="wrapper">
            <header class="index-header">
                <div class="container">
                    <div class="header-line clearfix row">
                        <div class="cols col-2">
                            <img src="resources/frontend/image/logo.jpg" alt="logo" class="image" />
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
            <h2 class="users-wrapperHeader">Пользователи</h2>
            <table id="users" class="display" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th>Email</th>
                    <th>Роли</th>
                    <th>Редактировать</th>
                    <th>Детали</th>
                    <th>Удалить</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>
                            <c:out value="${user.email}"/>
                        </td>
                        <td>
                            <c:forEach var="role" items="${user.roles}">
                                <c:out value="${role.name}"/><br>
                            </c:forEach>
                        </td>
                        <td>
                            <a href='<spring:url value="/users/${user.id}/edit" htmlEscape="true"/>' role="button">Редактировать пользователя</a></p>
                        </td>
                        <td>
                            <a href='<spring:url value="/users/${user.id}" htmlEscape="true"/>' role="button">Детальное описание</a></p>
                        </td>
                        <td>
                            <spring:url value="/users/${user.id}/delete" htmlEscape="true" var="action"/>
                            <form:form method="delete" action="${action}">
                                <p class="submit"><input type="submit" value="Удалить пользователя"/></p>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </form:form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="footer-index clearfix">
        <p class="footer-mainPage">work project &copy; 2015</p>
    </div>
</body>
</html>