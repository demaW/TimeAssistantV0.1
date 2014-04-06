<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="com.java.task11.i18n.text"/>
<html lang="${language}">

<head>
<<<<<<< 03de9564aa2a01ee2979b0c85fa2546ee15f6065
<title>Create invoice</title>
<jsp:include page="header.jsp" />


<script>
	var tableToExcel = (function() {
		var uri = 'data:application/vnd.ms-excel;base64,', template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head></head><body><table>{table}</table></body></html>', base64 = function(
				s) {
			return window.btoa(unescape(encodeURIComponent(s)));
		}, format = function(s, c) {
			return s.replace(/{(\w+)}/g, function(m, p) {
				return c[p];
			});
		};
		return function(table, name) {
			if (!table.nodeType)
				table = document.getElementById(table);
			var ctx = {
				worksheet : name || 'Worksheet',
				table : table.innerHTML
			};
			window.location.href = uri + base64(format(template, ctx));
		};
	})()
</script>



</head>
<body>


	<div class="container">
		<div id="dvData">
		<h3><fmt:message key="project.title"/>: ${project.projectName}</h3>
		<br>
		<p><fmt:message key="invoice.projectId"/>: ${project.id }</p>
		<br>
		</div>
		<form action="${pageContext.request.contextPath}/manager/invoice"
			method="get">
			<select name="taskfilter" id="taskfilter"
				onchange="this.form.submit();">
				<option value="all" ${taskfilter == 'all' ? 'selected' :''}>All
					tasks</option>
				<option value="finished"
					${taskfilter == 'finished' ? 'selected' :''}>Only finished
					tasks</option>
				<option value="inprogress"
					${taskfilter == 'inprogress' ? 'selected' :''}>In progress</option>

			</select> <input type="hidden" id="project_id" name="project_id"
				value="${project.id }">
		</form>
		<div id="dvData">
			<table id="testTable" class="table table-striped">
				<thead>
					<tr>
						<th><fmt:message key = "invoice.employee"/></th>
						<th><fmt:message key = "user.position"/></th>
						<th><fmt:message key = "invoice.task"/></th>
						<th><fmt:message key = "task.state"/></th>
						<th><fmt:message key ="invoice.workedHours"/></th>
						<th><fmt:message key ="invoice.bookedHours"/></th>
						<th><fmt:message key ="invoice.salaryRate"/>
						</th>
						<th><fmt:message key ="invoice.costs"/>
						</th>
						<th>Planned costs <br> per employee, $
						</th>
					</tr>
				</thead>
				<c:forEach var="invoice" items="${invoices}">
					<tr>
						<td>${invoice.firstName}${invoice.lastName}</td>
						<td>${invoice.position}</td>
						<td>${invoice.taskName }</td>
						<td>${invoice.taskState}</td>
						<td>${invoice.workedTime}</td>
						<td><c:out value=" ${invoice.planedTime}"></c:out></td>
						<td>${invoice.salaryRate}</td>
						<td>${invoice.cosPerEmployee}</td>
						<td><c:out value="${invoice.planedCostPerEmployee}"></c:out></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="row">
			<div class="col-md-6"></div>
			<div class="col-md-6">
				<span class="pull-right"> Total cost: ${sumCost} $ </span>
			</div>
			<div class="col-md-6"></div>
			<div class="col-md-6">
				<span class="pull-right"> Planed total cost: ${planedSumCost}
					$ </span>
			</div>
		<a href="${pageContext.request.contextPath}/manager/invoicestats?project_id=${project.id }"> Stats</a>
		</div>
			<input type="button" onclick="tableToExcel('testTable', '${project.projectName}')"
			value="Export to Excel" class="btn btn-primary">
            <br/><br/>
			<input class="btn btn-primary" type="button" value="Print" name="pr" title="Print" onClick="window.print();">
	    </div>
