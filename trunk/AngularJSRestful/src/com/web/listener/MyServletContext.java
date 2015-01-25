package com.web.listener;

import javax.servlet.ServletContext;

public class MyServletContext {

	public static MyServletContext instance;
	
	public static MyServletContext getInstance() {
		if(instance==null) {
			instance = new MyServletContext();
		}
		return instance;
	}
	
	private ServletContext servletContext;

	public ServletContext getServletContext() { return servletContext; }
	public void setServletContext(ServletContext servletContext) { this.servletContext = servletContext; }
	
	
}
