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
    <link rel="stylesheet" type="text/css" href="../../css/styles.css" />
    <title>add project</title>
    <jsp:include page="header.jsp" />
</head>

<body>
	<div class="container">
		<form action="${pageContext.request.contextPath}/manager/addproject" method="post">
			<table  class="table">
				<tr>
					<td><fmt:message key="project.name" /></td>
					<td><input type="text" name="name"  required></td>
				</tr>
				<tr>
					<td><fmt:message key="project.description" /></td>
					<td><input type="text" name="description"></td>
				</tr>
				<tr>
					<td ><fmt:message key="project.notes" /></td>
					<td><textarea rows="6" cols="35" name="notes"></textarea></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<button name="submit" type="submit" class="btn btn-primary" value="Submit" >
							<fmt:message key="button.addProject" />
						</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
