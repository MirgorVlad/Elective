<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "co" uri = "/WEB-INF/showCourses.tld"%>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Courses</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-3">
        <div class="container">
            <a href="manager.jsp" class="navbar-brand">Elective</a>


            <div class="collapse navbar-collapse" id="navbarContent">
                <ul class="navbar-nav mr-auto mb-2">
                    <li class="nav-item">
                        <a href="controller?command=manageCourses" class="nav-link">Manage courses</a>
                    </li>
                    <li class="nav-item">
                        <a href="create_course.jsp" class="nav-link">Create course</a>
                    </li>
                    <li class="nav-item">
                        <a href="controller?command=viewAllUsers" class="nav-link">All users</a>
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
    <co:showcourses coursesList="${coursesList}"/>
</body>
</html>
