<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${empty param.id?'Add New Teacher':'Edit Teacher' }</title>
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
<c:url value="/resources/application.css" var="cssLink"></c:url>
<link rel="stylesheet" href="${cssLink}" />
</head>
<body>

	<c:import url="/jsp/include/navbar.jsp">
		<c:param name="view" value="teachers"></c:param>
	</c:import>
	
	<div class="container">
	
	  <h1 class="text-success my-4"><b>${empty param.id?'<i class="fas fa-user-check"></i> Add New Data':'<i class="fas fa-user-edit"></i> Edit Data' }</b></h1>
	 
	    <div class="row">
	    
	    <c:url value="/teachers" var="teacherSaveLink"></c:url>
	     <sf:form action="${teacherSaveLink }" method="post" modelAttribute="form" cssClass="col-6">
	      <sf:hidden path="id"/>
	      
	     <div class="form-group">
	       <label for="name" class="form-label"><b>Teacher Name : </b></label>
	       <sf:input path="name" type="text" id="name" placeholder="Enter Teacher Name !" cssClass="form-control"/>
	       <sf:errors path="name" cssClass="text-danger"></sf:errors>
	      </div>
	     
	      <div class="form-group my-4">
	       <label for="phone" class="form-label"><b>Teacher's Phone Number : </b></label>
	       <sf:input path="phone" type="tel" id="phone" placeholder="Enter Teacher's Phone Number !" cssClass="form-control"/>
	       <sf:errors path="phone" cssClass="text-danger"></sf:errors>
	      </div>
	      
	      <div class="form-group my-4 ${empty param.id?'':'d-none'}">
	       <label for="email" class="form-label"><b>Teacher's Email : </b></label>
	       <sf:input path="email" type="email" id="email" placeholder="Enter Teacher's Email !" cssClass="form-control"/>
	       <sf:errors path="email" cssClass="text-danger"></sf:errors>
	      </div>
	      
	      <div class="form-group my-4">
	       <label for="date" class="form-label"><b>Assign Date : </b></label>
	       <sf:input path="assignDate" id="date" type="date" cssClass="form-control"/>
	       <sf:errors path="assignDate" cssClass="text-danger"></sf:errors>
	      </div>
	      
	      <div class="form-group">
	      <button type="submit" class="btn btn-outline-success"> <i class="fas fa-save"></i> Save Data</button>
	      </div>
	      
	     
	     </sf:form>
	    
	    </div>
	  
	</div>

</body>
</html>