package org.exmplae.spring.ch6;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableAsync;

@ComponentScan(basePackages = {"*"})
@Configuration
public class ProjectConfig6 {

	@Bean
	@Scope(BeanDefinition.SCOPE_PROTOTYPE)
	public CommentService6 commentService6() {
		return new CommentService6();
	}
}
