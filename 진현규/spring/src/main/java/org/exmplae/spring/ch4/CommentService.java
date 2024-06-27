package org.exmplae.spring.ch4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class CommentService {
	@Autowired
	private CommentRepository commentRepository;

	public CommentService (CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	public CommentRepository getCommentRepository () {
		return commentRepository;
	}

}
