<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${lang}">
<head>
    <title>Assignment</title>
    <link rel="stylesheet" href="bootstrap/css/materials.css">
</head>
<body>

<%@include file="menu.jsp" %>

    <div class="center">
    <div class="row_section">
        <form action="controller?" method="post" onsubmit="return checkDeadline()">
            <input name="command" value="addMaterials" type="hidden">
            <input name="courseId" value="${param.courseId}" type="hidden">
            <h4><fmt:message key="material.title" />: <input name="title" class="form-control"></h4>
            <h4><fmt:message key="material.text" />:</h4>
            <textarea name="description" class="form-control" rows="10"></textarea>
            <br/>
            <h4><fmt:message key="material.deadline" />: <input type="date" name="deadline" id="deadline" class="form-control"></h4>
            <input value="<fmt:message key="material.upload" />" type="submit" class="btn btn-success my-3">
        </form>
    </div>
    </div>
<script src="bootstrap/js/assignment.js"></script>
</body>
</html>
