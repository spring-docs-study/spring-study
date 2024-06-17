package org.exmplae.spring;

import org.exmplae.spring.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main (String[] args) throws Exception {

		try {
			var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
			Parrot p = context.getBean(Parrot.class);
			System.out.println(p.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
