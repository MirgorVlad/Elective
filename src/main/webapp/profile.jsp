<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${lang}">
<head>
    <title>${requestScope.currentUser.fullName}</title>
    <link href="bootstrap/css/profile.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
    <%@include file="menu.jsp" %>
    <div class="container" style="margin-left: 60px;">
        <div class="row">
            <div class="col-md-6">
                <div class="profile-links">
                    <img src="files/profile.png" alt="profile_image" >
                    <li><a href="">${requestScope.currentUser.email}</a></li>
                    <c:if test="${user.role eq 'student'}">
                        <li><a href="student.jsp" class="bold">${requestScope.currentUser.role}</a></li>
                    </c:if>
                    <c:if test="${user.role eq 'teacher'}">
                        <li><a href="teacher.jsp" class="bold">${requestScope.currentUser.role}</a></li>
                    </c:if>
                    <c:if test="${user.role eq 'manager'}">
                        <li><a href="manager.jsp" class="bold">${requestScope.currentUser.role}</a></li>
                    </c:if>
                </div>
            </div>
             <div class="col-md-6">
                <div class="mt-custum">
                    <div class="details">
                        <div class="row">
                            <div class="col-8">
                                <fmt:message key="profile.firstname" />
                            </div>
                            <div class="col-4">
                                ${requestScope.currentUser.firstName}
                            </div>
                        </div>
                    </div>
                    <div class="details">
                        <div class="row">
                            <div class="col-8">
                                <fmt:message key="profile.lastname" />
                            </div>
                            <div class="col-4">
                                ${requestScope.currentUser.lastName}
                            </div>
                        </div>
                    </div>
                    <div class="details">
                        <div class="row">
                            <div class="col-8">
                                <fmt:message key="profile.email" />
                            </div>
                            <div class="col-4">
                                ${requestScope.currentUser.email}
                            </div>
                        </div>
                    </div>
                    <div class="details">
                        <div class="row">
                            <div class="col-8">
                                <fmt:message key="profile.role" />
                            </div>
                            <div class="col-4">
                                <fmt:message key="role.${requestScope.currentUser.role}" />
                            </div>
                        </div>
                    </div>
                </div>
                <h3 class="mt-5"><fmt:message key="profile.about" /></h3>
                <p class="text-justify">
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                    tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                    quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                    consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                    cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
                    proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                </p>
            </div>
        </div>
    </div>
</body>
</html>
