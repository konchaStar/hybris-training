<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="text.bands.title"/> </title>
</head>
<body>
<c:forEach var="band" items="${bands}">
    <img src="${band.imageUrl}"/><br>
    <a href="${pageContext.servletContext.contextPath}/bands.html?code=${band.code}">${band.name}<br>${band.bandHistory}</a><br><br>
</c:forEach>
</body>
</html>
