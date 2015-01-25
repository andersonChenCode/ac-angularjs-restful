package com.web.listener;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.web.util.MySpringWebApplicationContext;
import javax.servlet.ServletContext;

public class MySpringContextLoaderListener extends ContextLoaderListener {

	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);

		MySpringWebApplicationContext.defaultWebApplicationContext =
			WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());

	}
}
