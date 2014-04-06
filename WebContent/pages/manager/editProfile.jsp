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
    <title>Profile Info</title>
    <jsp:include page="header.jsp"/>
</head>

<body>
<!-- CONTENT -->
<div class="container-fluid users-table">
    <form action="${pageContext.request.contextPath}/manager/editProfile"  method="post" id="edit-profile-form">
        <div class="row">
            <div class="table-responsive tile col-md-10 col-sm-12 col-xs-12 col-md-offset-1">
                <table class="table table-striped table-hover">
                    <%--Titles--%>
                    <thead>
                    <tr>
                        <th>#</th>
                        <th><fmt:message key="user.firstName"/></th>
                        <th><fmt:message key="user.lastName"/></th>
                        <th><fmt:message key="user.email"/></th>
                        <th><fmt:message key="user.password"/></th>
                        <th><fmt:message key="user.position"/></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>${sessionScope.user.id}</td>
                            <td>${sessionScope.user.firstName}</td>
                            <td>${sessionScope.user.lastName}</td>
                            <td>${sessionScope.user.email}</td>
                            <td>${sessionScope.user.password}</td>
                            <td>${sessionScope.user.position}</td>
                                <%--edit profile pop up window--%>
                            <td>
                                    <span class="fui-new modal-icon" data-toggle="modal"
                                          data-target="#modalEdit${sessionScope.user.id}"></span>

                                <div class="modal fade" id="modalEdit${sessionScope.user.id}" tabindex="-1" userRole="dialog"
                                     aria-labelledby="modalEditLabel${sessionScope.user.id}" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal"
                                                        aria-hidden="true">&times;</button>
                                                <h4 class="modal-title">ID: ${sessionScope.user.id}</h4>
                                            </div>
                                            <div class="modal-body">
                                                <div class="form-group">
                                                    <span><fmt:message key="user.firstName"/></span>
                                                    <input class="form-control" placeholder="<fmt:message key="user.firstName"/>"
                                                           name="first_name-${sessionScope.user.id}"
                                                           value="${sessionScope.user.firstName}"/>
                                                </div>
                                                <div class="form-group">
                                                    <span><fmt:message key="user.lastName"/></span>
                                                    <input class="form-control" placeholder="<fmt:message key="user.lastName"/>"
                                                           name="last_name-${sessionScope.user.id}"
                                                           value="${sessionScope.user.lastName}"/>
                                                </div>
                                                <div class="form-group">
                                                    <span><fmt:message key="user.email"/></span>
                                                    <input class="form-control" type="email" placeholder="<fmt:message key="user.email"/>"
                                                           name="email-${sessionScope.user.id}"
                                                           pattern="[^ @]*@[^ @]*\.[^ @]{2,}" value="${sessionScope.user.email}"/>
                                                </div>
                                                <div class="form-group">
                                                    <span><fmt:message key="user.position"/></span>
                                                    <input class="form-control" type="text" placeholder="<fmt:message key="user.position"/>"
                                                           name="position-${sessionScope.user.id}"
                                                           value="${sessionScope.user.position}"/>
                                                </div>
                                                <div class="form-group">
                                                    <button class="btn btn-primary btn-lg btn-block" name="update"
                                                            type="submit" value="${sessionScope.user.id}">
                                                        <fmt:message key="button.update"/>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </form>
</div>
<jsp:include page="../parts/scripts.jsp"/>
</body>
</html>
