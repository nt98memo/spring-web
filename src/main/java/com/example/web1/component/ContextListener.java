package com.example.web1.component;

import org.springframework.stereotype.Component;

import com.example.common.component.BaseContextListener;

import jakarta.servlet.ServletContextEvent;

@Component
public class ContextListener extends BaseContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		super.contextInitialized(sce);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		super.contextDestroyed(sce);
	}

}
