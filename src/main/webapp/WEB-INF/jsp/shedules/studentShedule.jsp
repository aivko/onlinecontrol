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

    <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <jsp:include page="../fragments/jQueryLib.jsp"/>

</head>
<body>

<div>

    <h1>Просмотр расписания</h1>

    <div>
        <c:forEach var="entry" items="${shedules}">
            <c:forEach var="varShedule" items="${entry.value}">
                ${varShedule.period}
            </c:forEach>
        </c:forEach>
    </div>

</div>

</body>
</html>
