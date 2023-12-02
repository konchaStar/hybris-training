<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Band</title>
</head>
<body>
<img src="${band.imageUrl}"><br>
<h1>${band.name}</h1>
<span>
    Album sales: ${band.soldAlbums}<br>
    History: ${band.bandHistory}<br>
</span>
</body>
</html>
