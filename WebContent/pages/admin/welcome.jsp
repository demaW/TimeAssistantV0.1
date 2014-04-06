<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.java.task11.i18n.text" />

<html lang="${language}">
<head>

    <title>Admin Welcome page</title>
    <jsp:include page="header.jsp" />

</head>

<body>
<div class = "container">
	<div style="text-align: center;">
	<img  src="${pageContext.request.contextPath}/img/logo.png" width="300">
	</div>
	<h1  style = "text-align: center"><fmt:message key = "admin.welcomeHeader"/></h1> <br>
	<h2	 style = "text-align: center"><fmt:message key = "admin.listHead"/></h2><br>
	<ul>
			<li><h3><fmt:message key = "admin.lestEl1"/></h3></li>
			<li><h3><fmt:message key = "admin.lestEl2"/></h3></li>
			
		</ul>
	
	<p> </p>
</div>
</body>
</html>