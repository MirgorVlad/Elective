<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Access joined</title>
</head>
<body>
    <c:if test="${isJoined ne true}">
        <h1>You are follow to the ${course.name} course!</h1>
    </c:if>
    <c:if test="${isJoined eq true}">
        <h1>You are unfollow from the ${course.name} course!</h1>
    </c:if>
  <a href="controller?command=viewCourse&courseId=${course.id}">Back to course</a>
</body>
</html>
