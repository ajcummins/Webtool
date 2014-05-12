<!doctype html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
		
	<head>
		<title>My Projects</title>
		<h1>My Projects</h1>
	</head>
	
	<body>
		<c:if test ="${validprojects == 'true'}">
			<c:forEach var="project" items="${MyProjectList}">
			<table class="myprojectlist">
				<tr>
					<th>Project : </th>
					<td><a href = "${pageContext.servletContext.contextPath}/MyProjects/${project.projectName}?action=view">  ${project.projectName}  </a></td>	
					<td><a href = "${pageContext.servletContext.contextPath}/MyProjects/${project.projectName}?action=share">  Share this Project  </a></td>
					<td><a href = "${pageContext.servletContext.contextPath}/MyProjects/${project.projectName}?action=delete">  Delete Project  </a></td>				
				</tr>
			</table>
			<table>
				<tr>
					<td>Project Description: </td>
					<td> ${project.projectDesc} </td>
				</tr>
			</table>
			<br>	
			</c:forEach>
		</c:if>
		<c:if test="${empty validprojects or validprojects == 'false'}">
			<h4> You have no projects currently </h4>
		</c:if>
		<c:if test="${!empty result}">
			<p><c:out value="${result}"/><p>
		</c:if>
		<div class="link"><a href="${pageContext.servletContext.contextPath}/MyProjects/NewProject?action=newproject">Create New Project</a></div>
	</body>
</html>