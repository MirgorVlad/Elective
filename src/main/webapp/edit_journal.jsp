<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
  <form action="controller?" method="post">
    <input type="hidden" name="command" value="editJournal">
    <input type="hidden" name="courseId" value="${param.courseId}">
    <label for="students">Student: </label>
    <select name="students" id="students">
      <c:forEach items="${studentsList}" var="student">
        <option value="${student.email}">${student.fullName}</option>
      </c:forEach>
    </select>
    <p>Date: <input type="date" name="date"></p>
    <p>Grade: <input name="grade"></p>
    <input type="submit" value="Edit">
  </form>
</body>
</html>
