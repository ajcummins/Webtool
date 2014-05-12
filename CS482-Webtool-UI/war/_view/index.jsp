<!doctype html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
	
	</head>


	<body>
	
		<h3>TEST</h3>
		
		<c:if test="${!empty result}">
			<p><c:out value="${result}"/><p>
		</c:if>
	</body>
</html>