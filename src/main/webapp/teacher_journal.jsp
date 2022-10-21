<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "ex" uri = "/WEB-INF/courseLib.tld"%>
<html>
<head>
    <title>Journal</title>
    <link href="bootstrap/css/table.css" rel="stylesheet">
    <link href="bootstrap/css/journal.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-3">
        <div class="container">
             <h1 class="fs-4" style="margin-left:-70px; margin-right:10px ;"><span class="bg-white text-dark rounded shadow px-2 me-2">EL</span><span class="text-white"><a href="teacher.jsp" class="navbar-brand">Elective</a><span></span></h1>


            <div class="collapse navbar-collapse" id="navbarContent">
                <ul class="navbar-nav mr-auto mb-2">
                    <li class="nav-item">
                        <a href="controller?command=viewCoursesList" class="nav-link">All courses</a>
                    </li>
                    <li class="nav-item">
                        <a href="controller?command=viewTeacherAvailableCourses" class="nav-link">My courses</a>
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
    <div class="center">
        <ex:showjournal course="${course}" studentsList="${studentsList}"/>
        

        <%--<a href="edit_journal.jsp?courseId=${course.id}">Edit</a>--%>
         <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#myModal">Set grade</button>
    </div>
    <div class="modal" id="myModal">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Set grade</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
              <form action="controller?" method="post">
                <input type="hidden" name="command" value="editJournal">
                <input type="hidden" name="courseId" value="${param.courseId}">
                <div class="mb-3">
                  <label class="form-label" for="students">Student</label>
                    <select name="students" id="students" class="form-control">
                          <c:forEach items="${studentsList}" var="student">
                            <option value="${student.email}">${student.fullName}</option>
                          </c:forEach>
                    </select>
                </div>
               
                 <div class="mb-3">
                  <label class="form-label">Date</label>
                  <input name="date" type="date" class="form-control">
                </div>
                <div class="mb-3">
                  <label class="form-label">Grade</label>
                  <input name="grade" class="form-control">
                </div>
                <button type="submit" class="btn btn-primary">Set grade</button>
            </form>
            </div>
          </div>
        </div>
    </div>
</body>
</html>
