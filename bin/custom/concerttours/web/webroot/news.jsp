<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>News</title>
</head>
<body>
    <c:forEach items="${news}" var="item">
        <h2>${item.headline}</h2>
        <span>
            ${item.content}
        </span><br>
    </c:forEach>
</body>
</html>
