<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${ empty param.id ?'Add New Class':'Edit Class'}</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
</head>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
	integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<body>

  <c:import url="/jsp/include/navbar.jsp">
  <c:param name="view" value="classes"></c:param>
  </c:import>
  
  <div class="container">
  
   <h1 class="text-success my-3"><b>${empty param.id ? '<i class="fas fa-user-plus"></i> Add New Class':'<i class="fas fa-user-edit"></i> Edit Class'}</b></h1>
   <div class="row">
   
   <!-- Form -->
      <c:url value="/classes" var="save"></c:url>
    <sf:form action="${save}"  method="post" cssClass="col-6" modelAttribute="formSave">
    
     <sf:hidden path="id"/>
     
      <div class="form-group my-4">
       <label for="teachers" class="form-lable"><b>Teachers : </b></label>
       <sf:select path="teacher" cssClass="form-select">
       <sf:option  value="0">Select Teachers</sf:option>
       <sf:options items="${teachers}"  itemLabel="name" itemValue="id"/>
       </sf:select>
      <sf:errors path="teacher" cssClass="text-danger"></sf:errors>
      </div>
      
      <div class="row">
      
      <div class="form-group col">
      <label for="start" class="form-label"><b>Start Date : </b></label>
      <sf:input path="start" id="start" type="date" cssClass="form-control"/>
      <sf:errors path="start" cssClass="text-danger"></sf:errors>
      </div>
      
       <div class="form-group col mx-2">
       <label for="months" class="form-label"><b>Months : </b></label>
       <sf:input path="months" id="months" type="number" cssClass="form-control"/>
       <sf:errors path="months" cssClass="text-danger"></sf:errors>
      </div>
      
      </div>
      
      <div class="row my-5">
       <div class="col form-group">
        <label for="description" class="form-label"><b>Description : </b></label>
        <sf:textarea path="description" id="description" cssClass="form-control" placeholder="Enter Description !"/>
        <sf:errors path="description" cssClass="text-danger"></sf:errors>
       </div>
      </div>
      
      <div class="row">
      <div class="col-2">
      <div class="form-group">
       <button type="submit" class="btn btn-success form-control">Save <i class="fas fa-save"></i></button>
      </div>
      </div>
      </div>
      
      
     
     
    </sf:form>
   
   </div>
  
  </div>

</body>
</html>