<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registr</title>
</head>
<body>
<form action="controller?" method="post">
    <input name="command" value="registr" type="hidden">
    <sapn>Email</sapn><input name="email" type="email"><br>
    <span>First name</span><input name="fName"><br>
    <span>Last name</span><input name="lName"><br>

    <span>I am a ... </span> <input type="radio" id="teacher"  name="userRole" value="teacher">
    <label for="teacher">teacher</label>
    <input type="radio" id="student"  name="userRole" value="student">
    <label for="student">student</label> <br/>

    <span>Password</span><input name="password" type="password"><br>
    <input value="Register" type="submit">

</form>
</body>
</html>
