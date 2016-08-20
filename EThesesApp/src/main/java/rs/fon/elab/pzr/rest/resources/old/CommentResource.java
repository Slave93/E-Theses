package rs.fon.elab.pzr.rest.resources.old;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/messages/{messageId}/comments")
public class CommentResource {

	/*private CommentDAO commentDAO = new CommentDAOImpl();

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Comment> getAllComments(@PathVariable("messageId") long messageId) {
		return commentDAO.getAllComments(messageId);
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Comment addComment(@PathVariable("messageId") long messageId, @RequestBody Comment comment) {
		return commentDAO.addComment(messageId, comment);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{commentOrder}")
	public @ResponseBody Comment deleteComment(@PathVariable("messageId") long messageId,
			@PathVariable("commentOrder") long commentOrder) {
		return commentDAO.removeComment(messageId, (int) commentOrder);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{commentOrder}")
	public @ResponseBody Comment getComment(@PathVariable("messageId") long messageId,
			@PathVariable("commentOrder") long commentOrder) {
		Comment comment = commentDAO.getComment(messageId, (int) commentOrder);
		if (comment == null) {
			// TODO: throw some exception
			return null;
		}
		return comment;
	}
	*/

}
