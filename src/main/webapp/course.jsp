<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${course.name}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-3">
        <div class="container">
            <c:if test="${user.role eq 'student'}">
                 <h1 class="fs-4" style="margin-left:-70px; margin-right:10px ;"><span class="bg-white text-dark rounded shadow px-2 me-2">EL</span><span class="text-white"><a href="student.jsp" class="navbar-brand">Elective</a><span></span></h1>
            </c:if>
            <c:if test="${user.role eq 'teacher'}">
                 <h1 class="fs-4" style="margin-left:-70px; margin-right:10px ;"><span class="bg-white text-dark rounded shadow px-2 me-2">EL</span><span class="text-white"><a href="teacher.jsp" class="navbar-brand">Elective</a><span></span></h1>
            </c:if>
            <c:if test="${user.role eq 'manager'}">
                 <h1 class="fs-4" style="margin-left:-70px; margin-right:10px ;"><span class="bg-white text-dark rounded shadow px-2 me-2">EL</span><span class="text-white"><a href="manager.jsp" class="navbar-brand">Elective</a><span></span></h1>
            </c:if>


            <div class="collapse navbar-collapse" id="navbarContent">
                <ul class="navbar-nav mr-auto mb-2">
                    <li class="nav-item">
                        <c:if test="${user.role eq 'student' or user.role eq 'teacher'}">
                            <a href="controller?command=viewCoursesList" class="nav-link">All courses</a>
                        </c:if>
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
                        <c:if test="${user.role eq 'manager'}">
                            <a href="controller?command=manageCourses" class="nav-link">Manage courses</a>
                        </c:if>
                    </li>

                    <li class="nav-item">
                        <c:if test="${user.role eq 'manager'}">
                            <a href="controller?command=viewAllUsers" class="nav-link">All users</a>
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

    <div class="container">
            <div class="row">
                <div class="col-md-5">
                    <img src="files/course_img.webp" alt="course_image">
                </div>
                <div class="col-md-7">
                    
                </div>
            </div>
    </div>




<%--    
    <div>
        <img src="files/course_img.webp" alt="course_img">
    <h1>${course.name}</h1>
        <p>Teacher: <a href = "controller?command=viewProfile&userId=${course.teacher.id}">${course.teacher.fullName}</a></p>
    <h2>Description</h2>
    <p>${course.description}</p>
        <c:if test="${user.role eq 'student'}" >
            <c:if test="${isJoined ne true}" >
                <a href = "controller?command=joinToCourse&userId=${user.id}&courseId=${course.id}">Join to course</a>
            </c:if>
            <c:if test="${isJoined eq true}" >
                <a href = "controller?command=unfollowCourse&userId=${user.id}&courseId=${course.id}">Unfollow course</a>
                <a href = "controller?command=showJournal&courseId=${course.id}">Show journal</a>
            </c:if>
        </c:if>
    </div>
--%>
</body>
</html>
