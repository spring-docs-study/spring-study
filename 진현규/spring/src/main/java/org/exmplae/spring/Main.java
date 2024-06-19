package org.exmplae.spring;

import java.util.function.Supplier;

import org.exmplae.spring.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main (String[] args) throws Exception {
			var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
			Parrot p = context.getBean(Parrot.class);
			System.out.println(p.getName());
			context.registerBean();

	}
}
