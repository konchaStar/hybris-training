<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="text.band.title"/> </title>
</head>
<body>
<img src="${band.imageUrl}"><br>
<h1>${band.name}</h1>
<span>
    <spring:message code="text.album.sales"/>: ${band.soldAlbums}<br>
    <spring:message code="text.history"/>: ${band.bandHistory}<br>
    <spring:message code="text.producer"/>: ${band.getProducer}<br>
</span>
<a href="${pageContext.servletContext.contextPath}/bands.html">
    <spring:message code="text.back"/>
</a>
</body>
</html>
