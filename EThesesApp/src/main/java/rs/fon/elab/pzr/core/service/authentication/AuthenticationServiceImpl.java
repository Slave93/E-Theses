package rs.fon.elab.pzr.core.service.authentication;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import rs.fon.elab.pzr.core.exception.AuthenticationException;
import rs.fon.elab.pzr.core.exception.InvalidTicketException;
import rs.fon.elab.pzr.core.model.User;
import rs.fon.elab.pzr.core.model.ticket.SimpleTicket;
import rs.fon.elab.pzr.core.repository.UserRepository;

public class AuthenticationServiceImpl implements AuthenticationService {

	private TicketService ticketService;

	private UserRepository userRepository;

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public String authenticate(String email, String password) throws AuthenticationException {
		if (email == null || password == null) {
			throw new AuthenticationException("Bad credentials");
		}
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new AuthenticationException("Bad credentials");
		}

		String encodedPassword = user.getPassword();
		boolean matches = passwordEncoder.matches(password, encodedPassword);
		if (!matches) {
			throw new AuthenticationException("Bad credentials");
		}
		return ticketService.generateTicket(user.getId()).getTicket();

	}

	@Override
	public String validate(String ticket) throws InvalidTicketException {
		if (ticket == null) {
			throw new InvalidTicketException("Must provide a valid ticket!");
		}
		Long userId = ticketService.getTicketUserId(ticket);
		if (userId == null) {
			throw new InvalidTicketException("Provided ticket is not valid! Your ticket may be expired.");
		}
		ticketService.prolongTicket(ticket);
		User user = userRepository.findOne(userId);
		return user.getEmail();

	}
	
	@Override
	public User getTicketUser(String ticket){
		if(ticket==null){
			throw new InvalidTicketException("Must provide a valid ticket!");
		}	    
		Long userId = ticketService.getTicketUserId(ticket);
		if(userId==null){
			throw new InvalidTicketException("Must provide a valid ticket!");
		}
		return userRepository.findOne(userId);
	}
	
	@Override
	public void invalidateTicket(String ticket){
		if(ticket==null){
			return;
		}
		ticketService.invalidateTicket(ticket);
	}	

	public TicketService getTicketService() {
		return ticketService;
	}

	public void setTicketService(TicketService ticketService) {
		this.ticketService = ticketService;
	}	

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public BCryptPasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	

}
