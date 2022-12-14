<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${lang}">
<head>
    <title>Manager</title>
    <meta charset="UTF-8"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="bootstrap/css/manager.css" rel="stylesheet">
    <link href="bootstrap/css/welcome_page.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
    <%@include file="menu.jsp" %>
    <%@include file="languge_switch.jsp" %>
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

    <!--Creating course window-->
    <div class="modal" id="myModal">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title"><fmt:message key="manager.createcourse" /></h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
              <form action="controller?" method="post" enctype="multipart/form-data">
                <input name="command" value="createCourse" type="hidden">
                <div class="mb-3">
                  <label class="form-label"><fmt:message key="course.name" /></label>
                   <input name="name" class="form-control">
                </div>
               
                <div class="mb-3">
                  <label for="topics" class="form-label"><fmt:message key="course.topic" /></label>
                  <select name="topics" id="topics" class="form-control">
                    <c:forEach items="${topicList}" var="topic">
                        <c:if test="${lang ne 'eng' and lang ne null}">
                            <option value="<fmt:message key="${topic}" />">${topic}</option>
                        </c:if>
                        <c:if test="${lang eq 'eng' or lang eq null}">
                            <option value="${topic}">${topic}</option>
                        </c:if>
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
                  <div class="mb-3">
                      <label class="form-label" for="file"><fmt:message key="course.image" /></label>
                      <input type="file" name="file" class="form-control" id="file"/>
                  </div>
                  <div class="mb-3">
                      <fmt:message key="manager.create.lang" />:
                      <input type="radio" id="eng"  name="language" value="eng" class="form-radio-input" required>
                      <label for="eng" class="form-radio-label"><fmt:message key="lang.eng" /></label>
                      <input type="radio" id="ua"  name="language" value="ua" class="form-radio-input" required>
                      <label for="ua" class="form-radio-label"><fmt:message key="lang.ua" /></label>
                  </div>
                <input type="submit" class="btn btn-primary" value="<fmt:message key="course.create" />"/>
            </form>
            </div>
          </div>
        </div>
    </div>

</body>
</html>
