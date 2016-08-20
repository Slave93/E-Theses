package rs.fon.elab.pzr.rest.resources.old;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/messages")
public class MessageResource {

	/*MessageDAO messageDAO = new MessageDAOImpl();

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Message> getMessages(
			@RequestParam(value = "profileName", required = false) String profileName) {
		if (profileName != null) {
			return messageDAO.getAllMessagesForProfile(profileName);
		}
		return messageDAO.getAllMessages();
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Message addMessage(Message message,
			@RequestParam(value = "profileName", required = false) String profileName) {
		if (profileName == null) {
			// TODO:
			// should throw exception of some kind
			// but controller advice for catching exceptions is not yet implemetned
			// so this wiil work for now
			return null;
		}

		message = messageDAO.addMessage(profileName, message);
		return message;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{messageId}")
	public @ResponseBody Message deleteMessage(@PathVariable("messageId") long id) {
		return messageDAO.removeMessage(id);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{messageId}")
	public @ResponseBody Message getMessage(@PathVariable("messageId") long id) {
		Message message = messageDAO.getMessage(id);
		return message;

	}
	*/
}
