package edu.ycp.cs482.webtool.ui.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs482.webtool.controllers.CreateUserController;
import edu.ycp.cs482.webtool.model.User;


public class CreateUserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String pass;
	private String confirmPass;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setStatus(HttpServletResponse.SC_OK);
		req.getRequestDispatcher("/_view/createUser.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//FIXME: add post
		username = (String) req.getParameter("username");
		pass = (String) req.getParameter("password");
		confirmPass = (String) req.getParameter("confirmPassword");
		
		System.out.println("username = " + username);
		System.out.println("password = " + pass);
		System.out.println("confirm Password = " + confirmPass);
		
		
		// Check for empty fields EXCEPT FOR PROF PASSWORD
		if(noEmptyFields())
		{	
			// Check if Passwords match
			if(pass.equals(confirmPass))
			{
				// Use CreateAcctController to check their credentials
				CreateUserController controller = new CreateUserController();

				// Fill User class with fields
				User newUser = new User(username,pass);


				User returnedUser = controller.createUser(newUser);
				
				if(returnedUser != null)
				{
					// Add User to session
					HttpSession session = req.getSession();
					session.setAttribute("User", returnedUser);
	
					//  Redirect to My Course List... 
					resp.sendRedirect(req.getContextPath()+"/MyProjects");
				}
				else
				{
					req.setAttribute("result", "Error Adding User to Database");
					this.doGet(req, resp);
				}

			}
			else
			{
				// Passwords didnt' match, send response to view
				req.setAttribute("result", "Passwords did not match");
				this.doGet(req, resp);
			}	
		}
		else
		{
			// Empty fields exist, send response to the view
			req.setAttribute("result", "Empty fields exsist, please fill all fields");
			this.doGet(req, resp);
		}
		
	}
	
	private boolean noEmptyFields()
	{
		if(username != "" && pass != "" && confirmPass != "")
		{
			if(username != null && pass != null && confirmPass != null)
			{
				// no empty fields
				return true;
			}
		}

		return false;

	}
	
}
