<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${lang}">
<head>
    <title>Courses</title>
    <link href="bootstrap/css/courses_list.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>

    <%@include file="menu.jsp" %>
<%-- -------------------------------------------------------------- --%> 

    <div class="main-container d-flex">
        <div class="sidebar" id="#side_nav">
            <div class="header-box">

            </div>

            <%-- --%>
            <div class="text-white">
                <h3 class="px-2 pt-3 pb-4"><fmt:message key="sidenav.sorting" />:</h3>
                <ul class="list-unstyled px-2 py-2">
                    <li class="d-block"><a href="controller?command=sortCourses&sample=az" ><i class="fa-solid fa-arrow-up-a-z"></i><fmt:message key="sidenav.sortbynameaz" /></a></li>
                    <li class="d-block"><a href="controller?command=sortCourses&sample=za" ><fmt:message key="sidenav.sortbynameza" /></a></li>
                    <li class="d-block"><fmt:message key="sidenav.sortbydur" />: <a href="controller?command=sortCourses&sample=duration&method=asc" >(<fmt:message key="sidenav.asc" />)</a> |
                        <a href="controller?command=sortCourses&sample=duration&method=desc" >(<fmt:message key="sidenav.desc" />)</a></li>
                    <li class="d-block"><fmt:message key="sidenav.sortbystudent" />: <a href="controller?command=sortCourses&sample=students&method=asc" >(<fmt:message key="sidenav.asc" />)</a> |
                        <a href="controller?command=sortCourses&sample=students&method=desc" >(<fmt:message key="sidenav.desc" />)</a></li>
                </ul>
                <hr class="h-color mx-3">
                    <h3 class="px-2 pt-3 pb-4"><fmt:message key="sidenav.selection" />:</h3>
                    <form method="get" action="controller?" class="px-2">
                        <input type="hidden" name="command" value="selectCourses">
                        <%--By topic--%>
                        <label for="topics"><fmt:message key="sidenav.courses" /> </label>
                        <select name="topics" id="topics" class="form-control">
                            <option value="all"><fmt:message key="sidenav.all" /></option>
                            <c:forEach items="${topicList}" var="topic">
                                <c:if test="${lang ne 'eng' and lang ne null}">
                                    <option value="<fmt:message key="${topic}" />">${topic}</option>
                                </c:if>
                                <c:if test="${lang eq 'eng' or lang eq null}">
                                    <option value="${topic}">${topic}</option>
                                </c:if>
                            </c:forEach>
                        </select>

                    <%--By teacher--%>
                    <br/>
                    <label for="teachers"><fmt:message key="sidenav.teachers" /> </label>
                    <select name="teachers" id="teachers" class="form-control" style="with:100px;">
                        <option value="all"><fmt:message key="sidenav.all" /></option>
                        <c:forEach items="${teacherList}" var="teacher">
                            <option value="${teacher.id}">${teacher.fullName}</option>
                        </c:forEach>
                    </select>
                    <br/>
                        <label for="languages"><fmt:message key="sidenav.languages" /> </label>
                        <select name="languages" id="languages" class="form-control" style="with:100px;">
                            <option value="all"><fmt:message key="sidenav.all" /></option>
                            <option value="eng"><fmt:message key="language.eng" /></option>
                            <option value="ua"><fmt:message key="language.ua" /></option>
                        </select>
                        <br/>
                    <input type="submit" value="<fmt:message key="sidenav.select" />" class="btn btn-success">
                </form>

            </div>
        </div>
        <div class="content">

            <%-- -------------------------------------------------------------- --%>
            <div class="container">
                <div class="text-center my-5">
                    <h1><fmt:message key="courses.allcourses" /></h1>
                    <hr/>
                </div>


                <div class="row">
                    <c:forEach var="course" items="${coursesList}">
                        <div class="col-lg-4 col-md-6 col-sm-12">
                            <div class="card mb-5 shadow-sm">
                                <img src="files/course_img.webp" alt="course_image" class="img-fluid">

                                <div class="card-body">
                                    <div class="card-title">
                                        <h2><a href="controller?command=viewCourse&courseId=${course.id}">${course.name}</a></h2>
                                    </div>
                                    <div class="card-text">
                                        <p>${course.description}</p>
                                    </div>
                                    <a href="controller?command=viewCourse&courseId=${course.id}" class="btn btn-outline-primary rounded-0 float-end"><fmt:message key="courses.viewcourse" /></a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
    </div>
</body>
</html>
