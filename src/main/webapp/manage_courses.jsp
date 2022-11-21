<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix = "co" uri = "/WEB-INF/showCourses.tld"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${lang}">
<head>
    <title>Courses</title>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <link href="bootstrap/css/table.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
    <%@include file="menu.jsp" %>

    <div class="center">
        <div>
        <h3><fmt:message key="manager.manage" /></h3>
        <form action="controller">
            <input type="hidden" name="command" value="manageCourses">
            <label for="name"><fmt:message key="course.findbyname" />: </label>
            <input type="text" id="name" name="name" >
            <input type="submit" value="<fmt:message key="course.find" />">
        </form>
        </div>
    <co:showcourses coursesList="${coursesList}"/>
        <c:if test="${pageCount ne 1}">
            <c:forEach begin="1" end="${pageCount}" var="pageNum">
                <a href="controller?command=manageCourses&page=${pageNum}">${pageNum}</a>
            </c:forEach>
        </c:if>
    </div>

</body>
</html>
