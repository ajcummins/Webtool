<!doctype html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	
	<head>
		<title>${Project.projectName}</title>
	</head>

	<body>
		
		<c:if test="${empty action or action == 'view'}">
			<h1>${Project.projectName}</h1>
			<h4>Project Description : ${Project.projectDesc}</h4>
			<p> This is where my pages would go... IF I HAD SOME! </p>
			
		</c:if>
		
		<c:if test="${action == 'share'}">
			<h1>${Project.projectName}</h1>
			<h3>Find Users you would like to share this project with</h3>
			<form action="${pageContext.servletContext.contextPath}/MyProjects/${Project.projectName}?action=share" method="post">
				<select>
					<option></option>
				</select>
			</form>
		</c:if>
		
		<c:if test="${action == 'delete'}">
			<h1>${Project.projectName}</h1>
			<form action="${pageContext.servletContext.contextPath}/MyProjects/${Project.projectName}?action=delete" method="post">
				<h3>Are you sure you want to delete this project?</h3>
				<table>
					<tr>
						<td><input type="submit" name="submit" value = "Yes"></td>
						<td><a href="${pageContext.servletContext.contextPath}/MyProjects?action=view>Cancel</a></td>
					</tr>
				</table>						
			</form>
		</c:if>
		
		<c:if test="${action == 'newproject'}">
			<h1>Create a new Project</h1>
			<form action="${pageContext.servletContext.contextPath}/MyProjects/NewProject?action=newproject" method="post">
				<table>
					<tr>
						<th>Name Your Project: </th>
						<td><input type="text" name="projectName" size="12" /input></th>
					</tr>
					<tr>
						<th>Project Description: </th>
						<td><textarea rows="4" cols="76" name="projDesc">Enter your project description here</textarea></td>
					</tr>
				</table>
				<input type="submit" value="Submit"></input>
			</form>
		</c:if>
		
		<c:if test="${!empty result}">
			<p><c:out value="${result}"/><p>
		</c:if>
		
		<div class="link"><a href="${pageContext.servletContext.contextPath}/MyProjects">Back to Project List</a></div>
	</body>
</html>