<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="table table-hover table-striped">
  <thead>
   <tr>
   <th>Registration Date</th>
   <th>Student</th>
   <th>Phone</th>
   <th></th>
   </tr>
  </thead>
  <tbody>
   <c:forEach items="${dto.registrations}" var="r">
   <tr>
     <td>${r.registrationDate }</td>
     <td>${r.student }</td>
     <td>${r.studentPhone }</td>
     <td>
     <c:url value="/classes/registration" var="regEdit">
     <c:param name="classId" value="${r.classId}"></c:param>
     <c:param name="studentId" value="${r.studentId}"></c:param>
     </c:url>
      <a href="${regEdit}" class="mx-3"><i class="fas fa-pencil-alt"></i></a>
     
     
      <c:url value="/classes/registration/${r.classId}/${r.studentId}" var="reg_detail">
      </c:url>
      <a href="${reg_detail}"><i class="fas fa-location-arrow"></i></a>
     </td>
   </tr>
   </c:forEach>
  </tbody>
</table>