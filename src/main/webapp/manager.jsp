<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Manager</title>
    <link href="bootstrap/css/manager.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
   <nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-3">
        <div class="container">
        <a href="" class="navbar-brand">Elective</a>
        

        <div class="collapse navbar-collapse" id="navbarContent">
            <ul class="navbar-nav mr-auto mb-2">
                <li class="nav-item">
                     <a href="controller?command=manageCourses" class="nav-link">Manage courses</a>
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

    <%--<a href="create_course.jsp" class="nav-link" data-bs-toggle="modal">Create course</a>--%>
    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal">Create course</button>
    <div class="modal" id="myModal">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Create course</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
              <form action="controller?" method="post">
                <input name="command" value="createCourse" type="hidden">
                <div class="mb-3">
                  <label class="form-label">Name</label>
                   <input name="name" class="form-control">
                </div>
               
                <div class="mb-3">
                  <label for="topics" class="form-label">Topic</label>
                  <select name="topics" id="topics" class="form-control">
                    <c:forEach items="${topicList}" var="topic">
                      <option value="${topic}">${topic}</option>
                    </c:forEach>
                  </select>
                </div>
                 <div class="mb-3">
                  <label class="form-label">Teacher</label>
                  <input name="teacherEmail" type="email" class="form-control">
                </div>
                <div class="mb-3">
                  <label class="form-label">Start date</label>
                  <input name="startDate" type="date" class="form-control">
                </div>
                <div class="mb-3">
                  <label class="form-label">Finish date</label>
                  <input name="finishDate" type="date" class="form-control">
                </div>
                <div class="mb-3">
                  <label class="form-label">Description</label>
                  <textarea name="description" class="form-control"></textarea>
                </div>
                <button type="submit" class="btn btn-primary">Create</button>
            </form>
            </div>
          </div>
        </div>
    </div>
</body>
</html>
