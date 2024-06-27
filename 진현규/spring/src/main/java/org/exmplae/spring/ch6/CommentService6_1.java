package org.exmplae.spring.ch6;

import org.exmplae.spring.ch4.Comment;
import org.springframework.stereotype.Service;

@Service
public class CommentService6_1 {

	public void sendComment(Comment c) {
		CommentProcessor cp = new CommentProcessor();
		cp.setComment(c);
		cp.processComment(c);
		cp.validateComment(c);

		c = cp.getComment(); // 수정된 Comment 인스턴스를 가져와 추가로 사용한다.
	}
}
