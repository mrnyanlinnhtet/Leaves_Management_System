<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Class Details</title>
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
   <h1 class="my-4 text-success"><b><i class="fas fa-info-circle"></i> Class Details</b></h1>
    <div class="card my-4">
    <div class="card-header text-success"><b><i class="fas fa-info"></i> Class Information</b></div>
     
    <div class="card-body row">
     <div class="col">
      <label for="teacher" class="form-label"><b>Teacher : </b></label>
      <span class="form-control">${dto.classInfo.teacherName}</span>
     </div>
     
      <div class="col">
      <label for="teacher" class="form-label"><b>Start Date : </b></label>
      <span class="form-control">${dto.classInfo.startDate }</span>
     </div>
     
      <div class="col">
      <label for="teacher" class="form-label"><b>Duration :  </b></label>
      <span class="form-control">${dto.classInfo.months} Months</span>
     </div>
     
      <div class="col">
      <label for="teacher" class="form-label"><b>Description :  </b></label>
      <span class="form-control">${dto.classInfo.description }</span>
     </div>
    </div>
   
    </div>
    <!-- End of Card -->
    
    <div class="d-flex justify-content-between my-5">
    
     <ul class="nav nav-pills">
     <li class="nav-item">
      <button class="nav-link active" data-bs-toggle="pill" data-bs-target="#registration">Registrations  <i class="fas fa-user-plus"></i></button>
     </li>
     
     <li class="nav-item">
      <button class="nav-link" data-bs-toggle="pill" data-bs-target="#leaves">Leaves Application <i class="fas fa-users"></i></button>
     </li>
     
     </ul>
     
     <div>
      <c:url value="/classes/registration" var="class_reg">
       <c:param name="classId" value="${dto.classInfo.id}"></c:param>
       <c:param name="teacherName" value="${dto.classInfo.teacherName}"></c:param>
       <c:param name="startDate" value="${dto.classInfo.startDate }"></c:param>
      </c:url>
      <a href="${class_reg}" class="btn btn-outline-danger">Add New Registration <i class="fas fa-user-plus"></i></a>
      
      
      
      <c:url value="/classes/edit" var="class_edit">
       <c:param name="id" value="${dto.classInfo.id}"></c:param>
      </c:url>
      <a href="${class_edit}" class="btn btn-outline-warning">Edit Class <i class="fas fa-pen"></i></a>
     </div>
     
    </div>
    
     <!-- End of justify -->
     <div class="tab-content" id="content">
     <div class="tab-pane fade show active" id="registration">
     <c:choose>
     <c:when test="${empty dto.registrations }">
      <div class="alert alert-info">There is no registration data !</div>
     </c:when>
     <c:otherwise>
      <!-- Registration Form -->
     <c:url value="/jsp/include/class-registration.jsp" var="reg"></c:url>
      <c:import url="${reg}"></c:import>
     </c:otherwise>
     </c:choose>
     </div>
     
      <div class="tab-pane fade" id="leaves">
       <c:choose>
     <c:when test="${empty dto.leaves }">
      <div class="alert alert-info">There is no leaves data !</div>
     </c:when>
     <c:otherwise>
     <!-- Leaves -->
      <c:url value="/jsp/include/class-leaves.jsp" var="leave"></c:url>
      <c:import url="${leave}"></c:import>
     </c:otherwise>
     </c:choose>
      
      </div>
     </div>
    
  </div>

</body>
</html>