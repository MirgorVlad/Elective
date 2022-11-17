<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Video</title>
    <link rel="stylesheet" href="bootstrap/css/materials.css">
</head>
<body>

<%@include file="menu.jsp" %>

<div class="container">
    <div class="row">
        <form action="controller?" method="post">
            <input name="command" value="addMaterials" type="hidden">
            <input name="courseId" value="${param.courseId}" type="hidden">
            <h4>Title: <input name="title" class="form-control"></h4>
            <h4>Text:</h4>
            <textarea name="description" class="form-control" rows="10"></textarea>
            <br/>
            <h4>Video link: <input name="videoPath" class="form-control"></h4>
            <input value="Add" type="submit" class="btn btn-success my-3">
        </form>
    </div>
</div>
</body>
</html>
