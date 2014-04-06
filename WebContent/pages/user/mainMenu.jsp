<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.java.task11.i18n.text" />

<!-- NAVBAR -->
<nav class="navbar navbar-default" role="navigation">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target="#navbar-collapse-01">
			<span class="sr-only">Toggle navigation</span>
		</button>
		<a class="navbar-brand" href="#">TimeAssistant</a>
	</div>
	<div class="collapse navbar-collapse" id="navbar-collapse-01">
		<ul class="nav navbar-nav">
			<li><a href="${pageContext.request.contextPath}/user/tasks"><fmt:message
						key="tasks.title" /></a></li>
			<li><a
				href="${pageContext.request.contextPath}/user/userEditProfile"><fmt:message
						key="edit.profile.title" /></a></li>
			<li><a href="${pageContext.request.contextPath}/user/stats"><fmt:message
						key="user.statistic" /></a></li>
		</ul>
		<div class="navbar-form navbar-right">
			${sessionScope.user.firstName} | <a
				href="${pageContext.request.contextPath}/logout">
                <fmt:message key="nav.dropdown.logout" /></a> | <a
				href="?language=${language == 'uk' ? 'en' : 'uk'}"> ${language == 'uk' ? 'EN' : 'UKR'}
			</a>
		</div>
		<form action="${pageContext.request.contextPath}/search" method="get"
			class="navbar-form navbar-left" role="search">
			<div class="form-group">
				<input type="text" name="search" class="form-control"
					placeholder="<fmt:message key="search" />">
			</div>
			<button type="submit" class="btn btn-default"><fmt:message key="search" /></button>
		</form>
	</div>
</nav>