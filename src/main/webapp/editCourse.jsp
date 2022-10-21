<%--
  Created by IntelliJ IDEA.
  User: Mirgo
  Date: 30.09.2022
  Time: 18:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<form action="controller?" method="post">
    <input name="command" value="updateCourse" type="hidden">
    <input name="courseId" value="${param.courseId}" type="hidden">
    <p>Title: <input name="name" value="${param.name}"></p>
    <label for="topics">Topic: </label>
    <select name="topics" id="topics" sele>
        <c:forEach items="${topicList}" var="topic">
            <c:if test="${topic eq param.topic}">
                <option value="${topic}" selected="selected">${topic}</option>
            </c:if>
            <c:if test="${topic ne param.topic}">
                <option value="${topic}">${topic}</option>
            </c:if>
        </c:forEach>
    </select>
    <p>Teacher email: <input name="teacherEmail" value="${param.teacher}" type="email" ></p>
    <p>Description:</p>

    <%--teachers list --%>
    <%--start date - finish date --%>
    <span>Start: <input name="startDate" value="${param.start}" type="date"></span>
    <span>Finish: <input name="finishDate" value="${param.finish}" type="date"></span>
    <br/>
    <input value="Update" type="submit">

</form>
</body>
</html>