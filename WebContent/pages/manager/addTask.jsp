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
    <title>Add task</title>
    <jsp:include page="/pages/user/import.jsp" />
    <jsp:include page="header.jsp" />

    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.9.1.js"></script>
    <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    <script>
        $(function() {
            $("#startDate").datepicker();
            $("#endDate").datepicker();
        });
    </script>
</head>

<body>
	<!-- CONTENT -->
	<div class="container">
		<form action="${pageContext.request.contextPath}/manager/addTask" method="post">
            <div class="form-group">
                <c:forEach items="${requestScope.addTaskErrors}" var="error">
                    <p class="error">${error}</p>
                </c:forEach>
            </div>
			<input type="hidden">
			<table class="table">
                <tr><td><fmt:message key="project.title"/>:</td>
                    <td>
                        <select name="project_id">
                            <c:forEach var="project" items="${requestScope.projectList}">
                                <option value="${project.id}">
                                    <c:out value="${project.projectName}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
				<tr><td><fmt:message key="task.name"/>:</td>
					<td>
						<input type="text" name="title" required>
					</td>
				</tr>
				<tr><td><fmt:message key="task.description"/>:</td>
					<td><textarea name="description" required></textarea></td>
				</tr>
				<tr><td><fmt:message key="task.estimate"/>:</td>
					<td><input type="number" name="estimate_time" required></td>
				</tr>
				<tr><td><fmt:message key="task.assignee"/>:</td>
					<td><select name="user_id">
							<c:forEach var="user" items="${requestScope.users_in_project}">
								<option value="${user.id}">
								    <c:out value="${user.firstName} ${user.lastName} - ${user.position} - ${user.salaryRate}"/>
                                </option>
							</c:forEach>
					</select></td>
				</tr>
                <tr><td><fmt:message key="task.start"/>:</td>
                    <td>
                        <input id="startDate" name="startDate" placeholder="MM/dd/yy"/> <br/>
                    </td>
                </tr>
                <tr><td><fmt:message key="task.end"/>:</td>
                    <td>
                        <input id="endDate" name="endDate" placeholder="MM/dd/yy"/> <br/>
                    </td>
                </tr>
                <tr><td><fmt:message key="task.sendNotification"/>:</td>
                    <td>
                       <input type="radio" name="mailNotification" value="yes">  yes 
						<input type="radio" name ="mailNotification" value = "no" checked> no
                    </td>
                </tr>
				<tr>
					<td align="right"><a href="${pageContext.request.contextPath}/manager/projectstable"
						class="btn btn-danger"><fmt:message key="button.cancel"/></a></td>
					<td>
						<button name="submit" type="submit" class="btn btn-primary"
							value="Submit"><fmt:message key="button.addTask"/></button>
					</td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>