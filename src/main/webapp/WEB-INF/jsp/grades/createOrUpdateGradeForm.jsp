<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <c:when test="${grade['new']}">
            <c:set var="method" value="post"/>
            <spring:url value="/shedules/${grade.shedule.id}/students/${grade.student.id}/grades/new" htmlEscape="true"
                        var="action"/>
        </c:when>
        <c:otherwise>
            <c:set var="method" value="put"/>
            <spring:url value="/shedules/${grade.shedule.id}/students/${grade.student.id}/grades/${grade.id}/edit"
                        htmlEscape="true" var="action"/>
        </c:otherwise>
    </c:choose>

    <jsp:include page="../fragments/jQueryLib.jsp"/>

    <jsp:include page="../fragments/datatablesLib.jsp"/>

</head>
<body>

<div id="wrapper-login">

    <h1>Событие</h1>

    <form:form modelAttribute="grade" method="${method}" action="${action}">

        <table class="horiz">

            <tr>
                <td>
                    <label>Расписание:</label>
                </td>
                <td>
                    <fmt:formatDate pattern="dd.MM.yyyy" value="${grade.shedule.date}"/>
                    <c:out value="| ${grade.shedule.period}"/><br/>
                    <c:out value="${grade.shedule.subject}"/> <c:out value="| ${grade.shedule.teacher}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label>Студент:</label>
                </td>
                <td>
                    <c:out value="${grade.student.lastName} ${grade.student.firstName} ${grade.student.middleName}"/>
                </td>
            </tr>
            <tr>
                <td><form:label path="task">Задание:</form:label></td>
                <td><form:input path="task" id="task"/><form:errors path="task" cssStyle="color:red;"
                                                                    cssclass="error"/></td>
            </tr>
            <tr>
                <td><form:label path="mark">Оценка:</form:label></td>
                <td><form:input path="mark" id="mark"/><form:errors path="mark" cssStyle="color:red;"
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
