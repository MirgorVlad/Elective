
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Student</title>
        <link href="bootstrap/css/welcome_page.css" rel="stylesheet">
     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
         <h1 class="fs-4" style="margin-left:-70px; margin-right:10px ;"><span class="bg-white text-dark rounded shadow px-2 me-2">EL</span><span class="text-white"><a href="student.jsp" class="navbar-brand">Elective</a><span></span></h1>
        

        <div class="collapse navbar-collapse" id="navbarContent">
            <ul class="navbar-nav mr-auto mb-2">
                <li class="nav-item">
                     <a href="controller?command=viewCoursesList" class="nav-link">All courses</a>
                </li>
                <li class="nav-item">
                     <a href="controller?command=viewStudentAvailableCourses" class="nav-link">My courses</a>
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
      <div class="row custom-section d-flex align-items-center">
        <div class="col-12 col-lg-4">
          <h1>You are student</h1>
          <h3>Text</h3>
          <p>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
          tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
          quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
          </p>
        
        </div>
        <div class="col-12 col-lg-5">
          <img src="files/student.png" alt="welcome">
        </div>
      </div>
    </div>



</body>
</html>
