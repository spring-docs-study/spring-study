package org.exmplae.spring.chpater;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan(basePackages = "org.exmplae.spring.*")
@Configuration
@EnableAspectJAutoProxy
public class ProjectConfig {

	@Bean
	public LoggingAspect loggingAspect() {
		return new LoggingAspect();
	}

	@Bean
	public SecurityAspect securityAspect() {
		return new SecurityAspect();
	}
}
