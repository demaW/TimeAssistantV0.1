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

    <title>Users list</title>
    <jsp:include page="header.jsp" />

</head>

<body>
<jsp:useBean id="roles" scope="page" class="com.java.task11.model.UserRole" />
<div class="container -fluid projects-table">
<a href="${pageContext.request.contextPath}/admin/adduser"> <fmt:message key="button.addUser"/></a>
<form action="${pageContext.request.contextPath}/admin/updateemployee" name="updateEmployee" method="get">
		<TABLE class="table"   >
			<thead>
				<tr>
					<th>Id</th>
					<th><fmt:message key="user.firstName"/></th>
					<th><fmt:message key="user.lastName"/></th>
					<th><fmt:message key="user.email"/></th>
					<th><fmt:message key="user.role"/></th>
					<th><fmt:message key="user.position"/></th>
					<th><fmt:message key="user.salaryRate"/></th>
					
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>

			<c:forEach var="user" items="${users}" >
				
				<tr>
					<td>${user.id}</td>

					<td>${user.firstName}</td>
					<td>${user.lastName}</td>
					<td>${user.email}</td>
					<td> <c:choose>
					  <c:when test="${user.roleId=='1'}">
					  	${requestScope.roles[0].roleName}
					  </c:when>
					
					  <c:when test="${user.roleId=='2'}">
					   	${requestScope.roles[1].roleName}
					  </c:when>
					
					  <c:when test="${user.roleId=='3'}">
					   	${requestScope.roles[2].roleName}
					  </c:when>
					
					</c:choose>
					</td>
					<td>${user.position}</td>
					<td>${user.salaryRate}</td>
					<td><input type="radio" name="notification" value="${user.id}"></td>
					
			</tr>
			
		
		
</c:forEach>
			
			</tbody>
			
		</TABLE>
	
		<button type="submit" name="edit" class = "btn btn-primary pull-right"  style= "margin-right: 2%"> <fmt:message key="button.edit" /></button>
			</form>
		</div>
</body>
</html>
