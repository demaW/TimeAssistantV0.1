<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="com.java.task11.i18n.text"/>

<html lang="${language}">
<head>
    <title>Team info</title>
    <jsp:include page="header.jsp"/>
</head>

<body>
<div class="container-fluid team-info-table">
    <div class="row">
        <div class="col-md-10 col-sm-12 col-xs-12 col-md-offset-1">
            <h2>${requestScope.teamProject.projectName}</h2>
            <h4>${requestScope.teamProject.description}</h4>
            <h7>${requestScope.teamProject.notes}</h7>
            <br/><br/>

            <div class="row">
                <a class="btn btn-info" href="${pageContext.request.contextPath}/manager/teamsTable">
                    <fmt:message key="teamsTable.title"/>
                </a>
            </div>
        </div>
    </div>
    <br/>

    <form action="${pageContext.request.contextPath}/manager/teamsTable" method="post">
        <div class="row">
            <div class="table-responsive tile col-md-10 col-sm-12 col-xs-12 col-md-offset-1">
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th></th>
                        <th>#</th>
                        <th><fmt:message key="user.firstName"/></th>
                        <th><fmt:message key="user.lastName"/></th>
                        <th><fmt:message key="user.email"/></th>
                        <th><fmt:message key="user.position"/></th>
                        <th><fmt:message key="user.role"/></th>
                        <th><fmt:message key="user.salaryRate"/></th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${requestScope.userList}" var="teamMember">
                        <tr>
                            <td>
                                <label class="checkbox" for="checkbox${teamMember.id}">
                                    <input type="checkbox" name="checkedMembers" value="${teamMember.id}"
                                           id="checkbox${teamMember.id}" data-toggle="checkbox">
                                </label>
                            </td>
                            <td>${teamMember.id}</td>
                            <td>${teamMember.firstName}</td>
                            <td>${teamMember.lastName}</td>
                            <td>${teamMember.email}</td>
                            <td>${teamMember.position}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${teamMember.roleId == '1'}">
                                        user
                                    </c:when>
                                    <c:when test="${teamMember.roleId == '2'}">
                                        manager
                                    </c:when>
                                    <c:otherwise>
                                        admin
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${teamMember.salaryRate}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row">
            <div class="col-md-7 col-sm-7 col-xs-6 col-md-offset-1">
                <button class="btn btn-danger" name="delete"
                        type="submit" value="Delete">
                    <fmt:message key="button.delete"/>
                </button>
                <%--<button class="btn btn-primary" name="addMember"
                        type="submit" value="addMember">
                    <fmt:message key="button.addMember"/>
                </button>--%>
            </div>
        </div>
    </form>
    <jsp:include page="../parts/scripts.jsp"/>
</body>
</html>
