package rs.fon.elab.pzr.core.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import rs.fon.elab.pzr.core.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	public List<User> findAll();
	public User findByEmail(String email);
}
