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
    <title>Registration</title>
    <jsp:include page="parts/header.jsp"/>
</head>

<body>
<div class="container-fluid registration">
    <div class="row">
        <div class="login-screen">
            <div class="login-part">
                <div class="login-ico col-md-1 col-sm-2 col-xs-12 col-md-offset-3">
                    <h4>
                        <small><fmt:message key="registration.label"/></small>
                    </h4>
                </div>
                <div class="login-form col-md-4 col-sm-8 col-xs-12 col-md-offset-1 col-sm-offset-1">
                    <form action="registration" method="post">
                        <div class="form-group">
                            <c:forEach items="${requestScope.registrationErrors}" var="error">
                                <p class="error">${error}</p>
                            </c:forEach>
                        </div>
                        <div class="form-group">
                            <input class="form-control" placeholder="<fmt:message key="user.firstName"/>" name="first_name" required
                                   id="first-name"/>
                            <label class="login-field-icon fui-user"></label>
                        </div>
                        <div class="form-group">
                            <input class="form-control" placeholder="<fmt:message key="user.lastName"/>" name="last_name" required
                                   id="last-name"/>
                            <label class="login-field-icon fui-user"></label>
                        </div>

                        <div class="form-group">
                            <input class="form-control" placeholder="<fmt:message key="login.email"/>" name="email"
                                  required id="email"/>
                            <label class="login-field-icon fui-mail"></label>
                        </div>
                        <div class="form-group">
                            <input class="form-control" type="password" placeholder="<fmt:message key="user.password"/>" name="password" required
                                   id="password"/>
                            <label class="login-field-icon fui-lock"></label>
                        </div>
                        <div class="form-group">
                            <input class="form-control" type="text" placeholder="<fmt:message key="user.position"/>" name="position" required
                                   id="position"/>
                            <label class="login-field-icon fui-user"></label>
                        </div>
                        <div class="form-group">
                            <button class="btn btn-primary btn-lg btn-block" name="submit"
                                    type="submit" value="Submit">
                                <fmt:message key="button.submit"/>
                            </button>
                            <a class="login-link" href="<c:url value="/login"/>"><fmt:message key="registration.login"/></a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="parts/scripts.jsp"/>
</body>
</html>
