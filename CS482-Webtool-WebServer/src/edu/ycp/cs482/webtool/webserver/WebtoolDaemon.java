package edu.ycp.cs482.webtool.webserver;

import org.cloudcoder.daemon.IDaemon;
import org.cloudcoder.daemon.Util;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.WebAppClassLoader;
import org.eclipse.jetty.webapp.WebAppContext;

public class WebtoolDaemon implements IDaemon {
	private Server server;
	
	@Override
	public void start(String instanceName) {
		try {
			// Use 20 threads to handle requests
			server = new Server(8082);
 
			// Create and register a webapp context
			WebAppContext handler = new WebAppContext();
			handler.setClassLoader(new WebAppClassLoader(getClass().getClassLoader(), handler)); // needed to resolve JSTL?
			handler.setContextPath("/"); // no context path

			// Run the webapp out of the /war directory of the jarfile
			String codeBase = Util.findCodeBase(WebtoolDaemon.class);
			if (!codeBase.endsWith(".jar")) {
				throw new IllegalStateException("Only running from a jar file is supported");
			}
			System.out.println("Running from codebase: " + codeBase);
			String warPath = "jar:file:" + codeBase + "!/war";
			handler.setWar(warPath);
			System.out.println("War path is " + warPath);
			
			server.setHandler(handler);
			
			server.setThreadPool(new QueuedThreadPool(20));
			
			// Start the server
			server.start();
			
			System.out.println("Web server started");
		} catch (Exception e) {
			System.out.println("Error starting server: " + e.getMessage());
		}
	}

	@Override
	public void handleCommand(String command) {
		// No commands are supported
	}

	@Override
	public void shutdown() {
		try {
			System.out.println("Shutting down...");
			server.stop();
			server.join();
			System.out.println("Server has shut down, exiting");
		} catch (Exception e) {
			System.out.println("Error shutting down server: " + e.getMessage());
		}
	}

}
