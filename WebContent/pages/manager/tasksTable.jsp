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
    <title>Project info</title>
    <jsp:include page="header.jsp" />
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.9.1.js"></script>
    <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    <script>
        $(function () {
            $("#firstDate").datepicker();
            $("#secondDate").datepicker();
        });
    </script>
</head>

<body>
<!-- CONTENT -->
<div class="container-fluid tasks-table">
    <%--add new task--%>
    <div class="col-md-10 col-sm-12 col-xs-12 col-md-offset-1 tables-menu">
        <div class="row">
            <a class="btn btn-primary"
               href="${pageContext.request.contextPath}/manager/addTask?project_id=${requestScope.project_id}">
                <fmt:message key="button.addTask"/>
            </a>
        </div>
    </div>
    <br/><br/>

    <%--form --%>
    <form action="${pageContext.request.contextPath}/manager/taskstable" method="post" id="taskstable-form">
        <div class="row">
            <div class="table-responsive tile col-md-10 col-sm-12 col-xs-12 col-md-offset-1">
                <table class="table table-striped table-hover">
                    <thead>
                    <%--Titles--%>
                    <tr>
                        <th></th>
                        <th>#</th>
                        <th><fmt:message key="task.name" /></th> <!-- title property -->
                        <th><fmt:message key="task.description" /></th>
                        <th><fmt:message key="task.state" /></th>
                        <th><fmt:message key="task.estimate" /></th>
                        <th><fmt:message key="task.real" /></th>
                        <th><fmt:message key="task.start" /></th>
                        <th><fmt:message key="task.end" /></th>
                        <th><fmt:message key="task.assignee"/></th>
                        <th></th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${requestScope.tasksList}" var="task">
                        <tr>
                            <td>
                                <label class="checkbox" for="checkbox${task.taskId}">
                                    <input type="checkbox" name="checkedTasks" value="${task.taskId}"
                                           id="checkbox${task.taskId}" data-toggle="checkbox">
                                </label>
                            </td>
                            <td>${task.taskId}</td>
                            <td>${task.title}</td>
                            <td>${task.description}</td>
                            <td>${task.state}</td>
                            <td>${task.estimateTime}</td>
                            <td>${task.realTime}</td>
                            <td><fmt:formatDate value="${task.startDate}" type="date" pattern="yyyy-MM-dd"/></td>
                            <td><fmt:formatDate value="${task.endDate}" type="date" pattern="yyyy-MM-dd"/></td>
                            <td>
                                <c:forEach var="user" items="${requestScope.usersList}">
                                    <c:if test="${task.employeeId == user.id}">
                                        <c:out value="${user.firstName} ${user.lastName}"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td></td>

                                <%--edit task icon => edit user info pop up window--%>
                            <td>
                                <span class="fui-new modal-icon" data-toggle="modal"
                                      data-target="#modalEdit${task.taskId}"></span>

                                <div class="modal fade" id="modalEdit${task.taskId}" tabindex="-1" role="dialog"
                                     aria-labelledby="modalEditLabel${task.taskId}" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal"
                                                        aria-hidden="true">&times;</button>
                                                <h4 class="modal-title">ID: ${task.taskId}</h4>
                                            </div>
                                            <div class="modal-body">
                                                <div class="form-group">
                                                    <span><fmt:message key="task.name"/></span>
                                                    <input class="form-control" placeholder="<fmt:message key="task.name"/>"
                                                           name="task_name-${task.taskId}"
                                                           value="${task.title}"/>
                                                </div>
                                                <div class="form-group">
                                                    <span><fmt:message key="task.description"/></span>
                                                    <input class="form-control" placeholder="<fmt:message key="task.description"/>"
                                                           name="task_description-${task.taskId}"
                                                           value="${task.description}"/>
                                                </div>
                                                <div class="form-group">
                                                    <span><fmt:message key="task.estimate"/></span>
                                                    <input class="form-control" placeholder="<fmt:message key="task.estimate"/>"
                                                           name="estimate_time-${task.taskId}" id="estimateTime" type="number"
                                                           value="${task.estimateTime}"/>
                                                </div>
                                                <div class="form-group">
                                                </div>
                                                <div class="form-group">
                                                    <span><fmt:message key="task.start"/></span>
                                                    <input class="form-control" id="firstDate" placeholder="<fmt:message key='task.start'/>"
                                                           name="start_date-${task.taskId}" value="<fmt:formatDate value='${task.startDate}'
                                                           type='date' pattern='yyyy-MM-dd'/>"/>
                                                </div>
                                                <div class="form-group">
                                                    <span><fmt:message key="task.end"/></span>
                                                    <input class="form-control" id="secondDate" placeholder="<fmt:message key='task.end'/>"
                                                           name="end_date-${task.taskId}" value="<fmt:formatDate value='${task.endDate}'
                                                           type='date' pattern='yyyy-MM-dd'/>"/>
                                                </div>
                                                <div class="form-group">
                                                    <button class="btn btn-primary btn-lg btn-block" name="update"
                                                            type="submit" value="${task.taskId}">
                                                        <fmt:message key="button.submit"/>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row">
            <div class="col-md-7 col-sm-7 col-xs-6 col-md-offset-1">
                <button class="btn btn-danger" name="delete"
                        type="submit" value="Delete">
                    <fmt:message key="button.delete"/>
                </button>
            </div>
        </div>
    </form>
</div>
<jsp:include page="../parts/scripts.jsp"/>
</body>
</html>
