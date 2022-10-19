<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Courses</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-3">
    <div class="container">
        <c:if test="${user.role eq 'student'}">
            <a href="student.jsp" class="navbar-brand">Elective</a>
        </c:if>
        <c:if test="${user.role eq 'teacher'}">
            <a href="teacher.jsp" class="navbar-brand">Elective</a>
        </c:if>



        <div class="collapse navbar-collapse" id="navbarContent">
            <ul class="navbar-nav mr-auto mb-2">
                <li class="nav-item">
                    <a href="controller?command=viewCoursesList" class="nav-link">All courses</a>
                </li>
                <li class="nav-item">
                    <c:if test="${user.role eq 'student'}">
                        <a href="controller?command=viewStudentAvailableCourses" class="nav-link">My courses</a>
                    </c:if>
                    <c:if test="${user.role eq 'teacher'}">
                        <a href="controller?command=viewTeacherAvailableCourses" class="nav-link">My courses</a>
                    </c:if>
                </li>
                <li class="nav-item">
                    <a href="controller?command=viewProfile&userId=${user.id}" class="nav-link">My profile</a>
                </li>
            </ul>
        </div>
        <form action="controller?" method="post" class="d-flex">
            <input name="command" value="logout" type="hidden" class="form-control mr-2">
            <button class="btn btn-outline-danger mr-auto mt-2">Log out</button>
        </form>
    </div>
</nav>

<c:forEach var="course" items="${coursesList}">
  <div style="border: black solid 2px; display: inline-block;">
      <img src="files/course_img.webp" alt="course_image" width="150px"/><br/>
   <a href="controller?command=viewCourse&courseId=${course.id}"><c:out value="${course.name}"/></a>
    <p><c:out value="${course.startDate}"/> - <c:out value="${course.finishDate}"/></p>
    <p><c:out value="${course.teacher.fullName}"/></p>
    <p><c:out value="${course.teacher.email}"/></p>
  </div>
</c:forEach>
<br/>
<div id="sort">
    <p><b>Sorting:</b></p>
    <p><a href="controller?command=sortCourses&sample=az" >Sort by name (a-z)</a></p>
    <p><a href="controller?command=sortCourses&sample=za" >Sort by name (z-a)</a></p>
    <p>Sort by duration: <a href="controller?command=sortCourses&sample=duration&method=asc" >(asc)</a> |
        <a href="controller?command=sortCourses&sample=duration&method=desc" >(desc)</a></p>
    <p>Sort by students count: <a href="controller?command=sortCourses&sample=students&method=asc" >(asc)</a> |
        <a href="controller?command=sortCourses&sample=students&method=desc" >(desc)</a></p>
    <p><b>Selection:</b></p>
    <form method="get" action="controller?">
        <input type="hidden" name="command" value="selectCourses">
        <%--By topic--%>
        <label for="topics">Course on the topic: </label>
        <select name="topics" id="topics">
            <option value="all">all</option>
            <c:forEach items="${topicList}" var="topic">
                <option value="${topic}">${topic}</option>
            </c:forEach>
        </select>
        <%--By teacher--%>
        <br/>
        <label for="teachers">Course with a teacher: </label>
        <select name="teachers" id="teachers">
            <option value="all">all</option>
            <c:forEach items="${teacherList}" var="teacher">
                <option value="${teacher.id}">${teacher.fullName}</option>
            </c:forEach>
        </select>
        <input type="submit" value="Select">
    </form>
</div>
</body>
</html>