=======
    <title>Create invoice</title>
    <jsp:include page="header.jsp"/>

    <script>
        var tableToExcel = (function () {
            var uri = 'data:application/vnd.ms-excel;base64,', template =
                    '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head></head><body><table>{table}</table></body></html>', base64 = function (s) {
                return window.btoa(unescape(encodeURIComponent(s)));
            }, format = function (s, c) {
                return s.replace(/{(\w+)}/g, function (m, p) {
                    return c[p];
                });
            };
            return function (table, name) {
                if (!table.nodeType)
                    table = document.getElementById(table);
                var ctx = {
                    worksheet: name || 'Worksheet',
                    table: table.innerHTML
                };
                window.location.href = uri + base64(format(template, ctx));
            };
        })()
    </script>
</head>

<body>
<div class="container">
    <div id="dvData">
        <h3><fmt:message key="project.name"/>: ${requestScope.project.projectName}</h3>
        <br>

        <p>#: ${requestScope.project.id }</p>
        <br>
    </div>
    <form action="${pageContext.request.contextPath}/manager/invoice"
          method="get">
        <select name="taskfilter" id="taskfilter"
                onchange="this.form.submit();">
            <option value="all" ${taskfilter == 'all' ? 'selected' :''}>
                <fmt:message key="task.all"/>
            </option>
            <option value="finished"
            ${taskfilter == 'finished' ? 'selected' :''}>
                <fmt:message key="task.finished"/>
            </option>
            <option value="inprogress"
            ${taskfilter == 'inprogress' ? 'selected' :''}>
                <fmt:message key="task.progres"/>
            </option>

        </select> <input type="hidden" id="project_id" name="project_id"
                         value="${requestScope.project.id }">
    </form>
    <div id="dvData">
        <table id="testTable" class="table table-striped">
            <thead>
            <tr>
                <th><fmt:message key="profile.firstname"/></th>
                <th><fmt:message key="profile.position"/></th>
                <th><fmt:message key="project.tasks"/></th>
                <th><fmt:message key="task.state"/></th>
                <th><fmt:message key="stat.worked"/></th>
                <th><fmt:message key="stat.book"/></th>
                <th><fmt:message key="user.salaryRate"/><br> $ / hour
                </th>
                <th><fmt:message key="stat.cost"/><br/>
                    <fmt:message key="stat.per.empl"/>, $
                </th>
                <th><fmt:message key="stat.cost"/><br/>
                    <fmt:message key="stat.per.empl"/>, $
                </th>
            </tr>
            </thead>

            <c:forEach var="invoice" items="${requestScope.invoices}">
                <tr>
                    <td>${invoice.firstName}${invoice.lastName}</td>
                    <td>${invoice.position}</td>
                    <td>${invoice.taskName }</td>
                    <td>${invoice.taskState}</td>
                    <td>${invoice.workedTime}</td>
                    <td><c:out value=" ${invoice.planedTime}"></c:out></td>
                    <td>${invoice.salaryRate}</td>
                    <td>${invoice.cosPerEmployee}</td>
                    <td><c:out value="${invoice.planedCostPerEmployee}"></c:out></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="row">
        <div class="col-md-6"></div>
        <div class="col-md-6">
            <span class="pull-right"> Total cost: ${requestScope.sumCost} $ </span>
        </div>
        <div class="col-md-6"></div>
        <div class="col-md-6">
				<span
                        class="pull-right"><fmt:message key="stat.cost"/>:
                    ${requestScope.planedSumCost} $
                </span>
        </div>
        <a href="${pageContext.request.contextPath}/manager/invoicestats?project_id=${requestScope.project.id }">
            <fmt:message key="user.statistic"/>
        </a>
    </div>
    <input type="button" onclick="tableToExcel('testTable', '${requestScope.project.projectName}')"
           value="Export to Excel" class="btn btn-primary">
    <br/><br/>
    <input class="btn btn-primary" type="button" value="Print" name="pr" title="Print" onClick="window.print();">
</div>
>>>>>>> 49dc31138aa78094f07b82ca218bf38f24271542

</body>
</html>