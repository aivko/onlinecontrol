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

    <c:choose>
        <c:when test="${shedule['new']}">
            <c:set var="method" value="post"/>
            <spring:url value="/shedules/new" htmlEscape="true" var="action"/>
        </c:when>
        <c:otherwise>
            <c:set var="method" value="put"/>
            <spring:url value="/shedules/${shedule.id}/edit" htmlEscape="true" var="action"/>
        </c:otherwise>
    </c:choose>

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
                <td><form:input path="date" id="date"/><form:errors path="date" cssStyle="color:red;"
                                                                                cssclass="error"/></td>
            </tr>
            <tr>
                <td><form:label path="period">Период:</form:label></td>
                <td>
                    <form:select path="period">
                        <form:option value="0" label="------Select a period------"/>
                        <c:forEach var="varperiod" items="${periods}">
                            <form:option value="${varperiod.id}"><c:out value="${varperiod}"/></form:option>
                        </c:forEach>
                    </form:select>
                    <form:errors path="period" cssStyle="color:red;" cssclass="error"/>
                </td>
            </tr>
            <tr>
                <td><form:label path="subject">Предмет:</form:label></td>
                <td>
                    <form:select path="subject">
                        <form:option value="0" label="------Select a subject------"/>
                        <c:forEach var="varsubject" items="${subjects}">
                            <form:option value="${varsubject.id}"><c:out value="${varsubject}"/></form:option>
                        </c:forEach>
                    </form:select>
                    <form:errors path="subject" cssStyle="color:red;" cssclass="error"/>
                </td>
            </tr>
            <tr>
                <td><form:label path="clazz">Класс:</form:label></td>
                <td>
                    <form:select path="clazz">
                        <form:option value="0" label="------Select a class------"/>
                        <c:forEach var="varclazz" items="${clazzes}">
                            <form:option value="${varclazz.id}"><c:out value="${varclazz}"/></form:option>
                        </c:forEach>
                    </form:select>
                    <form:errors path="clazz" cssStyle="color:red;" cssclass="error"/>
                </td>
            </tr>
            <tr>
                <td><form:label path="teacher">Преподаватель:</form:label></td>
                <td>
                    <form:select path="teacher">
                        <form:option value="0" label="------Select a teacher------"/>
                        <c:forEach var="varteacher" items="${teachers}">
                            <form:option value="${varteacher.id}"><c:out value="${varteacher.lastName} ${varteacher.firstName} ${varteacher.lastName}"/></form:option>
                        </c:forEach>
                    </form:select>
                    <form:errors path="teacher" cssStyle="color:red;" cssclass="error"/>
                </td>
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
