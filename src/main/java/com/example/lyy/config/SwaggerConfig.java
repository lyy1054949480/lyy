package com.example.lyy.config;

import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Configuration
@EnableSwagger2
public class SwaggerConfig implements EnvironmentAware {
	
	private RelaxedPropertyResolver propertyResolver;

	@Override
	public void setEnvironment(Environment environment) {
		// TODO Auto-generated method stub
		this.propertyResolver=new RelaxedPropertyResolver(environment,"swagger.");
	}

	public Docket swaggerSpringfoxDocket() {
		StopWatch watch=new StopWatch();
		watch.start();
		Docket swaggerSpringMvcPlugin =new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
				.genericModelSubstitutes(ResponseEntity.class).select()
				.build().securitySchemes(Collections.singletonList(apiKey()))
				.groupName("G1")
				.securityContexts(Collections.singletonList(securityContext()));
		watch.stop();
		return swaggerSpringMvcPlugin;
	}

	private ApiInfo apiInfo() {
		// TODO Auto-generated method stub
		return new ApiInfo(propertyResolver.getProperty("title"),propertyResolver.getProperty("description"),
				propertyResolver.getProperty("version"),propertyResolver.getProperty("termsOfServiceUrl"),
				new Contact("","",""),propertyResolver.getProperty("license"),
				propertyResolver.getProperty("licenseUrl"),new ArrayList<>());
	}
	
	private ApiKey apiKey() {
		// TODO Auto-generated method stub
		return new ApiKey("access_token","Authorization","header");
	}
	
	private SecurityContext securityContext() {
		// TODO Auto-generated method stub
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	private List<SecurityReference> defaultAuth() {
		// TODO Auto-generated method stub
		AuthorizationScope authorizationScope=new AuthorizationScope("global","accessEverything");
		
		return Collections.singletonList(new SecurityReference("access_token",new AuthorizationScope[] {authorizationScope}));
	}
}
