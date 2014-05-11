package edu.ycp.cs482.webtool.ui.servlet;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs482.webtool.persistence.DatabaseProvider;
import edu.ycp.cs482.webtool.persistence.DerbyDatabase;
import edu.ycp.cs482.webtool.persistence.FakeDatabase;


public class DatabaseInitListener implements ServletContextListener{
	
	@Override
	public void contextInitialized(ServletContextEvent e) {
		// Webapp is starting
		DatabaseProvider.setInstance(new DerbyDatabase());
		System.out.println("Initialized database!");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent e) {
		// Webapp is shutting down
	}
}
