<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${lang}">
<head>
    <title>Create course</title>
</head>
<body>

<form action="controller?" method="post">
  <input name="command" value="createCourse" type="hidden">
  <p><fmt:message key="course.name" />: <input name="name"></p>
  <label for="topics"><fmt:message key="course.topic" />: </label>
  <select name="topics" id="topics">
    <c:forEach items="${topicList}" var="topic">
      <option value="${topic}">${topic}</option>
    </c:forEach>
  </select>
  <p><fmt:message key="course.teacher" />: <input name="teacherEmail" type="email"></p>
  <p><fmt:message key="course.description" />: <input name="description"></p>
  <%--teachers list --%>
  <%--start date - finish date --%>
  <span><fmt:message key="course.start" />: <input name="startDate" type="date"></span>
  <span><fmt:message key="course.end" />: <input name="finishDate" type="date"></span>
  <br/>
  <input value="<fmt:message key="course.create" />" type="submit">

</form>

</body>
</html>
