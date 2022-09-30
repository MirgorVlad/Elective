<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${course.name}</title>
</head>
<body>
    <div>
        <img src="files/course_img.webp" alt="course_img">
    <h1><c:out value="${course.name}"/></h1>
    <p>Teacher: <c:out value="${course.teacher.fullName}"/></p>
    <h2>Description</h2>
    <p><c:out value="${course.description}"/></p>
    </div>
</body>
</html>
