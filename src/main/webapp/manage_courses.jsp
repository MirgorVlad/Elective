<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "co" uri = "/WEB-INF/showCourses.tld"%>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Courses</title>
</head>
<body>
    <%--<table border="1">
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
        <c:forEach var="course" items="${coursesList}">
            <tr>
                <th><a href="controller?command=viewCourse&courseId=${course.id}"><c:out value="${course.name}"/></a></th>
                <th><c:out value="${course.startDate}"/></th>
                <th><c:out value="${course.finishDate}"/></th>
                <th><c:out value="${course.teacher.fullName}"/></th>
                <th><c:out value="${course.teacher.email}"/></th>
                <th><a href="edit_course.jsp?courseId=${course.id}&name=${course.name}&description=${course.description}
                &teacher=${course.teacher.email}&start=${course.startDate}&finish=${course.finishDate}">EDIT</a></th>
                <th><a href="controller?command=deleteCourse&courseId=${course.id}">DELETE</a></th>
            </tr>
        </c:forEach>
    </table> --%>
    <co:showcourses coursesList="${coursesList}" role="manager"/>
</body>
</html>
