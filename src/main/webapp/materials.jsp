<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Materials</title>
    <link href="bootstrap/css/tabs.css" rel="stylesheet">
    <link href="bootstrap/css/materials.css" rel="stylesheet">
</head>
<body>
    <%@include file="menu.jsp" %>
    <div class="center">
        <!--<h1>Materials</h1> -->
        <div class="tabs">
            <div class="tabs__sidebar">
                <button class="tabs__button tabs__button--active left_p" data-for-tab="1">Lections</button>
                <button class="tabs__button  center_p" data-for-tab="2">Video</button>
                <button class="tabs__button right_p" data-for-tab="3">Assignments</button>
            </div>
            <div class="tabs__content tabs__content--active" data-tab="1">
                <c:if test="${user.role eq 'teacher'}">
                    <c:if test="${user.email eq course.teacher.email}">
                        <a href="add_lection.jsp?courseId=${param.courseId}"><img src="files/add.png" alt="add" width="20px"> Add lection</a>
                    </c:if>
                </c:if>
                <br/>
                <br/>
                <c:forEach var="lection" items="${lectionList}">
                    <h4><a href="view_material.jsp?material=${lection.name}">${lection.name}</a></h4>
                    <p>${lection.description}</p>
                    <hr/>
                </c:forEach>
            </div>
            <div class="tabs__content " data-tab="2">
               <h2>Videos</h2>
            </div>
            <div class="tabs__content " data-tab="3">
                <h2>Assignments</h2>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="bootstrap/js/tabs.js"></script>
</body>
</html>
