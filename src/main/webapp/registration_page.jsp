<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="${lang}">
<head>
    <title>Sign up</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

     <link href="bootstrap/css/login.css" rel="stylesheet">


</head>
<body>
    <%@include file="languge_switch.jsp" %>
    <div class="login">
        <h1 class="text-center"><fmt:message key="login.signup" /></h1>
        <form action="controller?" method="post">
            <input name="command" value="registr" type="hidden">

            <div class = "form-group was-validated">
                <label class="form-label" for="email"><fmt:message key="profile.email" /></label>
                <input name="email" type="email" class="form-control" required>
                <div class="invalid-feedback">
                    <fmt:message key="login.emailcapch" />
                </div>
            </div>

            <div class = "form-group was-validated">
                <label class="form-label" for="fName"><fmt:message key="profile.firstname" /></label>
                <input name="fName" class="form-control" required>
                <div class="invalid-feedback">
                    <fmt:message key="login.fnamecapch" />
                </div>
            </div>

    
            <div class = "form-group was-validated">
                <label class="form-label" for="lName"><fmt:message key="profile.lastname" /></label>
                <input name="lName" class="form-control" required>
                <div class="invalid-feedback">
                    <fmt:message key="login.lnamecapch" />
                </div>
            </div>

            <div class = "form-group was-validated">
                <label class="password" for="lName"><fmt:message key="login.password" /></label>
                <input name="password" type="password" class="form-control" required>
                <div class="invalid-feedback">
                    <fmt:message key="login.passcapch" />
                </div>
            </div>

            <div class="form-group from-radio">
                <fmt:message key="login.getrole" /> ...
                <input type="radio" id="teacher"  name="userRole" value="teacher" class="form-radio-input">
                <label for="teacher" class="form-radio-label"><fmt:message key="role.teacher" /></label>
                <input type="radio" id="student"  name="userRole" value="student" class="form-radio-input">
                <label for="student" class="form-radio-label"><fmt:message key="role.student" /></label> <br/>
             </div>
          

            <input value="<fmt:message key="login.signup" />" type="submit" class="btn btn-success w-100">

        </form>
    </div>
</body>
</html>
