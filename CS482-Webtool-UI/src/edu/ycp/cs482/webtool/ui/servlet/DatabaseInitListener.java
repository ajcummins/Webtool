package edu.ycp.cs482.webtool.ui.servlet;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import edu.ycp.cs482.webtool.persistence.DatabaseProvider;
import edu.ycp.cs482.webtool.persistence.DerbyDatabase;


public class DatabaseInitListener implements ServletContextListener{
	
	@Override
	public void contextInitialized(ServletContextEvent e) {

		// FIXME: huge hack - if /home/josh/test.db exists, assume that
		// it is where the database files are located.
		File file = new File("/home/josh/test.db");
		if (file.exists()) {
			DerbyDatabase.DATABASE_PATH = "/home/josh/test.db";
		}
		
		// Webapp is starting
		DatabaseProvider.setInstance(new DerbyDatabase());
		System.out.println("Initialized database!");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent e) {
		// Webapp is shutting down
	}
}
