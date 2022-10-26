<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${lang}">
<head>
  <title>Menu</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</head>
<body>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
      <h1 class="fs-4" style="margin-left:-70px; margin-right:10px ;">
        <span class="bg-white text-dark rounded shadow px-2 me-2">EL</span>
          <span class="text-white">
            <c:if test="${user.role eq 'manager'}">
              <a href="manager.jsp" class="navbar-brand">Elective</a>
            </c:if>
            <c:if test="${user.role eq 'student'}">
              <a href="student.jsp" class="navbar-brand">Elective</a>
            </c:if>
            <c:if test="${user.role eq 'teacher'}">
              <a href="teacher.jsp" class="navbar-brand">Elective</a>
            </c:if>
          </span>
      </h1>


      <div class="collapse navbar-collapse" id="navbarContent">
        <ul class="navbar-nav mr-auto mb-2">
          <li class="nav-item">
            <c:if test="${user.role eq 'manager'}">
              <a href="controller?command=manageCourses" class="nav-link"><fmt:message key="menu.manage" /></a>
            </c:if>
            <c:if test="${(user.role eq 'teacher') or (user.role eq 'student')}">
              <a href="controller?command=viewCoursesList" class="nav-link"><fmt:message key="menu.allcourses" /></a>
            </c:if>
          </li>
          <li class="nav-item">
            <c:if test="${user.role eq 'manager'}">
              <a href="controller?command=viewAllUsers" class="nav-link"><fmt:message key="menu.users" /></a>
            </c:if>
            <c:if test="${user.role eq 'teacher'}">
              <a href="controller?command=viewTeacherAvailableCourses" class="nav-link"><fmt:message key="menu.mycourses" /></a>
            </c:if>
            <c:if test="${user.role eq 'student'}">
              <a href="controller?command=viewStudentAvailableCourses" class="nav-link"><fmt:message key="menu.mycourses" /></a>
            </c:if>
          </li>
          <li class="nav-item">
            <a href="controller?command=viewProfile&userId=${user.id}" class="nav-link"><fmt:message key="menu.myprofile" /></a>
          </li>
        </ul>
      </div>
      <form action="controller?" method="post" class="d-flex">
        <input name="command" value="logout" type="hidden" class="form-control mr-2">
        <button class="btn btn-outline-danger mr-auto mt-2"><fmt:message key="menu.logout" /></button>
      </form>
    </div>
  </nav>
</body>
</html>
