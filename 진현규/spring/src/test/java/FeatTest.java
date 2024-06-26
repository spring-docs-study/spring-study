import org.exmplae.spring.ch4.Comment;
import org.exmplae.spring.ch4.CommentService;
import org.exmplae.spring.ch4.DBCommentRepository;
import org.exmplae.spring.ch4.EmailCommentNotificationProxy;

public class FeatTest {

	public static void main (String[] args) {
		var commentRepository = new DBCommentRepository();
		var emailCommentNotificationProxy = new EmailCommentNotificationProxy();

		CommentService commentService = new CommentService(commentRepository, emailCommentNotificationProxy);

		var comment = new Comment();
		comment.setAuthor("JIN HYEON KYU");
		comment.setText("나는 천재야");

		commentService.publishComment(comment);
	}
}
