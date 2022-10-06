<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>${requestScope.user.fullName}</title>
</head>
<body>
    <img src="files/profile_img.jpg" alt="profile_image">
    <p>First name: ${requestScope.user.firstName}</p>
    <p>Last name: ${requestScope.user.lastName}</p>
    <p>Email: ${requestScope.user.email}</p>
    <p>Role: ${requestScope.user.role}</p>
</body>
</html>
