<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Online control</title>
</head>
<body>
<div
        class="fb-like"
        data-share="true"
        data-width="450"
        data-show-faces="true">
</div>
<div>
    <h1>Start page</h1>

    <%--<h2>Find students</h2>--%>
    <%--<a href='<spring:url value="/students/find" htmlEscape="true"/>'>Find</a>--%>
    <br>
    <a href='<spring:url value="/auth" htmlEscape="true"/>'>Authorization</a>
</div>

<script>
    window.fbAsyncInit = function() {
        FB.init({
            appId      : '779752082073743',
            xfbml      : true,
            version    : 'v2.2'
        });
    };

    (function(d, s, id){
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) {return;}
        js = d.createElement(s); js.id = id;
        js.src = "//connect.facebook.net/en_US/sdk.js";
        fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));
</script>
</body>
</html>
