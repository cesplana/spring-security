<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
</head>
<body>
	<h2>${title}</h2>
	<h2>Welcome ${userName}!</h2>
	<br/>
	<b>Principal:</b> ${principal}
	<br/><br/>
	<b>UserInfo:</b> ${userInfo}
	<!--
	<br/>
	Welcome : ${pageContext.request.userPrincipal.name} <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
	-->
</body>
</html>
