<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${lang}">
<head>
    <title>Manager</title>
    <link href="bootstrap/css/manager.css" rel="stylesheet">
    <link href="bootstrap/css/welcome_page.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
    <%@include file="menu.jsp" %>
<%-- ------------------------------------------ --%>

     <div class="container">
      <div class="row custom-section d-flex align-items-center">
        <div class="col-12 col-lg-4">
            <h1><fmt:message key="manager.header" /></h1>
            <h3><fmt:message key="manager.underheader" /></h3>
            <p><fmt:message key="manager.text" /></p>
           <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal"><fmt:message key="manager.createcourse" /></button>
        </div>
        <div class="col-12 col-lg-8">
          <img src="files/manager.png" alt="welcome">
        </div>
      </div>
    </div>

<%-- ------------------------------------------ --%>

    <%--<a href="create_course.jsp" class="nav-link" data-bs-toggle="modal">Create course</a>--%>
    <div class="modal" id="myModal">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title"><fmt:message key="manager.createcourse" /></h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
              <form action="controller?" method="post">
                <input name="command" value="createCourse" type="hidden">
                <div class="mb-3">
                  <label class="form-label"><fmt:message key="course.name" /></label>
                   <input name="name" class="form-control">
                </div>
               
                <div class="mb-3">
                  <label for="topics" class="form-label"><fmt:message key="course.topic" /></label>
                  <select name="topics" id="topics" class="form-control">
                    <c:forEach items="${topicList}" var="topic">
                      <option value="${topic}">${topic}</option>
                    </c:forEach>
                  </select>
                </div>
                 <div class="mb-3">
                  <label class="form-label"><fmt:message key="course.teacher" /></label>
                  <input name="teacherEmail" type="email" class="form-control">
                </div>
                <div class="mb-3">
                  <label class="form-label"><fmt:message key="course.start" /></label>
                  <input name="startDate" type="date" class="form-control">
                </div>
                <div class="mb-3">
                  <label class="form-label"><fmt:message key="course.end" /></label>
                  <input name="finishDate" type="date" class="form-control">
                </div>
                <div class="mb-3">
                  <label class="form-label"><fmt:message key="course.description" /></label>
                  <textarea name="description" class="form-control"></textarea>
                </div>
                <button type="submit" class="btn btn-primary"><fmt:message key="course.create" /></button>
            </form>
            </div>
          </div>
        </div>
    </div>



</body>
</html>
