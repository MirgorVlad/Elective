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
</body>
</html>
