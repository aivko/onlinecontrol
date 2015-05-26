<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Изменение пароля</title>

    <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <spring:url value="/users/${user.userId}/changePassword" htmlEscape="true" var="action"/>

    <jsp:include page="../fragments/jQueryLib.jsp"/>

    <script type="text/javascript">
        function sendAjax() {

            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");

            passwordOld = $("#passwordOld").val();
            password = $("#password").val();
            passwordConfirm = $("#passwordConfirm").val();

            var json = {"passwordOld": passwordOld, "password": password, "passwordConfirm": passwordConfirm};
            $.ajax({
                url: "${action}",
                type: 'POST',
                dataType: 'json',
                data: JSON.stringify(json),
                contentType: 'application/json',
                mimeType: 'application/json',
                beforeSend: function(xhr){
                    xhr.setRequestHeader(header, token);
                },
                success: function (data) {
                    alert("passwordOld=" + data.passwordOld + " password=" + data.password + " passwordConfirm=" + data.passwordConfirm);
                },
                error: function (data, status, er) {
                    alert("error: " + data + " status: " + status + " er:" + er);
                }
            });
        }
    </script>

</head>

<body>

<fieldset>
    <div>
        <label for="passwordOld">Старый пароль:</label>
        <input id="passwordOld" name="passwordOld" size="20" maxlength="50" type="password"/>
    </div>
    <div>
        <label for="password">Новый пароль:</label>
        <input id="password" name="password" size="20" maxlength="50" type="password"/>
    </div>
    <div>
        <label for="passwordConfirm">Повторите новый пароль:</label>
        <input id="passwordConfirm" name="passwordConfirm" size="20" maxlength="50" type="password"/>
    </div>

    <input type='button' onclick='sendAjax()' name="save" value="Сохранить"/>
</fieldset>

</body>
</html>