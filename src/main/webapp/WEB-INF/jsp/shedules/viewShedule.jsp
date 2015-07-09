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

    <spring:url value="/shedules/generateReport" htmlEscape="true" var="generateReport"/>

    <jsp:include page="../fragments/jQueryLib.jsp"/>

    <script type="text/javascript">
        $(function () {
            $("#startDate").datepicker({dateFormat: 'dd.mm.yy'});
        });

        function testReport() {

            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");

            var startDate = $("#startDate").val();

            var json = {"startDate": '111'};

            $.ajax({
                url: "${generateReport}",
                type: 'POST',
                dataType: 'json',
                data: JSON.stringify(json),
                contentType: 'application/json',
                mimeType: 'application/json',
                beforeSend: function (xhr) {
                    xhr.setRequestHeader(header, token);
                },
                success: function (data) {
                    alert(data);
                },
                error: function (data, status, er) {
                    alert("Не удалось сформировать отчет");
                }
            });
        }

    </script>

</head>
<body>

<divd>

    <h1>Просмотр расписания</h1>

    <div id="content">
        <div>
            Start date: <input type="text" id="startDate">
        </div>
        <button onclick="testReport()">Test</button>
    </div>

</divd>

</body>
</html>
