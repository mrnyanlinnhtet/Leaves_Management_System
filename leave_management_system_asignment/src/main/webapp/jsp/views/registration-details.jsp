<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Leave Home</title>
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
<body>

  <c:import url="/jsp/include/navbar.jsp">
  <c:param name="view" value="classes"></c:param>
  </c:import>
  
  <div class="container">
   
   <h1 class="my-4 text-success"><b><i class="fas fa-info-circle"></i> Registration Details</b></h1>
   
   
   <div class="row">
   <!-- For Student Information -->
   <div class="col my-3">
   <div class="card">
   <div class="card-header text-success"><i class="fas fa-user-graduate"></i> <b>Student Information</b></div>
   <div class="card-body">
   
   <!-- For student name -->
   <div class="form-group mb-4">
    <label for="name" class="form-label"><b>Student Name : </b></label>
    <span class="form-control">${dto.student.name }</span>
   </div>
   
   <!-- For student phone -->
   <div class="form-group mb-4">
    <label for="name" class="form-label"><b>Phone Number : </b></label>
    <span class="form-control">${dto.student.phone}</span>
   </div>
   
   <!-- For student email -->
   <div class="form-group mb-4">
    <label for="name" class="form-label"><b>Email Address : </b></label>
    <span class="form-control">${dto.student.email }</span>
   </div>
   
   <!-- For student education -->
  <!--   <div class="form-group mb-4">
    <label for="name" class="form-label"><b>Education : </b></label>
    <span class="form-control">${dto.student.education }</span>
   </div>-->
   
   <!-- For registration -->
   <div class="form-group mb-4">
    <label for="name" class="form-label"><b>Registration Date : </b></label>
    <span class="form-control">${dto.registDate }</span>
   </div>
   
   </div>
   
   </div>
   </div>
   
   <!-- For Class Information -->
   <div class="col my-3">
   <div class="card">
   <div class="card-header text-success"><b><i class="fas fa-users"></i> Class Information</b></div>
   <div class="card-body">
   
   <!-- For teacher name -->
   <div class="form-group mb-4">
    <label for="name" class="form-label"><b>Teacher Name : </b></label>
    <span class="form-control">${dto.classInfo.teacherName }</span>
   </div>
   
   <!-- For start date -->
   <div class="form-group mb-4">
    <label for="name" class="form-label"><b>Start Date : </b></label>
    <span class="form-control">${dto.classInfo.startDate }</span>
   </div>
   
   <!-- For months -->
   <div class="form-group mb-4">
    <label for="name" class="form-label"><b>Duration : </b></label>
    <span class="form-control">${dto.classInfo.months } Months</span>
   </div>
   
   <!-- For reasons -->
   <div class="form-group mb-4">
    <label for="name" class="form-label"><b>Description : </b></label>
    <span class="form-control">${dto.classInfo.description }</span>
   </div>
   
  
   
   </div>
   </div>
   </div>
   
   </div>
   
    <div>
    <c:url value="/classes/registration" var="regEdit">
       <c:param name="studentId" value="${dto.student.id }"></c:param>
     <c:param name="classId" value="${dto.classInfo.id }"></c:param>
    </c:url>
     <a href="${regEdit}" class="btn btn-outline-danger">Edit Registration <i class="fas fa-pencil-alt"></i></a>
     
     <c:url value="/classes/${dto.classInfo.id}" var="classDetail">
     </c:url>
     <a href="${classDetail}" class="btn btn-outline-info mx-2"><i class="fas fa-location-arrow"></i> Class Detail</a>
    </div>
  
  </div>

</body>
</html>