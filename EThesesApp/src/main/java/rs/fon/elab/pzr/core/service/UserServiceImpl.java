package rs.fon.elab.pzr.core.service;

import java.util.List;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import rs.fon.elab.pzr.core.exception.InvalidArgumentException;
import rs.fon.elab.pzr.core.exception.InvalidTicketException;
import rs.fon.elab.pzr.core.model.Thesis;
import rs.fon.elab.pzr.core.model.User;
import rs.fon.elab.pzr.core.repository.ThesisRepository;
import rs.fon.elab.pzr.core.repository.UserRepository;

public class UserServiceImpl implements UserService {

	private ThesisRepository thesisRepository;
	private UserRepository userRepository;
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public User getUser(Long userId) {
		return userRepository.findOne(userId);
	}

	@Override
	public User getUser(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Transactional
	@Override
	public User addUser(User user) {
		if (userRepository.findByEmail(user.getEmail()) != null) {
			throw new InvalidArgumentException("Korisnik sa email-om "
					+ user.getEmail() + " je već registrovan!");
		}
		
		EmailValidator validator = EmailValidator.getInstance();
		if(!validator.isValid(user.getEmail())){
			throw new InvalidArgumentException("Email koji ste uneli nije validan.");
		}
		/*
		 * Password expresion that requires one lower case letter, one upper
		 * case letter, one digit, 6-13 length, and no spaces.
		 */
		if (!user.getPassword().matches(
				"^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).{6,13}$")) {
			throw new InvalidArgumentException(
					"Šifra ne sme sadržati razmake, mora imati barem jedno malo slovo, jedno veliko slovo, jednu cifru i sadržati između 6 i 13 karaktera.");
		}

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Transactional
	@Override
	public User updateUser(User user) {
		if (userRepository.findOne(user.getId()) == null) {
			throw new InvalidArgumentException("Korisnik sa id-em "
					+ user.getId() + " ne postoji u bazi!");
		}
		try {
			org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			String email = springUser.getUsername();
			User loggedInUser = getUser(email);
			if (!loggedInUser.isAdmin() && loggedInUser.getId() != user.getId()) {
				throw new Exception();
			}
		} catch (Exception e) {
			throw new InvalidTicketException(
					"Morate biti ulogovani kako bi menjali profil!");
		}
		return userRepository.save(user);
	}

	@Transactional
	@Override
	public void deleteUser(Long userId) {
		User user = userRepository.findOne(userId);
		if (user == null) {
			throw new InvalidArgumentException("Korisnik sa id-em " + userId
					+ " ne postoji u bazi!");
		}
		try {
			org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			String email = springUser.getUsername();
			User loggedInUser = getUser(email);
			if (!loggedInUser.isAdmin() && loggedInUser.getId() != user.getId()) {
				throw new Exception();
			}
		} catch (Exception e) {
			throw new InvalidTicketException(
					"Morate biti ulogovani kako bi menjali profil.");
		}
		userRepository.delete(user);

	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public ThesisRepository getThesisRepository() {
		return thesisRepository;
	}

	public void setThesisRepository(ThesisRepository thesisRepository) {
		this.thesisRepository = thesisRepository;
	}

}
