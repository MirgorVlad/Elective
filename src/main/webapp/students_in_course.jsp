<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Students</title>
    <link href="bootstrap/css/table.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
    <%@include file="menu.jsp" %>


    <div class="center">
        <h3>Students in course</h3>
    <table border="1">
    <tr class="header">
        <th>Name</th>
        <th>Email</th>
    </tr>
    <c:forEach var="student" items="${studentsList}">
        <tr>
            <th><c:out value="${student.fullName}"/></th>
            <th><a href="controller?command=viewProfile&userId=${student.id}"><c:out value="${student.email}"/></a></th>
        </tr>
    </c:forEach>
    </table>
    </div>
</body>
</html>
