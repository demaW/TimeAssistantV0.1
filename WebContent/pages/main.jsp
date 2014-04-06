<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    

<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.java.task11.i18n.text" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="${language}">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Time assistance</title>
<jsp:include page="user/import.jsp" />
</head>
<body>

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
      <div class="container">
        <h1 align="center">Time assistant</h1>
<<<<<<< 03de9564aa2a01ee2979b0c85fa2546ee15f6065
        <p><h6>Туту треба написати текст той що є в презі тайм ас це блалала.Туту треба написати текст той що є в презі тайм ас це блалала.</h6></p>
=======
        <p><h6>Time Assistant is webbased tool for companies and organizations who need information on how much time is spend on projects.</h6></p>
>>>>>>> 49dc31138aa78094f07b82ca218bf38f24271542
        <p align="center"><a href="${pageContext.request.contextPath}/registration" class="btn btn-default btn-hg" role="button" >Register</a>
        	<a href="${pageContext.request.contextPath}/login" class="btn btn-success btn-hg" role="button">Login</a></p>
      </div>
    </div>

    <div class="container">
      <!-- Example row of columns -->
      <div class="row">
        <div class="col-md-4">
          <h3>Time Tracking</h3>
          <p><img src="${pageContext.request.contextPath}/img/2.png"  class="img-thumbnail">  </p>
        </div>
        <div class="col-md-4">
          <h3>Statistics</h3>
          <p><img src="${pageContext.request.contextPath}/img/1.png"  class="img-thumbnail"> </p>
       </div>
        <div class="col-md-4">
          <h3>Team work</h3>
          <p><img src="${pageContext.request.contextPath}/img/3.png"  class="img-thumbnail"> </p>
        </div>
      </div>

      <hr>

    </div>

</body>
</html>