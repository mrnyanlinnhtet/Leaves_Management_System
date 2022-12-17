<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Leave Information</title>
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
   <h1 class="text-success my-3"><b><i class="fas fa-address-book"></i> Leaves</b></h1>
      
      <form class="mt-4 row">
      
       <div class="col-2">
       <div class="form-group">
       <label for="from" class="form-label"><b>Date From : </b></label>
       <input type="date" class="form-control" name="from" id="from"/>
       </div>
       </div>
       
       <div class="col-2">
       <div class="form-group">
       <label for="to" class="form-label"><b>Date To : </b></label>
       <input type="date" class="form-control" name="to" id="to"/>
       </div>
       </div>
       
       <div class="col-2">
        <button type="submit" class="btn btn-outline-danger" style="margin-top: 30px">Search <i class="fas fa-search"></i></button>
       </div>
      
      </form>
     
      <div class="container mt-5">
        <table class="table table-striped table-hover">
        
         <thead>
          <tr>
           <th>Class Information</th>
           <th>Teacher</th>
           <th>Apply At</th>
           <th>Leave Start</th>
           <th>Leave Days</th>
           <th>Reason</th>
          </tr>
         </thead>
         
         <tbody>
         <c:forEach items="${list}" var="l">
          <tr>
          <td>${l.classInfo}</td>
          <td>${l.teacher}</td>
          <td>${l.applyDate }</td>
          <td>${l.startDate}</td>
          <td>${l.days} Days</td>
          <td>${l.reason}</td>
          </tr>
          </c:forEach>
         </tbody>
        
        </table>
       
      </div>
  
  </div>


</body>
</html>