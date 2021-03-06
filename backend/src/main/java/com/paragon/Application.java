package com.paragon;

import org.h2.server.web.WebServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



@SpringBootApplication
@ComponentScan({"com.paragon.models","com.paragon","com.paragon.service","com.paragon.repository","com.paragon.mapper"})
public class Application {
	 private static final Logger log = LoggerFactory.getLogger(Application.class);	

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		log.info("Started SpringBoot - Paragon");
	
	}
	
	
	@Bean
	public ServletRegistrationBean h2servletRegistration() {
	    ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
	    registration.addUrlMappings("/console/*");
	    return registration;
	}
	


	@Configuration
	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			// @formatter:off
			http
				.authorizeRequests()
				.antMatchers("/**").permitAll();
				http.csrf().disable();
				http.headers().frameOptions().disable();
					
			// @formatter:on
		}
	}

}
