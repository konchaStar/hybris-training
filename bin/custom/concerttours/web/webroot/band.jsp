<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Band</title>
</head>
<body>
<h1>${band.name}</h1>
<span>
    Album sales: ${band.albumSales}<br>
    History: ${band.history}<br>
</span>
</body>
</html>
