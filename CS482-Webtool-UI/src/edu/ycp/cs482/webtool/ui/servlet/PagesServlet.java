package edu.ycp.cs482.webtool.ui.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs482.webtool.controllers.AddPage;
import edu.ycp.cs482.webtool.controllers.AddPageRegEntry;
import edu.ycp.cs482.webtool.controllers.AddProject;
import edu.ycp.cs482.webtool.controllers.AddProjectRegEntry;
import edu.ycp.cs482.webtool.controllers.GetMyProjectList;
import edu.ycp.cs482.webtool.controllers.GetPageByName;
import edu.ycp.cs482.webtool.controllers.GetProjectByName;
import edu.ycp.cs482.webtool.controllers.GetProjectPages;
import edu.ycp.cs482.webtool.model.Page;
import edu.ycp.cs482.webtool.model.Project;
import edu.ycp.cs482.webtool.model.User;


public class PagesServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	// Need both the current user and project we're working on
	private User currentUser = null;
	private Project currentProject = null;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Retrieve User object session
		HttpSession session = req.getSession();
		currentUser = (User) session.getAttribute("CurrentUser");
		
		// User was obtained successfully.. Continue
		if(currentUser != null)
		{
			currentProject = (Project) session.getAttribute("CurrentProject");
			if(currentProject != null)
			{
				String action = req.getParameter("action");
				if(action != null)
				{
					req.setAttribute("action", action);
				}
				
				String pageName = getPageName(req);
				showUI(req, resp, pageName);
			}
			else
			{
				req.setAttribute("result", "You didn't select a project, Please select a project");
				resp.sendRedirect(req.getContextPath() + "/MyProjects");
			}
			
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
			currentProject = (Project) session.getAttribute("CurrentProject");
			if(currentProject != null)
			{
				// Response
				
				// Obtain the Project Name we're looking @ currently
				String pageName = getPageName(req);
				String action = req.getParameter("action");
				
				// There is an action set... Use it!
				if(action != null && !action.trim().equals(""))
				{
					// Which action?
					if(action.trim().equals("edit"))
					{
						// Edit the page (WEBTOOL)
					}
					else if(action.trim().equals("delete"))
					{
						// Delete the current page				
					}
					else if(action.trim().equals("newpage"))
					{
						// Obtain the project detail
						String tempPageName = (String) req.getParameter("pageName");
	
						// Stuff the details in a project model class
						Page tempPage = new Page(tempPageName);
						// Add the project to the database using the controller
						AddPage addPageController = new AddPage();
						Page returnedPage = addPageController.addPage(tempPage);
						if(returnedPage != null)
						{
							// AddProject was successful add an entry to the User|Project registry
							AddPageRegEntry pageRegController = new AddPageRegEntry();
							boolean success = pageRegController.addPageRegEntry(currentProject.getProjectID(),returnedPage.getPageID());
							if(success)
							{
								// Redirect to back to the MyProjectList
								req.setAttribute("action", "view");
							}
							else
							{
								// Error add User|Project entry to registry failed
								req.setAttribute("result","Add Entry to Project|Page Registry Failed Unexpectedly...");
							}
						}
						else
						{
							// Error create new project failed
							req.setAttribute("result", "Create New Page Failed Unexpectedly...");
						}
						
					}
					else
					{
						// Unknown action throw an exception
						throw new ServletException("Unknown action: " + action);
					}
					
					// Display the new UI after the post has been completed
					showUI(req,resp,pageName);
				}
				else
				{
					// Action is empty, simply display the UI
					showUI(req,resp,pageName);
					return;
				}
				
			}
			else
			{
				// No Project data in session, redirect to Login...
				req.setAttribute("result", "You didn't select a project, Please select a project");
				resp.sendRedirect(req.getContextPath() + "/MyProjects");
			}
		}
		else
		{
			// No User data in session, redirect to Login...
			req.setAttribute("result", "You are not logged in, Please log in");
			resp.sendRedirect(req.getContextPath() + "/Login");
		}
	}
	
	private String getPageName(HttpServletRequest req){
		String pageName = null;
		String pathInfo = req.getPathInfo();
		if(pathInfo != null && !pathInfo.equals("") && !pathInfo.equals("/")){
			pageName = pathInfo;
			if(pageName.startsWith("/")) {
				pageName = pageName.substring(1);
			}
		}
		return pageName;
	}
	
	private void showUI(HttpServletRequest req, HttpServletResponse resp, String pageName) throws ServletException, IOException{
		// Disply the Specified Page
		GetPageByName pagecontroller = new GetPageByName();
		Page page = pagecontroller.getPageByName(pageName,currentProject.getProjectID());
		req.setAttribute("Page", page);
		/*
		 * Translation Code???
		 * 
		 */
		
		// Display UI
		req.getRequestDispatcher("/_view/pages.jsp").forward(req,resp);			
	}
}
