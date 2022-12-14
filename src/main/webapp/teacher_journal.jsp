<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "ex" uri = "/WEB-INF/courseLib.tld"%>
<html>
<head>
    <title>Journal</title>
    <link href="bootstrap/css/table.css" rel="stylesheet">
    <link href="bootstrap/css/journal.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
    <%@include file="menu.jsp" %>

    <div class="center">
        <ex:showjournal course="${course}" studentsList="${studentsList}"/>
        

        <c:if test="${course.finished eq true}">
            <h2 style="color:red;"><fmt:message key="journal.course.finished" /></h2>
        </c:if>
        <c:if test="${course.finished ne true}">
            <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#myModal"><fmt:message key="journal.setgrade" /></button>
        </c:if>
        <c:if test="${course.finished ne true}">
            <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#FinalModal"><fmt:message key="journal.starttest" /></button>
        </c:if>
    </div>

    <!--Edit journal-->
    <div class="modal" id="myModal">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title"><fmt:message key="journal.setgrade" /></h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
              <form action="controller?" method="post">
                <input type="hidden" name="command" value="editJournal">
                <input type="hidden" name="courseId" value="${param.courseId}">
                <div class="mb-3">
                  <label class="form-label" for="students"><fmt:message key="material.student" /></label>
                    <select name="students" id="students" class="form-control">
                          <c:forEach items="${studentsList}" var="student">
                            <option value="${student.email}">${student.fullName}</option>
                          </c:forEach>
                    </select>
                </div>
               
                 <div class="mb-3">
                  <label class="form-label"><fmt:message key="journal.date" /></label>
                  <input name="date" type="date" class="form-control">
                </div>
                <div class="mb-3">
                  <label class="form-label"><fmt:message key="journal.grade" /></label>
                  <input name="grade" class="form-control">
                </div>
                <button type="submit" class="btn btn-primary"><fmt:message key="journal.setgrade" /></button>
            </form>
            </div>
          </div>
        </div>
    </div>

    <!--Final project logic-->
    <div class="modal" id="FinalModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title"><fmt:message key="journal.test" /></h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    <form action="controller?" method="post" onsubmit="return checkDate()">
                        <input type="hidden" name="command" value="finalTest">
                        <input type="hidden" name="courseId" value="${param.courseId}">
                        <div class="mb-3">
                            <label class="form-label" ><fmt:message key="journal.date" />: </label>
                            <input name="testDate" class="form-control" type="date" id="dateInput">
                        </div>
                        <div class="mb-3">
                            <label class="form-label" ><fmt:message key="journal.starttime" />: </label>
                            <input name="sTime" class="form-control" type="time"  id="startInput">
                        </div>
                        <div class="mb-3">
                            <label class="form-label"><fmt:message key="journal.finishtime" />: </label>
                            <input name="fTime" class="form-control" type="time"  id="finishInput">
                        </div>
                        <button type="submit" class="btn btn-primary"><fmt:message key="journal.starttest" /></button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script src="bootstrap/js/journal.js"></script>
</body>
</html>
