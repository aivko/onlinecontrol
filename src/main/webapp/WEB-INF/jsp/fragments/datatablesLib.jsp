<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url value="/webjars/datatables/1.10.7/js/jquery.dataTables.js" var="dataTablesJs"/>
<script src="${dataTablesJs}"></script>

<spring:url value="/webjars/datatables/1.10.7/css/jquery.dataTables.css" var="dataTablesCss"/>
<link href="${dataTablesCss}" rel="stylesheet"/>
