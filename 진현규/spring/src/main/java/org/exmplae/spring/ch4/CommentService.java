package org.exmplae.spring.ch4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CommentService {
	private final CommentRepository commentRepository;
	private final CommentNotificationProxy commentNotificationProxy;

	public CommentService (CommentRepository commentRepository, @Qualifier("PUSH") CommentNotificationProxy emailCommentNotificationProxy) {
		this.commentRepository = commentRepository;
		this.commentNotificationProxy = emailCommentNotificationProxy;
	}

	// 기능을 의존성에 위임하는 사용 사례를 구현한다.
	public void publishComment (Comment comment) {
		commentRepository.storeComment(comment);
		commentNotificationProxy.sendComment(comment);
	}

}
