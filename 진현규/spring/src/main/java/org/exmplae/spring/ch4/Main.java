package org.exmplae.spring.ch4;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main (String[] args) {
		var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

		var comment = new Comment();
		comment.setAuthor("jin hyeon kyu");
		comment.setText("new comment");

		CommentService bean = context.getBean(CommentService.class);
	}
}
