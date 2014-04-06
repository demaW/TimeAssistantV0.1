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
    <title>Teams table</title>
    <jsp:include page="header.jsp" />
</head>

<body>
    <%--CONTENT--%>
    <%--<form action="${pageContext.request.contextPath}/manager/teamsTable" method="post">--%>
        <div class="row">
            <div class="table-responsive tile col-md-10 col-sm-12 col-xs-12 col-md-offset-1">
                <h1><fmt:message key="teamsTable.title"/></h1>
                <br/>
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th><fmt:message key="project.name"/></th>
                        <th><fmt:message key="project.description"/></th>
                        <th><fmt:message key="team.info"/></th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${requestScope.projectsList}" var="project">
                        <tr>
                            <td>${project.id}</td>
                            <td>${project.projectName}</td>
                            <td>${project.description}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/manager/teamsTable?project_id=${project.id}"
                                   class="btn btn-info btn-xs"><fmt:message key="team.info"/>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    <%--</form>--%>
    <jsp:include page="../parts/scripts.jsp"/>
</body>
</html>
