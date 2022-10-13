<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "u" uri = "/WEB-INF/showUsers.tld"%>

<html>
<head>
    <title>Users</title>
</head>
<body>
    <u:showusers userList="${userList}"/>
</body>
</html>
