package org.exmplae.spring.ch6;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main6 {
	public static void main (String[] args) {
		var c = new AnnotationConfigApplicationContext(ProjectConfig6.class);

		var s1 = c.getBean(CommentService6.class);
		var s2 = c.getBean(UserService6.class);

		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s1.equals(s2));
		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());
	}
}
