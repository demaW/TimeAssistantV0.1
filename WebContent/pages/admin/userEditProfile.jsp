<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
    <jsp:include page="header.jsp" />
</head>

<body>
	<!-- CONTENT -->
	<div class="container">
		<form action="${pageContext.request.contextPath}/admin/userEditProfile" method="post">
			<table  class="table">
				<tr>
					<td><fmt:message key="user.firstName" /></td>
					<td><input type="text" name="firstName" required value="${user.firstName}"></td>
				</tr>
				<tr>
					<td><fmt:message key="user.lastName" /></td>
					<td><input type="text" name="lastName" required value="${user.lastName}"></td>
				</tr>
							<tr>
					<td><fmt:message key="user.email" /></td>
					<td><input type="text" name="email" pattern="[^ @]*@[^ @]*\.[^ @]{2,}" required value="${user.email}"></td>
				</tr>
				<tr>
					<td align="right"><a href="${pageContext.request.contextPath}/admin/users"
					 class="btn btn-default">	<fmt:message key="button.cancel" /></a></td>
					<td>
						<button name="submit" type="submit" class="btn btn-primary btn-hg" value="Submit">
						<fmt:message key="button.save" />
						</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>