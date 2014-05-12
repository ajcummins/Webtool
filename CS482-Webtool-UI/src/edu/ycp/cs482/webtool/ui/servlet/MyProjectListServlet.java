package edu.ycp.cs482.webtool.ui.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs482.webtool.controllers.AddProject;
import edu.ycp.cs482.webtool.controllers.AddProjectRegEntry;
import edu.ycp.cs482.webtool.controllers.GetMyProjectList;
import edu.ycp.cs482.webtool.controllers.GetProjectByName;
import edu.ycp.cs482.webtool.model.Project;
import edu.ycp.cs482.webtool.model.User;


public class MyProjectListServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private User currentUser = null;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Retrieve the User object from the session
		HttpSession session = req.getSession();
		currentUser = (User) session.getAttribute("CurrentUser");
		
		// User was obtained successfully.. Continue
		if(currentUser != null)
		{
			String action = req.getParameter("action");
			if(action != null)
			{
				req.setAttribute("action", action);
			}
			
			String projectName = getProjectName(req);
			showUI(req, resp, projectName);
		}
		//if there isn't one the person isn't logged in send them back
		else
		{
			req.setAttribute("result", "You are not logged in, Please Log in");
			resp.sendRedirect(req.getContextPath() + "/Login");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Retrieve the User object from the session to make sure it isn't empty even during a post
		HttpSession session = req.getSession();
		currentUser = (User) session.getAttribute("CurrentUser");
		if(currentUser != null)
		{
			// Response
			
			// Obtain the Project Name we're looking @ currently
			String projectName = getProjectName(req);
			String action = req.getParameter("action");
			
			// There is an action set... Use it!
			if(action != null && !action.trim().equals(""))
			{
				// Which action?
				if(action.trim().equals("share"))
				{
					// Share the project with other users
					
					// Obtain the User(s) that you want to share the project w/
					
					// Add the Users and Project id's to the Project registry
				}
				else if(action.trim().equals("delete"))
				{
					// Delete the current project					
				}
				else if(action.trim().equals("newproject"))
				{
					// Obtain the project detail
					String tempProjectName = (String) req.getParameter("projectName");
					String projectDesc = (String) req.getParameter("projDesc");

					// Stuff the details in a project model class
					Project tempProject = new Project(tempProjectName,projectDesc);
					// Add the project to the database using the controller
					AddProject addProjController = new AddProject();
					Project returnedProject = addProjController.addProject(tempProject);
					if(returnedProject != null)
					{
						// AddProject was successful add an entry to the User|Project registry
						AddProjectRegEntry projRegController = new AddProjectRegEntry();
						boolean success = projRegController.addProjectRegEntry(currentUser.getUserID(),returnedProject.getProjectID());
						if(success)
						{
							// Redirect to back to the MyProjectList
							req.setAttribute("action", "view");
						}
						else
						{
							// Error add User|Project entry to registry failed
							req.setAttribute("result","Add Entry to User|Project Registry Failed Unexpectedly...");
						}
					}
					else
					{
						// Error create new project failed
						req.setAttribute("result", "Create New Project Failed Unexpectedly...");
					}
					
				}
				else
				{
					// Unknown action throw an exception
					throw new ServletException("Unknown action: " + action);
				}
				
				// Display the new UI after the post has been completed
				showUI(req,resp,projectName);
				
			}
			else
			{
				// Action is empty, simply display the UI
				showUI(req,resp,projectName);
				return;
			}
		}
		else
		{
			// No User data in session, redirect to Login...
			req.setAttribute("result", "You are not logged in, Please log in");
			resp.sendRedirect(req.getContextPath() + "/Login");
		}
		
	}
	
	private String getProjectName(HttpServletRequest req){
		String projectName = null;
		String pathInfo = req.getPathInfo();
		if(pathInfo != null && !pathInfo.equals("") && !pathInfo.equals("/")){
			projectName = pathInfo;
			if(projectName.startsWith("/")) {
				projectName = projectName.substring(1);
			}
		}
		
		return projectName;
	}
	
	private void showUI(HttpServletRequest req, HttpServletResponse resp, String projectName) throws ServletException, IOException{
		// Display My Project List because we have not specified a project
		if(projectName == null)
		{
			//My Project List
			
			GetMyProjectList myListController = new GetMyProjectList();
			List<Project> myprojectlist = myListController.getMyProjectList(currentUser);
			
			// If return has projects in it
			if(myprojectlist != null && myprojectlist.size() > 0)
			{
				// There are valid projects in the list
				req.setAttribute("validprojects", "true");
				// set the project list for the UI
				req.setAttribute("MyProjectList", myprojectlist);
				// display the view
				req.getRequestDispatcher("/_view/myProjects.jsp").forward(req,resp);
			}
			else
			{
				// No project currently for this user
				req.setAttribute("validprojects", "false");
				req.getRequestDispatcher("/_view/myProjects.jsp").forward(req,resp);
			}
			
		}
		// Disply the Specified Project
		else
		{
			GetProjectByName projectcontroller = new GetProjectByName();
			Project project = projectcontroller.getProjectByName(projectName);
			
			// Obatin and set page list
			// GetPageList pageListController = new GetPageList()
			// List<Page> myPageList = controller.getPageList(project)
			
			// Set the appropriate Attributes
			req.setAttribute("Project", project);
			// req.setAttribute("pagelist", myPageList);
			
			// Display UI
			req.getRequestDispatcher("/_view/project.jsp").forward(req,resp);			
		}
	}
}
