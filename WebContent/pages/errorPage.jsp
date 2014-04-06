<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.java.task11.i18n.text" />

<html lang="${language}">
<head>
    <title>Error</title>
    <jsp:include page="user/import.jsp" />
</head>

<body>
	
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
				<li><a href="${pageContext.request.contextPath}/home">Home</a></li>
			</ul>
			
		</div>
	</nav>

	
	<h2 align="center">Something bad happened.</h2>

	<img src="${pageContext.request.contextPath}/img/error.jpg"
		style="position: absolute; left: 50%; top: 50%; margin-left: -285px; margin-top: -190px;">

</body>
</html>