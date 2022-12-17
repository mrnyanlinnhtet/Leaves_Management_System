<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Teacher Home</title>
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
      <h1 class="text-success my-4"><b><i class="fas fa-home"></i> Teacher Home</b></h1>
     <form class="row">
      <div class="col-3 ">
      
       <div class="form-group my-3">
       <label for="date" class="form-label"><b>Target Date : </b></label>
       <input type="date" class="form-control" value="${targetDate}" id="date" name="targetDate" />
       </div>
      </div>
      
      <div class="col-3">
       <div class="form-group  my-5">
         <button type="submit" class="btn btn-outline-success">Search <i class="fas fa-search"></i></button>
        </div>
      </div>
      
     </form>
     
     <!-- End of Form -->
     
     <div class="row g-3">
     
      <c:forEach items="${list}" var="l">
       <div class="col-4">
       
       <div class="card">
       <div class="card-body">
        <h4 class="text-danger mb-2"><i class="fas fa-user-graduate"></i> ${l.teacher}</h4>
        <div class="text-secondary my-2"><i class="fas fa-book-reader"></i> ${l.details}</div>
        <span class="text-secondary mt-2"><i class="fas fa-calendar-alt"></i> ${l.startDate}</span>
        
        <div class="row mt-4">
        <div class="col-4">
        <h4 class="text-danger"><i class="fas fa-users"></i> ${l.students}</h4>
        <span class="text-secondary">Students</span>
        </div>
        
        <div class="col-4">
        <h4 class="text-danger"><i class="far fa-user-circle"></i> ${l.leaves}</h4>
        <span class="text-secondary">Leaves</span>
        </div>
        </div>
        
        <div class="row mt-4">
         <div class="col">
          <c:url value="/classes/${l.classId}" var="detail"></c:url>
        <a href="${detail}" class="btn btn-outline-primary"><i class="fas fa-location-arrow"></i> Show Details</a>
         </div>
        </div>
       </div>
       </div>
       </div>
      
      </c:forEach>
     </div>
     
     
   </div>

</body>
</html>