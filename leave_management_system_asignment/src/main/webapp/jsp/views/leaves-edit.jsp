<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Application of Leave</title>
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
  <c:param name="view" value="leaves"></c:param>
  </c:import>

 <div class="container">
  <h1 class="text-success my-3"><b><i class="fas fa-file-signature"></i> Leave Application</b></h1>
  <div class="row">
  
   <c:url value="/leaves" var="l"></c:url>
   <sf:form action="${l}" method="post" cssClass="col-6" modelAttribute="leavesForm">
    <sf:hidden path="classId"/>
	<sf:hidden path="studentId"/>
    <sf:hidden path="applyDate"/>
    
     <div class="form-group my-4">
      <label for="info" class="form-label"><b>Class Information : </b></label>
      <span class="form-control">${classInfo.classInfo.description} (${classInfo.classInfo.startDate})</span>
     </div>
     
     <div class="form-group mb-4">
     <label for="teacher" class="form-label"><b>Teacher : </b></label>
     <span class="form-control">${classInfo.classInfo.teacherName}</span>
     </div>
     
     <div class="form-group mb-4">
     <label for="student" class="form-label"><b>Student : </b></label>
     <span class="form-control">${studentInfo.name}</span>
     </div>
     
     <div class="row">
      <div class="col-8">
       
       <div class="form-group mb-4">
        <label for="start" class="form-label"><b>Leave Start Date : </b></label>
        <sf:input path="startDate" cssClass="form-control" id="start" type="date"/>
        <sf:errors path="startDate" cssClass="text-danger"></sf:errors>
       </div>
      
      </div>
      
       <div class="col-4">
       <div class="form-group mb-4">
       <label for="day" class="form-label"><b>Leave Days : </b></label>
       <sf:input path="days" cssClass="form-control" id="day" type="number"/>
       <sf:errors path="days" cssClass="text-danger"></sf:errors>
       </div>
      
      </div>
     </div>
     
     <div class="form-group mb-4">
      <label for="reason" class="form-label"><b>Reasons : </b></label>
      <sf:textarea path="reason" cssClass="form-control" placeholder="Enter Reason For Leaves !" id="reason"/>
      <sf:errors path="reason" cssClass="text-danger"></sf:errors>
     </div>
     
     <div>
      <button type="submit" class="btn btn-outline-success">Save <i class="fas fa-save"></i></button>
     </div>
   
   </sf:form>
   
  
  </div>
 </div>

 
</body>
</html>