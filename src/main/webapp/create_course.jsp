<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create course</title>
</head>
<body>

<form action="controller?" method="post">
  <input name="command" value="createCourse" type="hidden">
  <p>Title: <input name="name"></p>
  <label for="topics">Topic: </label>
  <select name="topics" id="topics">
    <c:forEach items="${topicList}" var="topic">
      <option value="${topic}">${topic}</option>
    </c:forEach>
  </select>
  <p>Teacher email: <input name="teacherEmail" type="email"></p>
  <p>Description: <input name="description"></p>
  <%--teachers list --%>
  <%--start date - finish date --%>
  <span>Start: <input name="startDate" type="date"></span>
  <span>Finish: <input name="finishDate" type="date"></span>
  <br/>
  <input value="create" type="submit">

</form>

</body>
</html>
