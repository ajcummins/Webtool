<!doctype html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<title>Create Account</title>
	</head>
	<body>
		<h1>Create Account</h1>
		<form action="${pageContext.servletContext.contextPath}/createaccount" method="post">
			<table>
				<tr>
					<th>Enter Username:</th>
					<td><input type = "text" name="Username" size="10" ></td>
				</tr>
				<tr>
					<th>Enter Password:</th>
					<td><input type = "password" name="Password" size="10" ></td>
				</tr>
				<tr>
					<th>Confirm Password:</th>
					<td><input type = "password" name="ConfirmPassword" size="10" ></td>
				</tr>
			</table>
			<input type="submit" value="Submit"></input>
			<a href="${pageContext.servletContext.contextPath}/login">Cancel</a>
			<c:if test="${!empty result}">
				<p><c:out value="${result}"/><p>
			</c:if>
		</form>
	</body>
</html>