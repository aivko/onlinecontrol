<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--<spring:url value="/webjars/bootstrap/3.3.4/css/bootstrap.min.css" var="bootstrapCss"/>--%>
<%--<link href="${bootstrapCss}" rel="stylesheet"/>--%>
<%--<spring:url value="/webjars/bootstrap/3.3.4/css/bootstrap-table.css" var="bootstrapTableCss"/>--%>
<%--<link href="${bootstrapTableCss}" rel="stylesheet"/>--%>
<%--<spring:url value="/webjars/bootstrap/3.3.4/js/bootstrap.js" var="bootstrapJs"/>--%>
<%--<script src="${bootstrapJs}"></script>--%>
<%--<spring:url value="/webjars/bootstrap/3.3.4/js/bootstrap-table.js" var="bootstrapTableJs"/>--%>
<%--<script src="${bootstrapTableJs}"></script>--%>

<spring:url value="/resources/css/jquery-ui.css" var="jQueryCss"/>
<link href="${jQueryCss}" rel="stylesheet"/>

<spring:url value="/webjars/jquery/2.1.3/jquery.js" var="jQuery"/>
<script src="${jQuery}"></script>

<spring:url value="/resources/js/jquery-ui.js" var="jQueryUi"/>
<script src="${jQueryUi}"></script>
