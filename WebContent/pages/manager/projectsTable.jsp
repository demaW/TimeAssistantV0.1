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
    <title>Projects table</title>
    <jsp:include page="header.jsp" />
</head>

<body>
    <!-- CONTENT -->
    <div class="container-fluid projects-table">
        <div class="row">
            <div class="col-md-10 col-sm-12 col-xs-12 col-md-offset-1">
                <h3><fmt:message key="projectsTable.title"/></h3>
                <br/>
                <div class="row">
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/manager/addproject">
                        <fmt:message key="button.addProject"/>
                    </a>
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/manager/addTask">
                        <fmt:message key="button.addTask"/>
                    </a>
                </div>
            </div>
        </div>
        <br/>

    <form action="${pageContext.request.contextPath}/manager/projectstable" method="post" id="projectstable-form">
        <div class="row">
            <div class="table-responsive tile col-md-10 col-sm-12 col-xs-12 col-md-offset-1">
                <table class="table table-striped table-hover">
                <!-- Table -->
                <thead>
                <tr>
                    <th></th>
                    <th>#</th>
                    <th><fmt:message key="project.name" /></th>
                    <th><fmt:message key="project.description" /></th>
                    <th><fmt:message key="project.notes"/></th>
                    <th><fmt:message key="project.tasks"/></th>
                    <th><fmt:message key="project.invoice"/></th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="project" items="${requestScope.projects}">
                    <tr>
                        <td>
                            <label class="checkbox" for="checkbox${project.id}">
                                <input type="checkbox" name="checkedProjects" value="${project.id}"
                                       id="checkbox${project.id}" data-toggle="checkbox">
                            </label>
                        </td>
                        <td>${project.id}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/manager/teamsTable?project_id=${project.id}">
                                <c:out value="${project.projectName}" />
                            </a>
                        </td>
                        <td>${project.description}</td>
                        <td>${project.notes}</td>
                        <td><a href="${pageContext.request.contextPath}/manager/taskstable?project_id=${project.id}"
                            class="btn btn-info btn-xs"><fmt:message key="project.see.tasks"/></a>
                        </td>
                        <td><a href="${pageContext.request.contextPath}/manager/invoice?project_id=${project.id}"
                               class="btn btn-info btn-xs"><fmt:message key="project.see.invoice"/></a>
                        </td>
                        <%--EDIT PROJECT INFO--%>
                        <td>
                            <span class="fui-new modal-icon" data-toggle="modal"
                                      data-target="#modalEdit${project.id}"></span>

                            <div class="modal fade" id="modalEdit${project.id}" tabindex="-1" role="dialog"
                                 aria-labelledby="modalEditLabel${project.id}" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal"
                                                    aria-hidden="true">&times;</button>
                                            <h4 class="modal-title">ID: ${project.id}</h4>
                                        </div>
                                        <div class="modal-body">
                                            <div class="form-group">
                                                <span><fmt:message key="project.name"/></span>
                                                <input class="form-control" placeholder="<fmt:message key="project.name"/>"
                                                       name="project_name-${project.id}"
                                                       value="${project.projectName}"/>
                                            </div>
                                            <div class="form-group">
                                                <span><fmt:message key="project.description"/></span>
                                                <input class="form-control" placeholder="<fmt:message key="project.description"/>"
                                                       name="project_description-${project.id}"
                                                       value="${project.description}"/>
                                            </div>
                                            <div class="form-group">
                                                <span><fmt:message key="project.notes"/></span>
                                                <input class="form-control" placeholder="<fmt:message key="project.notes"/>" type="text"
                                                       name="project_notes-${project.id}"
                                                       value="${project.notes}"/>
                                            </div>
                                            <div class="form-group">
                                                <button class="btn btn-primary btn-lg btn-block" name="update"
                                                        type="submit" value="${project.id}">
                                                    <fmt:message key="button.submit"/>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>
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
