<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix = "co" uri = "/WEB-INF/showCourses.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${lang}">
<head>
    <title>My Courses</title>
    <meta charset="UTF-8">
    <link href="bootstrap/css/table.css" rel="stylesheet">
    <link href="bootstrap/css/tabs.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
    <%@include file="menu.jsp" %>
    <div class="center">
        <div class="tabs">
            <div class="tabs__sidebar">
                <button class="tabs__button  left_p" data-for-tab="1">Not begun yet</button>
                <button class="tabs__button tabs__button--active center_p" data-for-tab="2">In Progress</button>
                <button class="tabs__button right_p" data-for-tab="3">Finished</button>
            </div>
            <div class="tabs__content " data-tab="1">
                <co:showcourses coursesList="${coursesList}" purpose="notBegun"/>
            </div>
            <div class="tabs__content tabs__content--active" data-tab="2">
                <co:showcourses coursesList="${coursesList}" purpose="continues"/>
            </div>
            <div class="tabs__content " data-tab="3">
                <co:showcourses coursesList="${coursesList}" purpose="finished"/>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="bootstrap/js/tabs.js"></script>
</body>
</html>
