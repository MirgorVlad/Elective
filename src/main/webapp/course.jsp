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
        <p>Teacher: <a href = "controller?command=viewProfile&userId=${course.teacher.id}"> <c:out value="${course.teacher.fullName}"/></a></p>
    <h2>Description</h2>
    <p><c:out value="${course.description}"/></p>
        <c:if test="${user.role eq 'student'}" >
            <c:if test="${isJoined ne true}" >
                <a href = "controller?command=joinToCourse&userId=${user.id}&courseId=${course.id}">Join to course</a>
            </c:if>
            <c:if test="${isJoined eq true}" >
                <a href = "controller?command=unfollowCourse&userId=${user.id}&courseId=${course.id}">Unfollow course</a>
                <a href = "controller?command=showJournal&courseId=${course.id}">Show journal</a>
            </c:if>
        </c:if>
    </div>
</body>
</html>
