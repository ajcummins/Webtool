package edu.ycp.cs482.webtool.webserver;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;

public class Main {
	public static void main(String[] args) throws Exception {
		// Create an embedded Jetty server on port 8081
		Server server = new Server(8081);

		// If your app isn't packaged into a WAR, you can do this instead
		WebAppContext altHandler = new WebAppContext();
		altHandler.setResourceBase("../CS482-Webtool-UI/war");
		//altHandler.setDescriptor("../CS482-Webtool-UI/war/WEB-INF/web.xml");
		//altHandler.setContextPath("/HostFreak.com");
		altHandler.setContextPath("/");
		altHandler.setParentLoaderPriority(true);

		// Add it to the server
		server.setHandler(altHandler);
		
		server.setThreadPool(new QueuedThreadPool(20));

		// And start it up
		server.start();
		server.join();
		
	}
}
