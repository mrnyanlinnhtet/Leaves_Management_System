<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:url value="/home" var="home"/>
<c:url value="/teachers" var="teachers"/>
<c:url value="/classes" var="classes"/>
<c:url value="/students" var="students"/>
<c:url value="/leaves" var="leaves"/>
<c:url value="/signout" var="signout"/>
<c:url value="/resources/signout.js" var="singoutJs"></c:url>

    <nav class="navbar navbar-expand-lg navbar-dark bg-success">
 
 <div class="container my-2">
   
   <a href="${home}" class="navbar-brand"><b><i class="fas fa-chalkboard-teacher"></i> Leave Registration System</b></a>
   
    <ul class="navbar-nav">
    <!-- Authority For Teacher Role -->
      <sec:authorize access="hasAuthority('Admin')">
      <li class="nav-item">
      <a href="${teachers}" class="nav-link ${param.view eq 'teachers' ? 'active':'' }"> <i class="fas fa-user-graduate"></i> Teachers</a>
      </li>
      </sec:authorize>
      
      <!-- Authority For Classes and Student Role -->
      <sec:authorize  access="hasAnyAuthority('Admin','Teacher')">
      <li class="nav-item">
      <a href="${classes}" class="nav-link ${param.view eq 'classes' ? 'active':'' }"><i class="fas fa-graduation-cap"></i> Classes</a>
      </li>
      
      <li class="nav-item">
      <a href="${students}" class="nav-link ${param.view eq 'students' ? 'active':'' }"><i class="fas fa-users"></i> Students</a>
      </li>
      </sec:authorize>
      
      <!-- Authority For leaves  -->
      <sec:authorize access="hasAuthority('Student')">
      <li class="nav-item">
      <a href="${leaves}" class="nav-link ${param.view eq 'leaves' ? 'active':'' }"><i class="fas fa-address-book"></i> Leaves</a>
      </li>
      </sec:authorize>
      <li class="nav-item">
      <a  id="signoutLink" class="nav-link" style="cursor: pointer;"><i class="fas fa-user-lock"></i> Sign Out</a>
      </li>
    
    </ul>
 </div>
 
 </nav>
 
     <sf:form id="signoutForm" method="post" action="${signout}" cssClass="d-none" ></sf:form>
     <script src="${singoutJs}" type="text/javascript"></script>
     
     
  