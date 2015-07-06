<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Online control</title>
    <sec:authorize access="isAuthenticated()">
        <sec:authentication property="principal.id" var="userId"/>
        <sec:authentication property="principal.email" var="email"/>
    </sec:authorize>
    <spring:url value="/resources/frontend/css/reset.css" var="ResetCss"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="${ResetCss}" rel="stylesheet"/>
    <spring:url value="/resources/frontend/css/style.css" var="MainCss"/>
    <link href="${MainCss}" rel="stylesheet"/>
    <!--[if !IE 7]>
    <style type="text/css">
        #wrap {display:table;height:100%}
    </style>
    <![endif]-->

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
                                <sec:authorize access="!isAuthenticated()">
                                    <a href='<spring:url value="/login" htmlEscape="true"/>' role="button" class="button">Войти</a>
                                </sec:authorize>
                                <sec:authorize access="isAuthenticated()">
                                    <p class="login-name">Ваш логин: ${email}</p>

                                    <%--<p><a href='<spring:url value="/logout" htmlEscape="true"/>' role="button">Выйти</a></p>--%>
                                    <c:url var="logoutUrl" value="/logout"/>
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
            <div class="row clearfix">
                <div class="article article-first cols col-12">
                    <a href="#" class="link"><img src="resources/frontend/image/news.jpg" alt="news" class="img"></a>
                    <div class="article-part">
                        <div class="date">
                            Saturday, July 05, 2015
                        </div>
                        <h2 class="header"><a href="#" class="article-link">Lorem ipsum</a></h2>
                        <p class="text">
                            The Sindicatura General de la Nacion, an Argentine organistion responsible for auditing government initiatives, has published its report concerning the economic benefits generated by organisation of the Dakar. It announced that these direct and indirect benefits (stemming from increased economic activity), reached a total of US$ 279.5 million. The occupancy rate of hotels along the event's route was estimated to be 81%, with some towns and cities such as Jujuy, La Rioja and San Juan boasting a rate of 100%.
                        </p>
                    </div>
                </div>
            </div>
            <div class="row clearfix">
                <div class="article cols col-12">
                    <a href="#" class="link"><img src="resources/frontend/image/news.jpg" alt="news" class="img"></a>
                    <div class="article-part">
                        <div class="date">
                            Saturday, July 05, 2015
                        </div>
                        <h2 class="header"><a href="#" class="article-link">Lorem ipsum</a></h2>
                        <p class="text">
                            The Sindicatura General de la Nacion, an Argentine organistion responsible for auditing government initiatives, has published its report concerning the economic benefits generated by organisation of the Dakar. It announced that these direct and indirect benefits (stemming from increased economic activity), reached a total of US$ 279.5 million. The occupancy rate of hotels along the event's route was estimated to be 81%, with some towns and cities such as Jujuy, La Rioja and San Juan boasting a rate of 100%.
                        </p>
                    </div>
                </div>
            </div>
            <div class="row clearfix">
                <div class="article cols col-12">
                    <a href="#" class="link"><img src="resources/frontend/image/news.jpg" alt="news" class="img"></a>
                    <div class="article-part">
                        <div class="date">
                            Saturday, July 05, 2015
                        </div>
                        <h2 class="header"><a href="#" class="article-link">Lorem ipsum</a></h2>
                        <p class="text">
                            The Sindicatura General de la Nacion, an Argentine organistion responsible for auditing government initiatives, has published its report concerning the economic benefits generated by organisation of the Dakar. It announced that these direct and indirect benefits (stemming from increased economic activity), reached a total of US$ 279.5 million. The occupancy rate of hotels along the event's route was estimated to be 81%, with some towns and cities such as Jujuy, La Rioja and San Juan boasting a rate of 100%.
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="footer-index clearfix">
          <p class="footer-mainPage">work project &copy; 2015</p>
    </div>
</body>
</html>
