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
    <title>User Info</title>
    <jsp:include page="parts/header.jsp" />
</head>

<body>
	<div class="container-fluid login-page">
		<div class="row">
			<div class="login-screen">
				<div class="login-part">
					<div class="login-ico col-md-1 col-sm-2 col-xs-12 col-md-offset-3">
					
					</div>

					<div
						class="login-form col-md-4 col-sm-8 col-xs-12 col-md-offset-1 col-sm-offset-1">

						<h3>Welcome</h3>
						<c:out value="${sessionScope.user.firstName}"></c:out>
						<c:out value="${sessionScope.user.lastName}"></c:out>
					</div>
				</div>
			</div>

		</div>
	</div>
</body>
</html>