<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bands</title>
</head>
<body>
<c:forEach var="band" items="${bands}">
    <img src="${band.imageUrl}"/><br>
    <a href="${pageContext.servletContext.contextPath}/bands.html?code=${band.code}">${band.name}<br>${band.bandHistory}</a><br><br>
</c:forEach>
</body>
</html>
