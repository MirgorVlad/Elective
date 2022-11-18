<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="bootstrap/css/materials.css" rel="stylesheet">
</head>
<body>
    <%@include file="menu.jsp" %>
    <div class="center">
        <div class="container">
            <h1>${material.name}</h1>
            <p> ${material.description}</p>
            <c:if test="${type eq 'lection'}">
                <a href="controller?command=downloadMaterial&courseId=${param.courseId}&material=${param.material}&type=${material.type}">Download this lection</a>
            </c:if>
            <c:if test="${type eq 'video'}">
                <a href="${material.path}">${material.path}</a>
            </c:if>
            <c:if test="${type eq 'assignment'}">
                <p>Deadline: ${material.deadline}</p>
            </c:if>
        </div>
    </div>
</body>
</html>
