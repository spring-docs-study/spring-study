package org.exmplae.spring.ch4;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Qualifier("PUSH")
@Component
public class CommentPushNotiProxy implements CommentNotificationProxy{
	@Override
	public void sendComment (Comment comment) {
		System.out.println("Sending comment " + comment.getText());
	}

}
