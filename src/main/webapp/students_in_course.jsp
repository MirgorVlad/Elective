<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Students</title>
</head>
<body>
<table border="1">
<caption>Students</caption>
<tr>
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
</body>
</html>
