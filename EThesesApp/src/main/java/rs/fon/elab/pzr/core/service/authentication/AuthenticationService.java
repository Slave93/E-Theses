package rs.fon.elab.pzr.core.service.authentication;

import rs.fon.elab.pzr.core.exception.AuthenticationException;
import rs.fon.elab.pzr.core.exception.InvalidTicketException;
import rs.fon.elab.pzr.core.model.User;

public interface AuthenticationService {
	public String authenticate(String username,String password);
	public String validate(String ticket);
	public User getTicketUser(String ticket);
	public void invalidateTicket(String ticket);
}
