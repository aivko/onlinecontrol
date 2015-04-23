<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url value="/resources/css/jquery-ui.css" var="jQueryCss"/>
<link href="${jQueryCss}" rel="stylesheet"/>

<spring:url value="/resources/js/jquery.js" var="jQuery"/>
<script src="${jQuery}"></script>

<spring:url value="/resources/js/jquery-ui.js" var="jQueryUi"/>
<script src="${jQueryUi}"></script>
