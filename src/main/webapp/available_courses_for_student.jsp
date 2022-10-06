<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix = "co" uri = "/WEB-INF/showCourses.tld"%>
<html>
<head>
    <title>My Courses</title>
</head>
<body>
    <co:showcourses coursesList="${coursesList}"/>
</body>
</html>
