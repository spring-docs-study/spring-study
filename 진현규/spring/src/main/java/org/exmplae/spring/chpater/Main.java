package org.exmplae.spring.chpater;

import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	private static Logger logger = Logger.getLogger(Main.class.getName());

	public static void main (String[] args) {
		var c = new AnnotationConfigApplicationContext(ProjectConfig.class);
		var service = c.getBean(CommentService.class);
		Comment comment = new Comment();
		comment.setAuthor("현규");
		comment.setText("진");
		service.publicComment(comment);
		System.out.println("최종 종료");
	}
}
