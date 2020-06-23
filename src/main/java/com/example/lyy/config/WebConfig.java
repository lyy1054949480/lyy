package com.example.lyy.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	private static Logger logger= LoggerFactory.getLogger(WebConfig.class);
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if(logger.isDebugEnabled()) {
			logger.debug("addResourceHandlers,[/static/**,/swagger-ui.html,/webjars/**]");
		}
		
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		
		
	}
	
}
