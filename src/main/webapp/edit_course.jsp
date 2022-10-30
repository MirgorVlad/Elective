<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="${lang}">
<head>
    <title>${param.name}</title>
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
                <form action="controller?" method="post">
                    <input name="command" value="updateCourse" type="hidden">
                    <input name="courseId" value="${param.courseId}" type="hidden">
                    <h1><fmt:message key="course.name" />: <input name="name" value="${param.name}" class="form-control"></h1>
                    <p><b><fmt:message key="course.teacher" />:</b> <input name="teacherEmail" value="${param.teacher}" type="email" class="form-control"/></p>
                    <p><b><fmt:message key="course.start" />:</b> <input name="startDate" value="${param.start}" type="date" class="form-control"></p>
                    <p><b><fmt:message key="course.finish" />: </b><input name="finishDate" value="${param.finish}" type="date" class="form-control"></p>
                    <h3><fmt:message key="course.description" />: </h3>
                    <textarea name="description" class="form-control" >${param.description}</textarea>
                    <input value="<fmt:message key="course.update" />" type="submit" class="btn btn-success my-3">
                </form>
            </div>
        </div>
    </div>

</body>
</html>
