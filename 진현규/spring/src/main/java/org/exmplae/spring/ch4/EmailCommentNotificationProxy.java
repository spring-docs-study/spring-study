package org.exmplae.spring.ch4;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Qualifier("EMAIL")
@Component
public class EmailCommentNotificationProxy implements CommentNotificationProxy{
	@Override
	public void sendComment (Comment comment) {
		System.out.println("Sending Comment : " + comment.getText());
	}

}
