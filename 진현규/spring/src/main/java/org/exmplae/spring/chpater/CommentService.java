package org.exmplae.spring.chpater;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class CommentService {
	private Logger log = Logger.getLogger(CommentService.class.getName());

	@ToLog
 	public String publicComment(Comment comment) {
		 log.info("[Public comment] : " + comment.getText());
		 return "Success";
	}

/*	@ToLog
	public void deleteComment(Comment comment) {
		 log.info("Delete comment: " + comment.getText());
	}

	public void updateComment(Comment comment) {
		 log.info("Update comment: " + comment.getText());
	}*/
}
