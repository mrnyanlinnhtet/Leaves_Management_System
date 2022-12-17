<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table class="table table-hover table-striped">

  <thead>
  <tr>
   <th>Student Name</th>
   <th>Phone</th>
   <th>Apply Date</th>
   <th>Leave Start</th>
   <th>Leave Day</th>
   <th>Reason</th>
  </tr>
  </thead>
  
  <tbody>
  <c:forEach items="${dto.leaves }" var="l">
   <tr>
    <td>${l.student}</td>
    <td>${l.studentPhone }</td>
    <td>${l.applyDate }</td>
    <td>${l.startDate }</td>
    <td>${l.days} Days</td>
    <td>${l.reason}</td>
   </tr>
   </c:forEach>
  </tbody>

</table> 