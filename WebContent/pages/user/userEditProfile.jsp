<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.java.task11.i18n.text" />

<html lang="${language}">
<head>
    <title>User Profile</title>
    <jsp:include page="import.jsp" />
</head>

<body>
	<jsp:include page="mainMenu.jsp" />
	<br />

	<!-- CONTENT -->
	<div class="container">
		<form action="${pageContext.request.contextPath}/user/userEditProfile"
			method="post">
			<table class="table">
				<tr>
					<td><fmt:message key="profile.firstname" />:</td>
					<td>
						<div class=" col-sm-4">
							<input type="text" name="firstName" class="form-control" required
								value="${sessionScope.user.firstName}">
						</div>
					</td>
				</tr>
				<tr>
					<td><fmt:message key="profile.lastname" />:</td>
					<td>
						<div class=" col-sm-4">
							<input type="text" name="lastName" class="form-control" required
								value="${sessionScope.user.lastName}">
						</div>
					</td>

				</tr>
				<tr>
					<td><fmt:message key="profile.email" />:</td>
					<td>
						<div class=" col-sm-4">
							<input type="text" name="email"
								pattern="[^ @]*@[^ @]*\.[^ @]{2,}" class="form-control" required
								value="${sessionScope.user.email}">
						</div>
					</td>
				</tr>
				<tr>
					<td><fmt:message key="profile.pasword" />:</td>
					<td>
					<div class=" col-sm-4">
					<input type="password" name="password" class="form-control" value="">
					</div>
					</td>
				</tr>
				<tr>
					<td align="right"><a
						href="${pageContext.request.contextPath}/user/tasks"
						class="btn btn-default"><fmt:message key="button.cancel" /></a></td>
					<td>
						<button name="submit" type="submit" class="btn btn-primary"
							value="Submit">
							<fmt:message key="button.save" />
						</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>