<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Materials</title>
    <link href="bootstrap/css/tabs.css" rel="stylesheet">
    <link href="bootstrap/css/materials.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
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
                        <a href="add_lection.jsp?courseId=${param.courseId}" class="adding"><img src="files/add.png" alt="add" width="20px"> Add lection</a>
                        <br/>
                    </c:if>
                </c:if>
                <br/>
                <c:forEach var="lection" items="${lectionList}">
                <div class="material-content">
                <div class="material-section">
                    <h4><a href="controller?command=viewMaterial&material=${lection.name}&courseId=${course.id}&type=lection">${lection.name}</a></h4>
                    <p>${lection.description}</p>
                </div>
                    <c:if test="${user.role eq 'teacher'}">
                        <div class="close">
                            <a href="controller?command=deleteMaterial&material=${lection.name}&courseId=${course.id}&type=lection" class="btn btn-close"></a>
                        </div>
                    </c:if>
                </div>
                    <hr/>
                </c:forEach>
            </div>
            <div class="tabs__content " data-tab="2">
                <c:if test="${user.role eq 'teacher'}">
                    <c:if test="${user.email eq course.teacher.email}">
                        <a href="add_video.jsp?courseId=${param.courseId}" class="adding"><img src="files/add.png" alt="add" width="20px" > Add video</a>
                        <br/>
                    </c:if>
                </c:if>
                <br/>
                <c:forEach var="video" items="${videoList}">
                    <div class="material-content">
                        <div class="material-section">
                            <h4><a href="controller?command=viewMaterial&material=${video.name}&courseId=${course.id}&type=video">${video.name}</a></h4>
                            <p>${video.description}</p>
                        </div>
                        <c:if test="${user.role eq 'teacher'}">
                            <div class="close">
                                <a href="controller?command=deleteMaterial&material=${video.name}&courseId=${course.id}&type=video" class="btn btn-close"></a>
                            </div>
                        </c:if>
                    </div>
                    <hr/>
                </c:forEach>
            </div>
            <div class="tabs__content " data-tab="3">
                <c:if test="${user.role eq 'teacher'}">
                    <c:if test="${user.email eq course.teacher.email}">
                        <a href="add_assignment.jsp?courseId=${param.courseId}" class="adding"><img src="files/add.png" alt="add" width="20px"> Add assignment</a>
                        <br/>
                    </c:if>
                </c:if>
                <br/>
                <c:forEach var="assignment" items="${assignmentList}">
                <div class="material-content">
                    <div class="material-section">
                        <h4><a href="controller?command=viewMaterial&material=${assignment.name}&courseId=${course.id}&type=assignment">${assignment.name}</a></h4>
                        <p>${assignment.description}</p>
                    </div>
                    <c:if test="${user.role eq 'teacher'}">
                        <div class="close">
                            <a href="controller?command=deleteMaterial&material=${assignment.name}&courseId=${course.id}&type=assignment" class="btn btn-close"></a>
                        </div>
                    </c:if>
                </div>
                    <hr/>
                </c:forEach>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="bootstrap/js/tabs.js"></script>
</body>
</html>
