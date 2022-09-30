<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Courses</title>
</head>
<body>
    <table border="1">
        <caption>All Courses</caption>
        <tr>
            <th>Name</th>
            <th>Start date</th>
            <th>End date</th>
            <th>Teacher</th>
            <th>Email</th>
            <th>EDIT</th>
            <th>DELETE</th>
        </tr>
        <c:forEach var="course" items="${sessionScope.coursesList}">
            <tr>
                <th><c:out value="${course.name}"/> </th>
                <th><c:out value="${course.startDate}"/></th>
                <th><c:out value="${course.finishDate}"/></th>
                <th><c:out value="${course.teacher.fullName}"/></th>
                <th><c:out value="${course.teacher.email}"/></th>
                <th><a href="controller?command=editCourse">EDIT</a></th>
                <th><a href="controller?command=deleteCourse&courseId=${course.id}" methods="POST">DELETE</a></th>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
