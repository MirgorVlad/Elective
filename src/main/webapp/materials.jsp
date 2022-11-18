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
                <div class="material-section">
                    <h4><a href="controller?command=viewMaterial&material=${lection.name}&courseId=${course.id}&type=lection">${lection.name}</a></h4>
                    <p>${lection.description}</p>
                </div>
                    <c:if test="${user.role eq 'teacher'}">
                        <div class="close">
                            <a href="controller?command=deleteMaterial&material=${lection.name}&courseId=${course.id}&type=lection">X</a>
                        </div>
                    </c:if>
                    <hr/>
                </c:forEach>
            </div>
            <div class="tabs__content " data-tab="2">
                <c:if test="${user.role eq 'teacher'}">
                    <c:if test="${user.email eq course.teacher.email}">
                        <a href="add_video.jsp?courseId=${param.courseId}"><img src="files/add.png" alt="add" width="20px"> Add video</a>
                    </c:if>
                </c:if>
                <br/>
                <br/>
                <c:forEach var="video" items="${videoList}">
                    <div class="material-section">
                        <h4><a href="controller?command=viewMaterial&material=${video.name}&courseId=${course.id}&type=video">${video.name}</a></h4>
                        <p>${video.description}</p>
                    </div>
                    <c:if test="${user.role eq 'teacher'}">
                        <div class="close">
                            <a href="controller?command=deleteMaterial&material=${video.name}&courseId=${course.id}&type=video">X</a>
                        </div>
                    </c:if>
                    <hr/>
                </c:forEach>
            </div>
            <div class="tabs__content " data-tab="3">
                <c:if test="${user.role eq 'teacher'}">
                    <c:if test="${user.email eq course.teacher.email}">
                        <a href="add_assignment.jsp?courseId=${param.courseId}"><img src="files/add.png" alt="add" width="20px"> Add assignment</a>
                    </c:if>
                </c:if>
                <br/>
                <br/>
                <c:forEach var="assignment" items="${assignmentList}">
                    <div class="material-section">
                        <h4><a href="controller?command=viewMaterial&material=${assignment.name}&courseId=${course.id}&type=assignment">${assignment.name}</a></h4>
                        <p>${assignment.description}</p>
                    </div>
                    <c:if test="${user.role eq 'teacher'}">
                        <div class="close">
                            <a href="controller?command=deleteMaterial&material=${assignment.name}&courseId=${course.id}&type=assignment">X</a>
                        </div>
                    </c:if>
                    <hr/>
                </c:forEach>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="bootstrap/js/tabs.js"></script>
</body>
</html>
