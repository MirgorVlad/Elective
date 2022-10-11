<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Courses</title>
</head>
<body>
<c:forEach var="course" items="${coursesList}">

  <div style="border: black solid 2px; display: inline-block;">
      <img src="files/course_img.webp" alt="course_image" width="150px"/><br/>
   <a href="controller?command=viewCourse&courseId=${course.id}"><c:out value="${course.name}"/></a>
    <p><c:out value="${course.startDate}"/> - <c:out value="${course.finishDate}"/></p>
    <p><c:out value="${course.teacher.fullName}"/></p>
    <p><c:out value="${course.teacher.email}"/></p>
  </div>

</c:forEach>
<br/>
<div id="sort">
    <p><a href="controller?command=sortCourses&sample=az" >Sort by name (a-z)</a></p>
    <p><a href="controller?command=sortCourses&sample=za" >Sort by name (z-a)</a></p>
    <p>Sort by duration: <a href="controller?command=sortCourses&sample=duration&method=asc" >(asc)</a> |
        <a href="controller?command=sortCourses&sample=duration&method=desc" >(desc)</a></p>
    <p>Sort by students count: <a href="controller?command=sortCourses&sample=students&method=asc" >(asc)</a> |
        <a href="controller?command=sortCourses&sample=students&method=desc" >(desc)</a></p>
</div>
</body>
</html>
