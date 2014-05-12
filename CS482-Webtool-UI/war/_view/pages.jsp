<!doctype html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<title>${Page.pageName}</title>
	</head>

	<body>		
		<c:if test="${empty action or action == 'view'}">
			<h1>${Page.pageName}</h1>
			<h3>THIS IS A PAGE</h3>
		<table>
			<tr>
				<th>Page Name : </th>
				<td>${Page.pageName}</td>
			</tr>
		</table>
		</c:if>
		
		<c:if test="${action == 'edit'}">
			<h1>${Page.pageName}</h1>
			<h3>Edit Page</h3>
			<form action="${pageContext.servletContext.contextPath}/Pages/${Page.pageName}?action=edit" method="post">
				<input type="submit" value="Submit"></input>
			</form>
		</c:if>
		
		<c:if test="${action == 'delete'}">
			<h1>${Page.pageName}</h1>
			<form action="${pageContext.servletContext.contextPath}/Pages/${Page.pageName}?action=delete" method="post">
				<h3>Are you sure you want to delete this project?</h3>
				<table>
					<tr>
						<td><input type="submit" name="submit" value = "Yes"></td>
						<td><a href="${pageContext.servletContext.contextPath}/Pages?action=view>Cancel</a></td>
					</tr>
				</table>						
			</form>
		</c:if>
		
		<c:if test="${action == 'newpage'}">
			<h1>Create a new Project</h1>
			<form action="${pageContext.servletContext.contextPath}/Pages/NewPage?action=newpage" method="post">
				<table>
					<tr>
						<th>Name This Page: </th>
						<td><input type="text" name="pageName" size="12" /input></th>
					</tr>
				</table>
				<input type="submit" value="Submit"></input>
			</form>
		</c:if>
		
		<c:if test="${!empty result}">
			<p><c:out value="${result}"/><p>
		</c:if>
		
		<div class="link"><a href="${pageContext.servletContext.contextPath}/Pages">Back to Page List</a></div>
	</body>
</html>