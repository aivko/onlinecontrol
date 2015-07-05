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
        <c:when test="${news['new']}">
            <c:set var="method" value="post"/>
            <spring:url value="/news/new" htmlEscape="true" var="action"/>
        </c:when>
        <c:otherwise>
            <c:set var="method" value="put"/>
            <spring:url value="/news/${news.id}/edit" htmlEscape="true" var="action"/>
        </c:otherwise>
    </c:choose>

    <jsp:include page="../fragments/jQueryLib.jsp"/>

    <script>
        $(function () {
            $("#date").datepicker({dateFormat: 'dd.mm.yy'});
        });
    </script>

</head>
<body>

<div id="wrapper-login">

    <h1>News</h1>

    <form:form modelAttribute="news" method="${method}" action="${action}">
        <table class="horiz">
            <tr>
                <td><form:label path="date">Дата новости:</form:label></td>
                <td><form:input path="date" id="date"/><form:errors path="date" cssStyle="color:red;"
                                                                                cssclass="error"/></td>
            </tr>

            <tr>
                <td><form:label path="topic">Описание новости:</form:label></td>
                <td><form:input path="topic"/><form:errors path="topic" cssStyle="color:red;"
                                                               cssclass="error"/></td>
            </tr>

            <tr>
                <td><form:label path="text">Детальное описание новости:</form:label></td>
                <td><form:input path="text"/><form:errors path="text" cssStyle="color:red;"
                                                               cssclass="error"/></td>
            </tr>

        </table>

        <hr>

        <input type="submit" value="Сохранить"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form:form>

</div>

</body>
</html>
