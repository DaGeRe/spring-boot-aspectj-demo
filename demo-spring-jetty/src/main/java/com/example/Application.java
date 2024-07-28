package com.example;

import java.io.IOException;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class Application {

	public static void main(final String[] args) throws Exception {
		new Application().startJetty(8080);
	}

	private void startJetty(final int port) throws Exception {
		System.out.println("Starting server at port " + port);
		final Server server = new Server(port);
		server.setHandler(getServletContextHandler(getContext()));
		server.start();
		server.join();
	}

	private static ServletContextHandler getServletContextHandler(final WebApplicationContext context) throws IOException {
		final ServletContextHandler contextHandler = new ServletContextHandler();
		contextHandler.setErrorHandler(null);
		contextHandler.setContextPath("/");
		contextHandler.addServlet(new ServletHolder(new DispatcherServlet(context)), "/*");
		contextHandler.addEventListener(new ContextLoaderListener(context));

		contextHandler.setResourceBase(new ClassPathResource("webapp").getURI().toString());
		return contextHandler;
	}

	private static WebApplicationContext getContext() {
		final AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setConfigLocation("com.example.demo");
		return context;
	}

}
