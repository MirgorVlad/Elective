<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

     <link href="bootstrap/css/login.css" rel="stylesheet">


</head>
<body>
    <div class="login">
        <h1 class="text-center">Sign up</h1>
        <form action="controller?" method="post">
            <input name="command" value="registr" type="hidden">

            <div class = "form-group was-validated">
                <label class="form-label" for="email">Email</label>
                <input name="email" type="email" class="form-control" required>
                <div class="invalid-feedback">
                    Please enter your email address
                </div>
            </div>

            <div class = "form-group was-validated">
                <label class="form-label" for="fName">First name</label>
                <input name="fName" class="form-control" required>
                <div class="invalid-feedback">
                    Please enter your first name
                </div>
            </div>

    
            <div class = "form-group was-validated">
                <label class="form-label" for="lName">Last name</label>
                <input name="lName" class="form-control" required>
                <div class="invalid-feedback">
                    Please enter your last name
                </div>
            </div>

            <div class = "form-group was-validated">
                <label class="password" for="lName">Password</label>
                <input name="password" type="password" class="form-control" required>
                <div class="invalid-feedback">
                    Please enter your password
                </div>
            </div>

            <div class="form-group from-radio">
                I'm a ... 
                <input type="radio" id="teacher"  name="userRole" value="teacher" class="form-radio-input">
                <label for="teacher" class="form-radio-label">teacher</label>
                <input type="radio" id="student"  name="userRole" value="student" class="form-radio-input">
                <label for="student" class="form-radio-label">student</label> <br/>
             </div>
          

            <input value="SIGN UP" type="submit" class="btn btn-success w-100">

        </form>
    </div>
</body>
</html>
