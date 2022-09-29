<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create course</title>
</head>
<body>

<form action="controller?" method="post">
  <input name="command" value="createCourse" type="hidden">
  <p>Title: <input name="name"></p>
  <p>Teacher email: <input name="teacherEmail" type="email"></p>
  <p>Description: <input name="description"></p> <br/>
  <%--teachers list --%>
  <%--start date - finish date --%>
  <span>Start: <input name="startDate" type="date"></span>
  <span>Finish: <input name="finishDate" type="date"></span>
  <br/>
  <input value="create" type="submit">

</form>

</body>
</html>
