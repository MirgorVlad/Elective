<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="${lang}">
<head>
    <title>${course.name}</title>
    <link href="bootstrap/css/course.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
    <%@include file="menu.jsp" %>

   <div class="container">
            <div class="row">
                <div class="col-md-8" style="margin-left:-120px;">
                    <img src="files/course_img.webp" alt="course_image" class="d-block w-200">
                </div>
                <div class="col-md-3 w-200">
                    <p class="newarrival"></p>
                    <h1>${course.name}</h1>
                    <p><b><fmt:message key="course.teacher" />:</b> <a href = "controller?command=viewProfile&userId=${course.teacher.id}" class="text-decoration-none">${course.teacher.fullName}</a></p>
                    <p><b><fmt:message key="course.start" />: </b>${course.startDate}</p>
                    <p><b><fmt:message key="course.end" />: </b>${course.finishDate}</p>
                    <h3><fmt:message key="course.description" />: </h3>
                    <p>${course.description}</p>
                    <c:if test="${course.finished eq true}">
                        <h2 style="color: red"><fmt:message key="course.isfinished"/></h2>
                    </c:if>
                     <c:if test="${user.role eq 'student'}" >
                        <c:if test="${isJoined ne true}" >
                            <c:if test="${course.finished ne true}">
                                <a href = "controller?command=joinToCourse&userId=${user.id}&courseId=${course.id}" class="btn btn-outline-success"><fmt:message key="course.jointo"/></a>
                            </c:if>
                        </c:if>
                        <c:if test="${isJoined eq true}" >
                            <c:if test="${course.finished ne true}">
                                <a href = "controller?command=unfollowCourse&userId=${user.id}&courseId=${course.id}" class="btn btn-outline-danger"><fmt:message key="course.unfollowfrom"/></a>
                            </c:if>
                            <a href = "controller?command=showJournal&courseId=${course.id}&page=1" class="btn btn-outline-primary"><fmt:message key="course.viewjournal"/></a>
                        </c:if>
                    </c:if>
                    <c:if test="${user.role eq 'teacher'}">
                        <c:if test="${user.email eq course.teacher.email}">
                            <a href = "controller?command=showJournal&courseId=${course.id}&page=1" class="btn btn-outline-primary"><fmt:message key="course.viewjournal"/>l</a>
                        </c:if>
                    </c:if>
                </div>
            </div>
    </div>
</body>
</html>
