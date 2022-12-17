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

<c:url value="/resources/application.css" var="cssLink"></c:url>
<link rel="stylesheet" href="${cssLink}" type="text/css" />
</head>
<body>

	<c:import url="/jsp/include/navbar.jsp">
		<c:param name="view" value="classes"></c:param>
	</c:import>

	<div class="container">
		<h1 class="my-4 text-success">
			<i class="fas fa-id-card"></i> <b>Class Management</b>
		</h1>
        
        <c:url value="/classes" var="search"></c:url>
		<form class="row" action="${search}">

			<div class="col">
				<div class="form-group">
					<label for="name"><b>Teacher Name : </b></label> <input type="text" value="${param.teacher}"
						id="name" name="teacher" class="form-control" placeholder="Enter Teacher Name !" />
				</div>
			</div>

			<div class="col">
				<div class="form-group">
					<label for="from"><b>Date From : </b></label> <input type="date" value="${param.from}"
						id="from" name="from" class="form-control" />
				</div>
			</div>

			<div class="col">
				<div class="form-group">
					<label for="to"><b>Date To : </b></label> <input type="date" value="${param.to}"
						id="to" name="to" class="form-control" />
				</div>
			</div>

			<div class="col btn-wrapper">
				<button type="submit"  class="btn btn-outline-success mx-3">
					Search <i class="fas fa-search"></i>
				</button>
				<c:url value="/classes/edit" var="addNew"></c:url>
				<a href="${addNew }" class="btn btn-outline-danger">Add New <i
					class="fas fa-plus"></i></a>
			</div>

		</form>

	</div>


	<div class="container my-4">
	
	 <c:choose>
	 <c:when test="${empty list}">
	 <div class="alert alert-info">There is no techer in class !</div>
	 </c:when>
	 
	 <c:otherwise>
	 
	 <table class="table table-striped table-hover">

			<thead>
				<tr>
					<th>Id</th>
					<th>Teacher Name</th>
					<th>Teacher Phone</th>
					<th>Start Date</th>
					<th>Months</th>
					<th>Student Count</th>
					<th>Description</th>
					<th>Action</th>
				</tr>
			</thead>

			<tbody>
			<c:forEach items="${list}" var="c">
				<tr>
					<td>${c.id}</td>
					<td>${c.teacherName }</td>
					<td>${c.teacherPhone}</td>
					<td>${c.startDate}</td>
					<td>${c.months }</td>
					<td>${c.studentCount}</td>
					<td>${c.description}</td>
					<td><c:url value="/classes/edit" var="classEdit">
							<c:param name="id" value="${c.id}"></c:param>
							
						</c:url> 
						
					<a href="${classEdit}" class="mx-4"><i class="fas fa-pencil-alt"></i></a> 
					
					<c:url value="/classes/${c.id}" var="class_detail">
					</c:url>
                    <a href="${class_detail}"><i class="fas fa-location-arrow"></i></a>
						
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