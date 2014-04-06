<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href='http://fonts.googleapis.com/css?family=Varela+Round' rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/flat-ui.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css"/>

<script src="${pageContext.request.contextPath}/js/application.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap-select.js"></script>
<script src="${pageContext.request.contextPath}/js/classie.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-2.0.3.min.js"></script>
<script src="${pageContext.request.contextPath}/js/modernizr.custom.js"></script>
<script src="${pageContext.request.contextPath}/js/scripts.js"></script>

<script src="${pageContext.request.contextPath}/js/modernizr.custom.js"></script>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.java.task11.i18n.text" />
<<<<<<< 03de9564aa2a01ee2979b0c85fa2546ee15f6065
=======

>>>>>>> 49dc31138aa78094f07b82ca218bf38f24271542
<html lang="${language}">
<body>
	<!-- NAVBAR -->
	<nav class="navbar navbar-default" role="navigation">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#navbar-collapse-01">
				<span class="sr-only">Toggle navigation</span>
			</button>
			<a class="navbar-brand" href="${pageContext.request.contextPath}/pages/admin/welcome.jsp">TimeAssistant</a>
		</div>
		<div class="collapse navbar-collapse" id="navbar-collapse-01">
			<ul class="nav navbar-nav">
<<<<<<< 03de9564aa2a01ee2979b0c85fa2546ee15f6065
				<li><a href="${pageContext.request.contextPath}/admin/users"><fmt:message key="admin.usersdisplay" /></a></li>
=======
				<li><a href="${pageContext.request.contextPath}/admin/users"><fmt:message key="admin.display.users" /></a></li>
>>>>>>> 49dc31138aa78094f07b82ca218bf38f24271542
				<li><a href="${pageContext.request.contextPath}/admin/adduser"><fmt:message key="admin.addUser" /></a></li>
				<li><a href="${pageContext.request.contextPath}/admin/userEditProfile"><fmt:message key="edit.profile.title" /></a></li>
			</ul>
			<div class="navbar-form navbar-right">
				${sessionScope.user.firstName} ${sessionScope.user.lastName}
                <a href="${pageContext.request.contextPath}/logout">
                    <fmt:message key="nav.dropdown.logout"/>
                </a>
					 <%-- i18n --%>
                <a href="?language=${language == 'uk' ? 'en' : 'uk'}">
                    ${language == 'uk' ? 'EN' : 'UKR'}
                </a>
               
			</div>
		</div>
	</nav>
</body>
</html>