<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${requestScope.currentUser.fullName}</title>
    <link href="bootstrap/css/profile.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
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

    <div class="container" style="margin-left: 60px;">
        <div class="row">
            <div class="col-md-6">
                <div class="profile-links">
                    <img src="files/profile.png" alt="profile_image" >
                    <li><a href="">${requestScope.currentUser.email}</a></li>
                    <c:if test="${user.role eq 'student'}">
                        <li><a href="student.jsp" class="bold">${requestScope.currentUser.role}</a></li>
                    </c:if>
                    <c:if test="${user.role eq 'teacher'}">
                        <li><a href="teacher.jsp" class="bold">${requestScope.currentUser.role}</a></li>
                    </c:if>
                    <c:if test="${user.role eq 'manager'}">
                        <li><a href="manager.jsp" class="bold">${requestScope.currentUser.role}</a></li>
                    </c:if>
                </div>
            </div>
             <div class="col-md-6">
                <div class="mt-custum">
                    <div class="details">
                        <div class="row">
                            <div class="col-8">
                                First name
                            </div>
                            <div class="col-4">
                                ${requestScope.currentUser.firstName}
                            </div>
                        </div>
                    </div>
                    <div class="details">
                        <div class="row">
                            <div class="col-8">
                                Last name
                            </div>
                            <div class="col-4">
                                ${requestScope.currentUser.lastName}
                            </div>
                        </div>
                    </div>
                    <div class="details">
                        <div class="row">
                            <div class="col-8">
                                Email
                            </div>
                            <div class="col-4">
                                ${requestScope.currentUser.email}
                            </div>
                        </div>
                    </div>
                    <div class="details">
                        <div class="row">
                            <div class="col-8">
                                Role
                            </div>
                            <div class="col-4">
                                ${requestScope.currentUser.role}
                            </div>
                        </div>
                    </div>
                </div>
                <h3 class="mt-5">About Me</h3>
                <p class="text-justify">
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                    tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                    quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                    consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                    cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
                    proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                </p>
            </div>
        </div>
    </div>

   
    <%-- <img src="files/profile_img.jpg" alt="profile_image">
    <p>First name: ${requestScope.currentUser.firstName}</p>
    <p>Last name: ${requestScope.currentUser.lastName}</p>
    <p>Email: ${requestScope.currentUser.email}</p>
    <p>Role: ${requestScope.currentUser.role}</p>--%>
</body>
</html>
