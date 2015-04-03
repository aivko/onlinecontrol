<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<html lang="en">

<body>
<div>

    <h2>Find students</h2>

</div>

<form:form modelAttribute="student" action="/students" method="post">

    <div>
        <form:label path="lastName">Введите фамилию:</form:label>
        <form:input path="lastName"/>
    </div>

    <input type="submit" name="submit" value="Найти"/>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form:form>

</body>

</html>