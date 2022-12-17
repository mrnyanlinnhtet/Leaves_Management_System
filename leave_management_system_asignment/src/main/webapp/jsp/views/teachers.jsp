<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Teacher Management</title>
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

		<h1 class="my-4 text-success">
		<i class="fas fa-user"></i>	<b>Teachers Management</b> 
		</h1>
       <c:url value="/teachers" var="search"></c:url>
		<form action="${search}" class="row">

			<div class="col">
				<div class="form-group">
					<label for="name"><b>Name : </b></label> <input type="text" value="${param.name}"
						id="name" class="form-control" placeholder="Enter Name !" name="name" />
				</div>
			</div>

			<div class="col">
				<div class="form-group">
					<label for="phone"><b>Phone : </b></label> <input type="tel" value="${param.phone}"
						id="phone" class="form-control" placeholder="Enter Phone Number !" name="phone" />
				</div>
			</div>

			<div class="col">
				<div class="form-group">
					<label for="email"><b>Email : </b></label> <input type="email" value="${param.email}"
						id="email" class="form-control" placeholder="Enter Email Address !" name="email" />
				</div>
			</div>

			<div class="col btn-wrapper">
				<button class="btn btn-outline-success mx-2">Search  <i class="fas fa-search"></i></button>
				<c:url value="/teachers/edit" var="addNew"></c:url>
				<a href="${addNew}" class="btn btn-outline-danger">Add New  <i class="fas fa-plus"></i></a>
			</div>

		</form>
	</div>

	<div class="container my-4">
	
	  <c:choose>
	   <c:when test="${empty list}">
	   <div class="alert alert-info">There is no data !</div>
	   </c:when>
	   
	   <c:otherwise>
	   <table class="table table-striped table-hover">

			<thead>
			
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Phone</th>
					<th>Email</th>
					<th>Assign Date</th>
					<th>Classes</th>
					<th>Actions</th>
				</tr>
			</thead>

			<tbody>
			<c:forEach items="${list}" var="t">
			<tr>
					<td>${t.id}</td>
					<td>${t.name}</td>
					<td>${t.phone}</td>
					<td>${t.email}</td>
					<td>${t.assignDate}</td>
					<td>${t.classCount }</td>
					<td><c:url value="/teachers/edit" var="teacherEdit">
							<c:param name="id" value="${t.id}"></c:param>
						</c:url> <a href="${teacherEdit}"><i class="fas fa-pencil-alt"></i></a>
					</td>
				</tr>
			</c:forEach>
				
			</tbody>

		</table>
	   </c:otherwise>
	  </c:choose>

		

	</div>


</body>
</html>