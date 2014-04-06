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
<title>User Task</title>
<jsp:include page="import.jsp" />
</head>

<body>
	<jsp:include page="mainMenu.jsp" />
	
	<br />

	<!-- CONTENT -->
	<div class="container">
		<p class="lead">
			<c:out value="${task.title}"></c:out>
		</p>
		<form action="${pageContext.request.contextPath}/user/task"
			method="post">
			<table class="table">
				<tr>
					<td width="300px">ID:</td>
					<td><c:out value="${task.taskId}"></c:out><input type="hidden"
						value="${task.taskId}" name="task_id"></td>
				</tr>
				<tr>
					<td><fmt:message key="task.description" />:</td>
					<td><c:out value="${task.description}"></c:out></td>
				</tr>
				<tr>
					<td><fmt:message key="task.state" />:</td>
					<td><span class="label label-default"><c:out
								value="${task.state}"></c:out></span></td>
				</tr>
				<tr>
					<td><fmt:message key="task.project" />:</td>
					<td><c:out value="${task.project.projectName}"></c:out></td>
				</tr>
				<tr>
					<td><fmt:message key="task.estimate" />:</td>
					<td><c:out value="${task.estimateTime}"></c:out></td>
				</tr>
				<tr>
					<td><fmt:message key="task.real" />:</td>
					<td>
						<div class="input-group col-sm-2">
							<input type="text" class="form-control" name="realTime" value="${task.realTime}"
								pattern="[0-9]+" required>  <span class="input-group-addon">hours</span>
						</div>
					</td>
				</tr>
				<tr>
					<td><fmt:message key="task.isfinished" />:</td>
					<td>
						<label class="checkbox" for="checkbox">
							<input type="checkbox" data-toggle="checkbox" name="finished"
							id="checkbox" <c:if test="${task.state == 'FINISHED'}">checked="checked"</c:if>>
						</label>
					</td>
				</tr>
				<tr>
					<td></td>
					<td><button name="submit" type="submit"
							class="btn btn-primary btn-hg" value="Submit"><fmt:message key="button.save" /></button></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>