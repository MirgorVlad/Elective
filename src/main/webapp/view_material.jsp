<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${lang}">
<head>
    <title>Title</title>
    <link href="bootstrap/css/materials.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
    <%@include file="menu.jsp" %>
    <div class="center">
        <div class="container">
            <h1>${material.name}</h1>
            <p> ${material.description}</p>
            <c:if test="${type eq 'lection'}">
                <a href="controller?command=downloadMaterial&courseId=${param.courseId}&material=${param.material}&type=${material.type}"><fmt:message key="material.downloadlection" /></a>
            </c:if>
            <c:if test="${type eq 'video'}">
                <a href="${material.path}">${material.path}</a>
            </c:if>
            <c:if test="${type eq 'assignment'}">
                <p><fmt:message key="material.deadline" />: ${material.deadline}</p>
                <c:if test="${user.role eq 'student'}">
                    <form action="controller?" method="post" enctype="multipart/form-data">
                        <input name="command" value="addMaterials" type="hidden">
                        <input name="courseId" value="${param.courseId}" type="hidden">
                        <input name="title" value="${material.name}" type="hidden">
                        <input name="assignment" value="${true}" type="hidden">
                        <p><fmt:message key="material.uploadsolution" />:<input type="file" name="assignmentFile" class="form-control"/></p>
                        <input value="<fmt:message key="material.upload" />" type="submit" class="btn btn-success my-3">
                    </form>
                </c:if>
                <c:if test="${user.role eq 'teacher'}">
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal"><fmt:message key="material.viewsolutions" /></button>
                </c:if>
            </c:if>
        </div>
    </div>
    <!-- MODAL -->
    <div class="modal" id="myModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title"><fmt:message key="material.solutions" /></h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <table>
                            <tr>
                                <th><fmt:message key="material.student" /></th>
                                <th><fmt:message key="material.date" /></th>
                                <th><fmt:message key="material.solution" /></th>
                            </tr>
                        <c:forEach items="${solutionList}" var="solution">
                            <tr>
                                <td>${solution.user.email}</td>
                                <td>${solution.deadline}</td>
                                <td><a href="controller?command=downloadMaterial&courseId=${param.courseId}&material=${param.material}&student=${solution.user.id}&type=solution"><fmt:message key="material.solution" /></a></td>
                            </tr>
                        </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
