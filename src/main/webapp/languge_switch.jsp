<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Switcher</title>
    <link rel="stylesheet" href="bootstrap/css/switcher.css">
</head>
<body>
    <div class="switch">
        <c:if test="${lang eq 'ua'}">
                <div class="lang" style="border: 3px solid red; border-radius: 50%;"><a href="?lang=ua"><img src="files/ua.png" alt="Ua"></a></div>
                <div class="lang" ><a href="?lang=eng"><img src="files/eng.png" alt="Eng"></a></div>
        </c:if>
        <c:if test="${lang ne 'ua'}">
                <div class="lang" ><a href="?lang=ua" alt="Ua"><img src="files/ua.png" alt="Ua"></a></div>
                <div class="lang" style="border: 3px solid red; border-radius: 50%;"><a href="?lang=eng"><img src="files/eng.png" alt="Eng"></a></div>
        </c:if>
    </div>
</body>
</html>
