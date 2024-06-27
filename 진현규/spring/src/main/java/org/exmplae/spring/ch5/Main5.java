package org.exmplae.spring.ch5;

import org.exmplae.spring.ch4.CommentService;
import org.exmplae.spring.ch4.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main5 {
	public static void main (String[] args) {
		var c = new AnnotationConfigApplicationContext(ProjectConfig.class);

		var service = c.getBean(CommentService.class);
	}
}
