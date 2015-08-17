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

    <%--<meta name="_csrf" content="${_csrf.token}"/>--%>
    <%--<!-- default header name is X-CSRF-TOKEN -->--%>
    <%--<meta name="_csrf_header" content="${_csrf.headerName}"/>--%>

    <c:set var="method" value="put"/>
    <spring:url value="/shedules/${shedule.id}/edit" htmlEscape="true" var="action"/>

    <jsp:include page="../fragments/jQueryLib.jsp"/>

    <jsp:include page="../fragments/datatablesLib.jsp"/>

    <script>
        $(function () {
            $("#date").datepicker({dateFormat: 'dd.mm.yy'});
        });
    </script>

</head>
<body>

<div id="wrapper-login">

    <h1>Событие</h1>

    <form:form modelAttribute="shedule" method="${method}" action="${action}">

        <table class="horiz">

            <tr>
                <td><form:label path="date">Дата события:</form:label></td>
                <td><form:label path="date" id="date"/><form:errors path="date" cssStyle="color:red;"
                                                                    cssclass="error"/></td>
            </tr>
            <tr>
                <td><form:label path="period">Период:</form:label></td>
                <td><form:label path="period" id="period"/><form:errors path="period" cssStyle="color:red;"
                                                                    cssclass="error"/></td>
            </tr>
            <tr>
                <td><form:label path="subject">Предмет:</form:label></td>
                <td><form:label path="subject" id="subject"/><form:errors path="subject" cssStyle="color:red;"
                                                                        cssclass="error"/></td>
            </tr>
            <tr>
                <td><form:label path="clazz">Класс:</form:label></td>
                <td><form:label path="clazz" id="clazz"/><form:errors path="clazz" cssStyle="color:red;"
                                                                        cssclass="error"/></td>
            </tr>
            <tr>
                <td><form:label path="teacher">Преподаватель:</form:label></td>
                <td><form:label path="teacher" id="teacher"/><form:errors path="teacher" cssStyle="color:red;"
                                                                        cssclass="error"/></td>
            </tr>
            <tr>
                <td><form:label path="job">Домашнее задание:</form:label></td>
                <td><form:input path="job" id="job"/><form:errors path="job" cssStyle="color:red;"
                                                                  cssclass="error"/></td>
            </tr>

        </table>

        <br>

        <input id="submit" type="submit" value="Сохранить"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form:form>

</div>

</body>
</html>
