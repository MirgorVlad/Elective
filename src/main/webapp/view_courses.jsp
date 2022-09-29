<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Courses</title>
</head>
<body>
<c:forEach var="course" items="${sessionScope.coursesList}">
    <p>${course}</p>
</c:forEach>
</body>
</html>
