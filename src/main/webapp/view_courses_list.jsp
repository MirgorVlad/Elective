<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Courses</title>
    <link href="bootstrap/css/courses_list.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <c:if test="${user.role eq 'student'}">
             <h1 class="fs-4" style="margin-left:-70px; margin-right:10px ;"><span class="bg-white text-dark rounded shadow px-2 me-2">EL</span><span class="text-white"><a href="student.jsp" class="navbar-brand">Elective</a><span></span></h1>
        </c:if>
        <c:if test="${user.role eq 'teacher'}">
             <h1 class="fs-4"><span class="bg-white text-dark rounded shadow px-2 me-2">EL</span><span class="text-white"><a href="teacher.jsp" class="navbar-brand">Elective</a><span></span></h1>
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

<%-- -------------------------------------------------------------- --%> 

<div class="main-container d-flex">
    <div class="sidebar" id="#side_nav">
        <div class="header-box">
            
        </div>

        <%-- --%>
        <div class="text-white">
            <h3 class="px-2 pt-3 pb-4">Sorting:</h3>
            <ul class="list-unstyled px-2 py-2">
                <li class="d-block"><a href="controller?command=sortCourses&sample=az" ><i class="fa-solid fa-arrow-up-a-z"></i> Sort by name (a-z)</a></li>
                <li class="d-block"><a href="controller?command=sortCourses&sample=za" >Sort by name (z-a)</a></li>
                <li class="d-block">Duration: <a href="controller?command=sortCourses&sample=duration&method=asc" >(asc)</a> |
                    <a href="controller?command=sortCourses&sample=duration&method=desc" >(desc)</a></li>
                <li class="d-block">Students count: <a href="controller?command=sortCourses&sample=students&method=asc" >(asc)</a> |
                    <a href="controller?command=sortCourses&sample=students&method=desc" >(desc)</a></li>
            </ul>
            <hr class="h-color mx-3">
                <h3 class="px-2 pt-3 pb-4">Selection:</h3>
                <form method="get" action="controller?" class="px-2">
                    <input type="hidden" name="command" value="selectCourses">
                    <%--By topic--%>
                    <label for="topics">Course on the topic: </label>
                    <select name="topics" id="topics" class="form-control">
                        <option value="all">all</option>
                        <c:forEach items="${topicList}" var="topic">
                            <option value="${topic}">${topic}</option>
                        </c:forEach>
                    </select>
            
                <%--By teacher--%>
                <br/>
                <label for="teachers">Course with a teacher: </label>
                <select name="teachers" id="teachers" class="form-control" style="with:100px;">
                    <option value="all">all</option>
                    <c:forEach items="${teacherList}" var="teacher">
                        <option value="${teacher.id}">${teacher.fullName}</option>
                    </c:forEach>
                </select>
                <br/>
                <input type="submit" value="Select" class="btn btn-success my-2">
            </form>

        </div>
    </div>
    <div class="content">

        <%-- -------------------------------------------------------------- --%> 
        <div class="container">
            <div class="text-center my-5">
                <h1>All Courses</h1>
                <hr/>
            </div>
           

            <div class="row">
                <c:forEach var="course" items="${coursesList}">
                    <div class="col-lg-4 col-md-6 col-sm-12">
                        <div class="card mb-5 shadow-sm">
                            <img src="files/course_img.webp" alt="course_image" class="img-fluid">

                            <div class="card-body">
                                <div class="card-title">
                                    <h2><a href="controller?command=viewCourse&courseId=${course.id}">${course.name}</a></h2>
                                </div>
                                <div class="card-text">
                                    <p>${course.description}</p>
                                </div>
                                <a href="controller?command=viewCourse&courseId=${course.id}" class="btn btn-outline-primary rounded-0 float-end">View course</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<%-- -------------------------------------------------------------- 

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

--%>


</div>
</body>
</html>
