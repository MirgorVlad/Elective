<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign in</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

     <link href="bootstrap/css/login.css" rel="stylesheet">


</head>
<body>
    <div class="login">
        <h1 class="text-center">Sign In</h1>
        <form action="controller?" method="post" class="needs-validation">
             <input name="command" value="login" type="hidden">

            <div class = "form-group was-validated">
                <label class="form-label" for="email">Email</label>
                <input class="form-control" name="email" type="email" required>
                <div class="invalid-feedback">
                    Please enter your email address
                </div>
            </div>

            <div class = "form-group was-validated">
                <label class="form-label" for="password">Password</label>
                 <input class="form-control" name="password" type="password" required>
                 <div class="invalid-feedback">
                    Please enter your password
                </div>
            </div>
      
            <input  class="btn btn-success w-100" value="SIGN IN" type="submit">
           
        </form>
    </div>

</body>
</html>
