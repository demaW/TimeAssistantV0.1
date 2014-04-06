<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/img/logo-url.png"/>
<link href='http://fonts.googleapis.com/css?family=Varela+Round' rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/flat-ui.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css"/>


<script src="${pageContext.request.contextPath}/js/jquery-2.0.3.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap-select.js"></script>
<script src="${pageContext.request.contextPath}/js/classie.js"></script>

<script src="${pageContext.request.contextPath}/js/modernizr.custom.js"></script>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="com.java.task11.i18n.text"/>

<html lang="${language}">
<body>
<!-- NAVBAR -->
<nav class="navbar navbar-default" role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse"
                data-target="#navbar-collapse-01">
            <span class="sr-only">Toggle navigation</span>
        </button>
        <a class="navbar-brand" href="<c:url value="/manager/projectstable"/>">TimeAssistant</a>
    </div>
    <div class="collapse navbar-collapse" id="navbar-collapse-01">
        <ul class="nav navbar-nav">
            <li>
                <a href="${pageContext.request.contextPath}/manager/addproject">
                    <fmt:message key="menu.add.projects"/>
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/manager/addTask">
                    <fmt:message key="menu.add.task"/>
                </a>
            </li>

            <li><a href="${pageContext.request.contextPath}/manager/users">
                <fmt:message key="menu.add.users"/>
            </a>
            </li>
            <li><a href="${pageContext.request.contextPath}/manager/projectstable">
                <fmt:message key="project.title"/>
            </a>
            </li>
            <li><a href="${pageContext.request.contextPath}/manager/editProfile">
                <fmt:message key="nav.dropdown.profile"/>
            </a>
            </li>
        </ul>
        <div class="navbar-form navbar-right lang">
            ${sessionScope.user.firstName} ${sessionScope.user.lastName}
            <a href="${pageContext.request.contextPath}/logout">
                <fmt:message key="nav.dropdown.logout"/>
            </a>
            <%-- i18n --%>
<<<<<<< 03de9564aa2a01ee2979b0c85fa2546ee15f6065
            <%--<a href="?language=${language == 'uk' ? 'en' : 'uk'}">
                ${language == 'uk' ? 'EN' : 'UKR'}
            </a>--%>
            <a href="<%= request.getContextPath()%>?language=${language == 'uk' ? 'en' : 'uk'}">
               ${language == 'uk' ? 'EN' : 'UKR'}
            </a>
=======
            <a href="?language=${language == 'uk' ? 'en' : 'uk'}">
                ${language == 'uk' ? 'EN' : 'UKR'}
            </a>
            <%--<a href="<%= request.getContextPath()%>?language=${language == 'uk' ? 'en' : 'uk'}">
               ${language == 'uk' ? 'EN' : 'UKR'}
            </a>--%>
>>>>>>> 49dc31138aa78094f07b82ca218bf38f24271542
        </div>
        <form action="${pageContext.request.contextPath}/search"
              method="get" class="navbar-form navbar-left" role="search">
            <div class="form-group">
                <input type="text" name="search" class="form-control" placeholder="Search">
            </div>
            <button type="submit" class="btn btn-default">Search</button>
        </form>
    </div>
</nav>
<br/>
</body>
</html>
