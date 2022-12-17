<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Information</title>
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
		<c:param name="view" value="students"></c:param>
	</c:import>

	<div class="container">

		<h1 class="text-success my-4">
			<i class="fas fa-user-graduate"></i> <b>Student Management</b>
		</h1>

         <c:url value="/students" var="search"></c:url>
		<form class="row" action="${search}">

			<div class="col">
				<div class="form-group">
					<label for="name"><b>Name : </b></label> <input type="text" value="${param.name}"
					name="name" class="form-control" placeholder="Enter Student Name !" id="name" />
				</div>
			</div>

			<div class="col">
				<div class="form-group">
					<label for="phone"><b>Phone : </b></label> <input type="tel" value="${param.phone}"
					name="phone" class="form-control" placeholder="Enter Phone Number !" id="phone" />
				</div>
			</div>

			<div class="col">
				<div class="form-group">
					<label for="email"><b>Email : </b></label> <input type="email" value="${param.email}"
					name="email" class="form-control" placeholder="Enter Email Address !"
						id="email" />
				</div>
			</div>
			
			<div class="col btn-wrapper">
			   <button type="submit" class="btn btn-outline-success">Search  <i class="fas fa-search"></i></button>
			</div>

		</form>

		<div class="container my-4">

			<table class="table table-striped table-hover">

				<thead>
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>Phone</th>
						<th>Email</th>
						<th>Education</th>
						<th>Class Count</th>
					</tr>
				</thead>

				<tbody>
				<c:forEach items="${studentDto}" var="s">
					<tr>
						<td>${s.id}</td>
						<td>${s.name}</td>
						<td>${s.phone}</td>
						<td>${s.email}</td>
						<td>${s.education}</td>
						<td>${s.classCount}</td>
					</tr>
					</c:forEach>
				</tbody>

			</table>

		</div>


	</div>

</body>
</html>