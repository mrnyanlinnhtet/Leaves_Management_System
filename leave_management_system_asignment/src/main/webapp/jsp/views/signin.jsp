<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign In</title>
<c:url value="/resources/application.css" var="cssLink"></c:url>
<link rel="stylesheet" href="${cssLink}" type="text/css" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
	
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
	integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>

<body class="vh-100 bg-success">



 <div class="d-flex vh-100 justify-content-center align-items-center">
 <div class="card" style="width: 420px">
 <!-- Card Header -->
 <div class="card-header">
  <i class="fas fa-door-open"></i> <b>Sign In</b>
 </div>
 
 <!-- Card Body -->
 <div class="card-body ">
 
   
 
     <c:url value="signin" var="signin"></c:url>
     <sf:form action="${signin}" method="post">
     
      <c:if test="${not empty param.error }">
      <div class="alert alert-danger">Login Error</div>
     </c:if>
     
       <div class="form-group mt-4">
        <label for="username" class="form-lable mb-2"><b>Email : </b></label>
        <input type="email" name="username" id="username" placeholder="Enter Email !" class="form-control" />
       </div>
       
       <div class="form-group my-4">
       <label for="password" class="form-label"><b>Password : </b></label>
        <input type="password" class="form-control" name="password" placeholder="Enter Password !" id="password" />
       </div>
       
       <button type="submit" class="btn btn-success"><i class="fas fa-sign-in-alt"></i> Sign In</button>
     
     </sf:form>
 
 </div>
 </div>
 
 </div>

</body>
</html>