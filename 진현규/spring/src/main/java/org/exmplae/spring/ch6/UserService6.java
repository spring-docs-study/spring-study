package org.exmplae.spring.ch6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService6 {
	@Autowired
	private CommentRepo6 commentRepo6;

	public CommentRepo6 getCommentRepo6 () {
		return commentRepo6;
	}

}
