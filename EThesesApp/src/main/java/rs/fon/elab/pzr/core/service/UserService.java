package rs.fon.elab.pzr.core.service;

import java.util.List;

import rs.fon.elab.pzr.core.model.Thesis;
import rs.fon.elab.pzr.core.model.User;

public interface UserService {

	public User getUser(Long userId);
	
	public User getUser(String email);
	
	public List<User> getAllUsers();

	public User addUser(User user);
	
	public User updateUser(User user);
	
	public void deleteUser(Long userId);

}
