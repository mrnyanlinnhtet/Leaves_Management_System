<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${not empty param.studentId > '0' ? 'Edit' : 'Add New'} Registration</title>
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
    <h1 class="text-success my-4"><i class="fas fa-user-edit"></i> ${not empty param.studentId > '0' ? 'Edit' : 'Add New'} Registration</h1>
     
      <div class="row">
      
     
       <sf:form modelAttribute="regiForm" method="post" cssClass="col-6">
        <sf:hidden path="studentId"/>
        <sf:hidden path="classId"/>
        <sf:hidden path="registrationDate"/>
        
        <div class="form-group mb-4">
        <div class="form-label"><b>Start Date : </b></div>
        <span class="form-control">${not empty param.startDate ? param.startDate : classInfo.startDate}</span>
        </div>
        
        <div class="form-group mb-4">
        <div class="form-label"><b>Teacher : </b></div>
        <span class="form-control">${not empty param.teacherName ? param.teacherName : classInfo.teacherName}</span>
        </div>
        
        <div class="form-group mb-4">
        <label for="name" class="form-label"><b>Student Name : </b></label>
        <sf:input path="studentName" type="text" cssClass="form-control" placeholder="Enter Student Name !" id="name"/>
        <sf:errors path="studentName" cssClass="text-danger"></sf:errors>
        </div>
        
        <div class="form-group mb-4">
        <label for="email" class="form-label"><b>Email Address : </b></label>
        <sf:input path="email" type="email" cssClass="form-control" placeholder="Enter Email Address !" id="email"/>
        <sf:errors path="email" cssClass="text-danger"></sf:errors>
        </div>
        
        <div class="form-group mb-4">
        <label for="phone" class="form-label"><b>Phone Number : </b></label>
        <sf:input path="phone" type="tel" cssClass="form-control" placeholder="Enter Phone Number !" id="phone"/>
        <sf:errors path="phone" cssClass="text-danger"></sf:errors>
        </div>
        
        <div class="form-group mb-4">
        <label for="edu" class="form-label"><b>Last Education : </b></label>
        <sf:input path="education" cssClass="form-control" placeholder="Enter Last Education !" id="edu"/>
        <sf:errors path="education" cssClass="text-danger"></sf:errors>
        </div>
        
        <div>
         <button type="submit" class="btn btn-outline-success"><i class="fas fa-save"></i> Save</button>
        </div>
       
       </sf:form>
      
      </div>
    
  </div>
</body>
</html>