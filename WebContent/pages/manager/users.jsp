<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty pageScope.language ? pageScope.language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="com.java.task11.i18n.text"/>

<html lang="${language}">
<head>
    <title>Users table</title>
    <jsp:include page="header.jsp"/>
</head>

<body>
<!-- CONTENT -->
<div class="container-fluid">
    <div class="row">
        <div class="table-responsive tile col-md-10 col-sm-12 col-xs-12 col-md-offset-1">
            <table class="table table-striped table-hover">
                <!-- Table -->
                <thead>
                <tr>

                    <th>#</th>
                    <th><fmt:message key="user.firstName"/> <fmt:message key="user.lastName"/></th>
                    <th><fmt:message key="user.position"/></th>
                    <th><fmt:message key="user.email"/></th>
                    <th><fmt:message key="user.salaryRate"/></th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="user" items="${requestScope.usersonly}">
                <tr>

                    <td>${user.id}</td>
                    <td>${user.firstName} ${user.lastName}</td>
                    <td>${user.position}</td>
                    <td>${user.email}</td>
                    <td>${user.salaryRate}</td>

                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>


</div>

<jsp:include page="../parts/scripts.jsp"/>
</body>
</html>
