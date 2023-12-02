<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>User</title>
</head>
<body>
    <h1>${user.uid}</h1>
    <b><spring:message code="text.name"/>: ${user.name}</b>
    <br/>
    <b><spring:message code="text.description"/>: ${user.description}</b>
</body>
</html>