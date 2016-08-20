package rs.fon.elab.pzr.rest.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rs.fon.elab.pzr.core.exception.InvalidArgumentException;
import rs.fon.elab.pzr.core.model.Course;
import rs.fon.elab.pzr.core.model.Thesis;
import rs.fon.elab.pzr.core.model.User;
import rs.fon.elab.pzr.core.service.CourseService;
import rs.fon.elab.pzr.core.service.ThesisService;
import rs.fon.elab.pzr.core.service.UserService;
import rs.fon.elab.pzr.core.service.util.ParamaterCheck;
import rs.fon.elab.pzr.rest.model.LoginData;
import rs.fon.elab.pzr.rest.model.request.AdminPrivilegeRequest;
import rs.fon.elab.pzr.rest.model.request.UserRequest;
import rs.fon.elab.pzr.rest.model.response.level1.UserResponseLevel1;
import rs.fon.elab.pzr.rest.model.util.RestFactory;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	private UserService userService;
	private CourseService courseService;
	private ThesisService thesisService;

	// READ
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<UserResponseLevel1> getUsers() {
		List<User> userList = userService.getAllUsers();
		List<UserResponseLevel1> userResponseList = new ArrayList<UserResponseLevel1>();
		for (User user : userList) {
			userResponseList.add(RestFactory.createUserResponseLevel1(user));
		}
		return userResponseList;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{userID}")
	public @ResponseBody UserResponseLevel1 getUser(
			@PathVariable("userID") Long userID) {
			return RestFactory.createUserResponseLevel1(
				userService.getUser(userID));
	}

	// CREATE
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody UserResponseLevel1 addUser(
			@RequestBody LoginData loginData) {
		ParamaterCheck.mandatory("Email ", loginData.getEmail());
		ParamaterCheck.mandatory("Password ", loginData.getPassword());
		User user = new User();
		user.setEmail(loginData.getEmail());
		user.setPassword(loginData.getPassword());
		user = userService.addUser(user);
		UserResponseLevel1 userResponse = new UserResponseLevel1();
		userResponse.setId(user.getId());
		userResponse.setEmail(user.getEmail());
		return userResponse;
	}

	// UPDATE
	@RequestMapping(method = RequestMethod.PUT, value = "/{userID}")
	public @ResponseBody UserResponseLevel1 updateUser(
			@RequestBody UserRequest userRequest,
			@PathVariable("userID") Long userID) {

		User user = userService.getUser(userID);
		if (user == null) {
			throw new InvalidArgumentException("Korisnik sa id-em " + userID
					+ " ne postoji u bazi!");
		}

		if (userRequest.getBiography() != null) {
			user.setBiography(userRequest.getBiography());
		}
		if (userRequest.getCourseName() != null) {
			Course course = courseService.getCourseByName(userRequest
					.getCourseName());
			user.setCourse(course);
		}
		if (userRequest.getFirstName() != null) {
			user.setFirstName(userRequest.getFirstName());
		}
		if (userRequest.getInterests() != null) {
			user.setInterests(userRequest.getInterests());
		}
		if (userRequest.getLastName() != null) {
			user.setLastName(userRequest.getLastName());
		}
		if (userRequest.getStudentsTranscript() != null) {
			user.setStudentsTranscript(userRequest.getStudentsTranscript());
		}
		return RestFactory.createUserResponseLevel1(
				userService.updateUser(user));

	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{userID}/admin")
	public @ResponseBody UserResponseLevel1 changeAdminPrivilege(
			@PathVariable("userID") Long userID,
			@RequestBody AdminPrivilegeRequest adminPrivilegeRequest) {
		ParamaterCheck.mandatory("admin", adminPrivilegeRequest.isAdmin());
		User user = userService.getUser(userID);
		if (user == null) {
			throw new InvalidArgumentException("Korisnik sa id-em " + userID
					+ " ne postoji u bazi!");
		}
		user.setAdmin(adminPrivilegeRequest.isAdmin());
		return RestFactory.createUserResponseLevel1(
				userService.updateUser(user));
	}

	// DELETE
	@RequestMapping(method = RequestMethod.DELETE, value = "/{userID}")
	public void deleteUser(@PathVariable("userID") Long userID) {
		userService.deleteUser(userID);
	}

	// GETTERS AND SETTERS
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public CourseService getCourseService() {
		return courseService;
	}

	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	public ThesisService getThesisService() {
		return thesisService;
	}

	public void setThesisService(ThesisService thesisService) {
		this.thesisService = thesisService;
	}

}
