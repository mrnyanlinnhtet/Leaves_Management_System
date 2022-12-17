<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Home</title>
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

  <c:import url="/jsp/include/navbar.jsp"></c:import>
  
  <div class="container">
  <h1 class="text-success my-4"><b><i class="fas fa-home"></i> Student Home</b></h1>
  
  <div class="row">
  <div class="col-4">
  <div class="card mt-3">
  <div class="card-header text-success"><b><i class="fas fa-user-tie"></i>  Personal Information</b></div>
  <div class="card-body">
  
    <div class="my-3">
     <span class="text-secondary"><i class="far fa-user-circle"></i>  Name</span>
     <h5 class="text-primary">${student.student.name}</h5>
    </div>
    
     <div class="mb-3">
     <span class="text-secondary"><i class="fas fa-phone-alt"></i>  Phone</span>
      <h5 class="text-primary">${student.student.phone}</h5>
    </div>
    
    <div class="mb-3">
     <span class="text-secondary"><i class="fas fa-envelope"></i>  Email</span>
      <h5 class="text-primary">${student.student.email}</h5>
    </div>
    
     <div class="my-3">
     <span class="text-secondary"><i class="fas fa-graduation-cap"></i>  Education</span>
      <h5 class="text-primary">${student.student.education}</h5>
    </div>
    
  </div>
  </div>
  </div>
  
   <div class="col">
     <h4 class="text-success mt-3"><b><i class="fas fa-address-card"></i>  Registration</b></h4>
     <div class="row g-3">
     <div class="col-5">
     <c:forEach items="${student.registration}" var="i">
   	  <div class="card">
   	  	<div class="card-body">
   	  	 <h5 class="text-danger mb-3"><b><i class="fas fa-book-open"></i> ${i.classInfo }</b></h5>
   	  	 
   	  	 <div class="d-flex justify-content-between mb-4">
   	  	   <span class="text-primary"><b><i class="fas fa-user-graduate"></i> ${i.teacher}</b></span>
   	  	   <span class="text-primary"><b><i class="far fa-calendar-alt"></i> ${i.startDate}</b></span>
   	  	 </div>
   	  	 
   	  	 <div>
   	  	 <c:url value="/leaves/edit" var="leaves">
   	  	 <c:param name="classId" value="${i.classId}"></c:param>
   	  	 <c:param name="studentId" value="${i.studentId}"></c:param>
   	  	 </c:url>
   	  	 <a href="${leaves}" class="btn btn-outline-success"><i class="fas fa-location-arrow"></i>  Apply Leaves</a>
   	  	 </div>
   	  	</div>
   	  </div>
     </c:forEach>
     </div>
     
     </div>
   	 
   </div>
  </div>
  </div>

</body>
</html>