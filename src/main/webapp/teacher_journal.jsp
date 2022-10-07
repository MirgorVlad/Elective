<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "ex" uri = "/WEB-INF/courseLib.tld"%>
<html>
<head>
    <title>Journal</title>
</head>
<body>
    <ex:showjournal course="${course}" studentsList="${studentsList}"/>
</body>
</html>
