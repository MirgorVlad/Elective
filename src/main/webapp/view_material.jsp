<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
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
            <a href="controller?command=downloadMaterial&courseId=${param.courseId}&material=${param.material}&type=${material.type}">Download this lection</a>
        </div>
    </div>
</body>
</html>
