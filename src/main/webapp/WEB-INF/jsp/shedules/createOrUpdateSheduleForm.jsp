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

    <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

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

        $(document).ready(function () {

            var table = $('#shedule').DataTable();
            var counter = 1;

//            $('button').click(function () {
//                var data = table.$('input, select').serialize();
//                alert(
//                        "The following data would have been submitted to the server: \n\n" +
//                        data.substr(0, 120) + '...'
//                );
//                return false;
//            });

            $('#addRow').on( 'click', function () {
                table.row.add( [
                    counter +'.1',
                    counter +'.2',
                    counter +'.3',
                    counter +'.4'
                ] ).draw();

                counter++;
            } );

        });

        $(function () {
            $("#startDate").datepicker({
                defaultDate: "+1w",
                changeMonth: true,
                dateFormat: 'dd.mm.yy',
                numberOfMonths: 3,
                onClose: function (selectedDate) {
                    $("#endDate").datepicker("option", "minDate", selectedDate);
                }
            });
            $("#endDate").datepicker({
                defaultDate: "+1w",
                changeMonth: true,
                dateFormat: 'dd.mm.yy',
                numberOfMonths: 3,
                onClose: function (selectedDate) {
                    $("#startDate").datepicker("option", "maxDate", selectedDate);
                }
            });
        });
    </script>

</head>
<body>

<div id="wrapper-login">

    <h1>Расписание</h1>

    <form:form method="${method}" action="${action}">

        <div>
            <label for="startDate">Дата начала</label>
            <input type="text" id="startDate"/>
            <label for="endDate">Дата окончания</label>
            <input type="text" id="endDate"/>
        </div>

        <br>

        <div>
            <label>Класс</label>
            <select>
                <option value="0"><c:out value="------Select a class------"/></option>
                <c:forEach var="varclazz" items="${clazzes}">
                    <option value="${varclazz.id}"><c:out value="${varclazz.number} - ${varclazz.letter}"/></option>
                </c:forEach>
            </select>
        </div>

        <br>

        <table id="shedule" class="display" cellspacing="0" width="100%">
            <thead>
            <tr>
                <th>День недели</th>
                <th>Время урока</th>
                <th>Предмет</th>
                <th>Преподаватель</th>
            </tr>
            </thead>

            <tfoot>
            <tr>
                <th>День недели</th>
                <th>Время урока</th>
                <th>Предмет</th>
                <th>Преподаватель</th>
            </tr>
            </tfoot>

            <tbody>
            <tr id="row">
                <td>
                    <select>
                        <option value="0"><c:out value="-------День недели------"/></option>
                        <c:forEach var="vardayOfTheWeek" items="${daysOfTheWeek}">
                            <option value="${vardayOfTheWeekv.id}"><c:out value="${vardayOfTheWeek.name}"/></option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <select>
                        <option value="0"><c:out value="-------Время урока------"/></option>
                        <c:forEach var="varperiod" items="${periods}">
                            <option value="${varperiod.id}"><c:out value="${varperiod}"/></option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <select>
                        <option value="0"><c:out value="-------Предмет------"/></option>
                        <c:forEach var="varsubject" items="${subjects}">
                            <option value="${varsubject.id}"><c:out value="${varsubject.name}"/></option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <select>
                        <option value="0"><c:out value="-------Преподаватель------"/></option>
                        <c:forEach var="varteacher" items="${teachers}">
                            <option value="${varteacher.id}"><c:out value="${varteacher.lastName} ${varteacher.firstName} ${varteacher.middleName}"/></option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            </tbody>
        </table>
        <div>
            <button id="addRow">Добавить строку</button>
        </div>
        <hr/>
        <input type="submit" value="Сохранить"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form:form>

</div>

</body>
</html>
