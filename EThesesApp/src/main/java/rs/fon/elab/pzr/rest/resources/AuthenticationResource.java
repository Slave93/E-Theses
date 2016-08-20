package rs.fon.elab.pzr.rest.resources;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rs.fon.elab.pzr.core.model.Thesis;
import rs.fon.elab.pzr.core.model.User;
import rs.fon.elab.pzr.core.service.ThesisService;
import rs.fon.elab.pzr.core.service.UserService;
import rs.fon.elab.pzr.core.service.authentication.AuthenticationService;
import rs.fon.elab.pzr.rest.model.LoginData;
import rs.fon.elab.pzr.rest.model.response.level1.UserResponseLevel1;
import rs.fon.elab.pzr.rest.model.response.ticket.TicketResponse;
import rs.fon.elab.pzr.rest.model.util.RestFactory;

@RestController
@RequestMapping(value = "/authentication")
public class AuthenticationResource {

	private AuthenticationService authenticationService;
	private UserService userService;
	private ThesisService thesisService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody UserResponseLevel1 getAuthenticatedUser(
			@RequestHeader(value="ticket",required=false) String ticket) {
		User user = authenticationService.getTicketUser(ticket);
		return RestFactory.createUserResponseLevel1(user);
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody TicketResponse authenticate(
			@RequestBody LoginData loginData) {
		String ticket = authenticationService.authenticate(
				loginData.getEmail(), loginData.getPassword());
		return new TicketResponse(ticket);

	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void invalidateTicket(@RequestHeader(value="ticket",required=false) String ticket) {
		authenticationService.invalidateTicket(ticket);
	}

	public AuthenticationService getAuthenticationService() {
		return authenticationService;
	}

	public void setAuthenticationService(
			AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ThesisService getThesisService() {
		return thesisService;
	}

	public void setThesisService(ThesisService thesisService) {
		this.thesisService = thesisService;
	}	

}
