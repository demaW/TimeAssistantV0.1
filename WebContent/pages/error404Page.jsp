<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isErrorPage="true"%>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.java.task11.i18n.text" />

<html lang="${language}">
<head>
    <title>404 opps</title>
    <jsp:include page="user/import.jsp" />
</head>

<body>
	<!-- NAVBAR -->
	<nav class="navbar navbar-default" role="navigation">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#navbar-collapse-01">
				<span class="sr-only">Toggle navigation</span>
			</button>
			<a class="navbar-brand" href="${pageContext.request.contextPath}/home">TimeAssistant</a>
		</div>
		<div class="collapse navbar-collapse" id="navbar-collapse-01">
			<ul class="nav navbar-nav">
				<li><a href="${pageContext.request.contextPath}/home"">Home</a></li>
			</ul>
			<div class="navbar-form navbar-right">
				Looged in as ${sessionScope.user.firstName} | <a
					href="${pageContext.request.contextPath}/logout">Log out</a>
			</div>
		</div>
	</nav>

	<!-- CONTENT -->
	<h2 align="center">404 - page not found</h2>

	<img src="${pageContext.request.contextPath}/img/error.jpg"
		style="position: absolute; left: 50%; top: 50%; margin-left: -285px; margin-top: -190px;">

</body>
</html>