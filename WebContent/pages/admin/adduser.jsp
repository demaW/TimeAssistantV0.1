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

<script type="text/javascript">
function validate() {
    if (isNaN(document.adduser.salaryRate.value)) {
        alert("Enter number, please");
        document.adduser.salaryRate.focus();
        return false;
    }
   
   
    return true;
}</script>


    <title>Add user</title>
    <jsp:include page="header.jsp"/>
</head>
<body>
	<div class="container">
	
		<form name="adduser" action="${pageContext.request.contextPath}/admin/adduser" method="post"  onsubmit="return validate(this);">
			<table class="table-striped">
			
				<tr>
					<td><fmt:message key="user.email" /></td>
					<td><input type="text" name="email"  class="form-control "  required id="email"/></td>
				</tr>
				<tr>
					<td><fmt:message key="user.password" /></td>
					<td><input type="password" name="password"  class="form-control " required id="password"></td>
				</tr>
				<tr>
					<td><fmt:message key="user.firstName" /></td>
					<td><input type="text" name="firstName"  class="form-control " required id="firstName"></td>
				</tr>
				<tr>
					<td><fmt:message key="user.lastName" /></td>
					<td><input type="text" name="lastName"   class="form-control " required id="lastName"></td>
				</tr>
				<tr>
					<td><fmt:message key="user.position" /></td>
					<td><input type="text" name="position"  class="form-control " required id="position"></td>
				</tr>
				<tr>
					<td><fmt:message key="user.salaryRate" /></td>
					<td><input type="text" name="salaryRate"  class="form-control " required id="salaryRate"></td>
				</tr>
				<tr>
					<td><fmt:message key="user.role" /></td>
					<td><select name="role">

							<option value="user">user</option>
							<option value="manager">manager</option>
							<option value="admin">admin</option>
					</select></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<button name="submit" type="submit" value="Submit" class="btn btn-primary btn-hg">
							<fmt:message key="button.addUser" />
						</button>
					</td>
				</tr>
			</table>
		</form>
		<div class="form-group">
                            <c:forEach items="${requestScope.registrationErrors}" var="error">
                                <p class="error">${error}</p>
                            </c:forEach>
                        </div>
	</div>
</body>
</html>
