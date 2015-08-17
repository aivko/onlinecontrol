<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>User account</title>

    <spring:url value="/resources/frontend/css/reset.css" var="ResetCss"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link href="${ResetCss}" rel="stylesheet"/>
    <spring:url value="/resources/frontend/css/style.css" var="MainCss"/>
    <link href="${MainCss}" rel="stylesheet"/>
</head>
<body>
<div class="clearfix">
    <header class="index-header">
        <div class="container">
            <div class="header-line clearfix row">
                <div class="cols col-2">
                    <img src="<spring:url value="/resources/frontend/image/logo.jpg"/>" alt="logo" class="image"/>
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
                            <a href='<spring:url value="/users/${userId}/account" htmlEscape="true"/>' role="button"
                               class="account-name">Account</a>
                        </sec:authorize>
                        <sec:authorize access="!isAuthenticated()">
                            <a href='<spring:url value="/login" htmlEscape="true"/>' role="button"
                               class="button">Войти</a>
                        </sec:authorize>
                        <sec:authorize access="isAuthenticated()">
                            <p class="login-name">Ваш логин: ${user.email}</p>

                            <%--<p><a href='<spring:url value="/logout" htmlEscape="true"/>' role="button">Выйти</a></p>--%>
                            <spring:url var="logoutUrl" value="/logout"/>
                            <form class="logout" action="${logoutUrl}" method="post">
                                <input class="buttonLogout" type="submit" value="Выход"/>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </form>
                        </sec:authorize>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_OPERATOR')">
        <div class="userAccount-block">
            <div class="userAccount-blockSize"><a href='<spring:url value="/users/new" htmlEscape="true"/>'
                                                  role="button" class="userAccount-link">Добавить пользователя</a></div>
        </div>
        <div class="userAccount-block">
            <div class="userAccount-blockSize"><a href='<spring:url value="/users" htmlEscape="true"/>' role="button"
                                                  class="userAccount-link">Список пользователей</a></div>
        </div>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_OPERATOR')">
        <div class="userAccount-block">
            <div class="userAccount-blockSize"><a href='<spring:url value="/parents/new" htmlEscape="true"/>'
                                                  role="button" class="userAccount-link">Добавить родителя</a></div>
        </div>
        <div class="userAccount-block">
            <div class="userAccount-blockSize"><a href='<spring:url value="/parents" htmlEscape="true"/>' role="button"
                                                  class="userAccount-link">Список родителей</a></div>
        </div>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <div class="userAccount-block">
            <div class="userAccount-blockSize"><a href='<spring:url value="/roles/new" htmlEscape="true"/>'
                                                  role="button" class="userAccount-link">Добавить роль</a></div>
        </div>
        <div class="userAccount-block">
            <div class="userAccount-blockSize"><a href='<spring:url value="/roles" htmlEscape="true"/>' role="button"
                                                  class="userAccount-link">Список ролей</a></div>
        </div>
    </sec:authorize>
    <div class="userAccount-block">
        <div class="userAccount-blockSize"><a href='<spring:url value="/students/find" htmlEscape="true"/>'
                                              class="userAccount-link">Найти студента</a></div>
    </div>
    <sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_OPERATOR')">
        <div class="userAccount-block">
            <div class="userAccount-blockSize"><a href='<spring:url value="/students/new" htmlEscape="true"/>'
                                                  role="button" class="userAccount-link">Добавить студента</a></div>
        </div>
        <div class="userAccount-block">
            <div class="userAccount-blockSize"><a href='<spring:url value="/students" htmlEscape="true"/>' role="button"
                                                  class="userAccount-link">Список студентов</a></div>
        </div>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_OPERATOR')">
        <div class="userAccount-block">
            <div class="userAccount-blockSize"><a href='<spring:url value="/classes/new" htmlEscape="true"/>'
                                                  role="button" class="userAccount-link">Добавить класс</a></div>
        </div>
        <div class="userAccount-block">
            <div class="userAccount-blockSize"><a href='<spring:url value="/classes" htmlEscape="true"/>' role="button"
                                                  class="userAccount-link">Список классов</a></div>
        </div>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_OPERATOR')">
        <div class="userAccount-block">
            <div class="userAccount-blockSize"><a href='<spring:url value="/news/new" htmlEscape="true"/>' role="button"
                                                  class="userAccount-link">Добавить новость</a></div>
        </div>
        <div class="userAccount-block">
            <div class="userAccount-blockSize"><a href='<spring:url value="/news" htmlEscape="true"/>' role="button"
                                                  class="userAccount-link">Список новостей</a></div>
        </div>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_OPERATOR')">
        <div class="userAccount-block">
            <div class="userAccount-blockSize"><a href='<spring:url value="/shedules/constructor" htmlEscape="true"/>'
                                                  role="button" class="userAccount-link">Ввести расписание по
                шаблону</a></div>
        </div>
        <div class="userAccount-block">
            <div class="userAccount-blockSize"><a href='<spring:url value="/shedules/new" htmlEscape="true"/>'
                                                  role="button" class="userAccount-link">Ввести событие</a></div>
        </div>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_USER')">
        <div class="userAccount-block">
            <div class="userAccount-blockSize"><a
                    href='<spring:url value="/shedules/studentShedule" htmlEscape="true"/>' role="button"
                    class="userAccount-link">Посмотреть расписание</a></div>
        </div>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_OPERATOR') or hasRole('ROLE_TEACHER')">
        <div class="userAccount-block">
            <div class="userAccount-blockSize"><a
                    href='<spring:url value="/shedules/studentJournal" htmlEscape="true"/>' role="button"
                    class="userAccount-link">Посмотреть журнал</a></div>
        </div>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_USER')">
        <div class="userAccount-block">
            <div class="userAccount-blockSize"><a
                    href='<spring:url value="/users/${user.id}/changePassword" htmlEscape="true"/>'
                    class="userAccount-link">Изменить пароль</a></div>
        </div>
    </sec:authorize>
</div>

</body>

</html>