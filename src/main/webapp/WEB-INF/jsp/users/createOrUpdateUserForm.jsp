<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Добавление пользователя</title>
    <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <spring:url value="/resources/frontend/css/reset.css" var="ResetCss"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="${ResetCss}" rel="stylesheet"/>
    <spring:url value="/resources/frontend/css/style.css" var="MainCss"/>
    <link href="${MainCss}" rel="stylesheet"/>
    <spring:url value="/users/checkEmail" htmlEscape="true" var="checkEmail"/>
    <c:choose>
        <c:when test="${user['new']}">
            <c:set var="method" value="post"/>
            <spring:url value="/users/new" htmlEscape="true" var="action"/>
        </c:when>
        <c:otherwise>
            <c:set var="method" value="put"/>
            <spring:url value="/users/${user.id}/edit" htmlEscape="true" var="action"/>
        </c:otherwise>
    </c:choose>

    <jsp:include page="../fragments/jQueryLib.jsp"/>

    <script type="text/javascript">
        function existEmail() {

            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");

            email = $("#email").val();

            var json = {"email": email};
            $.ajax({
                url: "${checkEmail}",
                type: 'POST',
                dataType: 'json',
                data: JSON.stringify(json),
                contentType: 'application/json',
                mimeType: 'application/json',
                beforeSend: function(xhr){
                    xhr.setRequestHeader(header, token);
                },
                success: function (data) {
                    if(data.result == "true"){
                        $("#errorEmail").text("Этот адресс существует, выберите другой.");
                    }else{
                        $("#errorEmail").text("");
                    }
                },
                error: function (data, status, er) {
                    alert("error: " + data + " status: " + status + " er:" + er);
                }
            });
        }
    </script>

</head>
<body>
    <div class="main-content clearfix">
        <div class="wrapper">
            <header class="index-header">
                <div class="container">
                    <div class="header-line clearfix row">
                        <img src="<spring:url value="/resources/frontend/image/logo.jpg"/>" alt="logo" class="image" />
                    </div>
                </div>
            </header>
        <div id="wrapper-login">
            <form:form modelAttribute="user" method="${method}" action="${action}" class="form-login">
                <h2 class="autor-login">Пользователь</h2>
                <div class="horiz">
                    <div class="data-author">
                        <form:label path="email" class="data-user">Email:</form:label>
                        <form:input path="email" id="email" onchange="existEmail()" class="data-info"/>
                        <form:errors path="email" cssStyle="color:red;" cssclass="error"/>
                        <span id="errorEmail"></span>
                    </div>
                    <c:choose>
                        <c:when test="${user['new']}">
                            <div class="data-author">
                                <form:label path="password" class="data-user">Пароль:</form:label>
                                <form:password path="password" class="data-info"/>
                                <form:errors path="password" cssStyle="color:red;" cssclass="error"/>
                            </div>
                            <div class="data-author">
                                <form:label path="passwordConfirm" class="data-user">Повторите пароль:</form:label>
                                <form:password path="passwordConfirm" class="data-info"/>
                                <form:errors path="passwordConfirm" cssStyle="color:red;" cssclass="error"/>
                            </div>
                        </c:when>
                    </c:choose>
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
                <input class="submit-button" type="submit" value="Сохранить"/>
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
