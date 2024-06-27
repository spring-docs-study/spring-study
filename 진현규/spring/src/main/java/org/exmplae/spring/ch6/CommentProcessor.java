package org.exmplae.spring.ch6;

import org.exmplae.spring.ch4.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Component
public class CommentProcessor {
	@Autowired // 빈에 등록된 CommentRepo6 를 주입받아서 사용하겠다
	private CommentRepo6 commentRepo6;
	private Comment comment;

	public void setComment (Comment comment) {
		this.comment = comment;
	}

	public Comment getComment () {
		return this.comment;
	}

	public void processComment(Comment comment) {
		// comment 속성 변경
	}

	public void validateComment(Comment comment) {
		// comment 속성 검증
	}

}
