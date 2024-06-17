package org.exmplae.spring.config;

import org.exmplae.spring.Parrot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {

	@Bean
	Parrot parrot() {
		var p = new Parrot();
		p.setName("Koko");
		return p;
	}

	@Bean
	String Hello() {
		return "Hello";
	}

	@Bean
	Integer ten() {
		return 10;
	}
}
