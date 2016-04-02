package rs.fon.elab.pzr.rest.model.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import rs.fon.elab.pzr.core.model.Course;
import rs.fon.elab.pzr.core.model.Studies;
import rs.fon.elab.pzr.core.model.Thesis;
import rs.fon.elab.pzr.core.model.ThesisComment;
import rs.fon.elab.pzr.core.model.User;
import rs.fon.elab.pzr.rest.model.response.level1.CourseResponseLevel1;
import rs.fon.elab.pzr.rest.model.response.level1.StudiesResponseLevel1;
import rs.fon.elab.pzr.rest.model.response.level1.ThesisCommentResponseLevel1;
import rs.fon.elab.pzr.rest.model.response.level1.ThesisPageResponse;
import rs.fon.elab.pzr.rest.model.response.level1.ThesisResponseLevel1;
import rs.fon.elab.pzr.rest.model.response.level1.UserResponseLevel1;
import rs.fon.elab.pzr.rest.model.response.level2.CourseResponseLevel2;
import rs.fon.elab.pzr.rest.model.response.level2.StudiesResponseLevel2;
import rs.fon.elab.pzr.rest.model.response.level2.ThesisCommentResponseLevel2;
import rs.fon.elab.pzr.rest.model.response.level2.ThesisResponseLevel2;
import rs.fon.elab.pzr.rest.model.response.level2.UserResponseLevel2;

public class RestFactory {
	
	public static ThesisPageResponse CreateThesisPageResponse(Page<Thesis> thesisPage){
		ThesisPageResponse thesisPageResponse = new ThesisPageResponse();
		List<ThesisResponseLevel1> thesisResponseLevel1List = new ArrayList<ThesisResponseLevel1>();
		for(Thesis thesis: thesisPage.getContent()){
			thesisResponseLevel1List.add(createThesisResponseLevel1(thesis));
		}
		thesisPageResponse.setNumber(thesisPage.getNumber()+1);
		thesisPageResponse.setNumberOfElements(thesisPage.getNumberOfElements());
		thesisPageResponse.setSize(thesisPage.getSize());
		thesisPageResponse.setTotalElements(thesisPage.getTotalElements());
		thesisPageResponse.setTotalPages(thesisPage.getTotalPages());
		thesisPageResponse.setContent(thesisResponseLevel1List);	
		
		return thesisPageResponse;
	}

	public static ThesisResponseLevel1 createThesisResponseLevel1(Thesis thesis) {
		if (thesis == null) {
			return null;
		}
		ThesisResponseLevel1 thesisResponseLevel1 = new ThesisResponseLevel1();

		Set<ThesisCommentResponseLevel2> thesisCommentResponseLevel2List = new HashSet<ThesisCommentResponseLevel2>();
		for (ThesisComment comment : thesis.getComments()) {
			thesisCommentResponseLevel2List
					.add(createThesisCommentResponseLevel2(comment));
		}
		thesisResponseLevel1.setComments(thesisCommentResponseLevel2List);

		thesisResponseLevel1.setDatePosted(thesis.getDatePosted());
		thesisResponseLevel1.setDefenseDate(thesis.getDefenseDate());
		thesisResponseLevel1.setDescription(thesis.getDescription());
		thesisResponseLevel1.setFile(thesis.getFile());
		thesisResponseLevel1.setGrade(thesis.getGrade());
		thesisResponseLevel1.setId(thesis.getId());
		thesisResponseLevel1.setMentor(createUserResponseLevel2(
				thesis.getMentor()));
		thesisResponseLevel1.setName(thesis.getName());
		thesisResponseLevel1.setCourse(createCourseResponseLevel2(thesis
				.getCourse()));
		thesisResponseLevel1.setTags(thesis.getTags());
		thesisResponseLevel1.setFieldsOfStudy(thesis.getFieldOfStudies());
		thesisResponseLevel1.setUser(createUserResponseLevel2(thesis.getUser()));
		thesisResponseLevel1.setUserEmail(thesis.getUserEmail());
		thesisResponseLevel1.setUserName(thesis.getUserName());
		thesisResponseLevel1.setViewCount(thesis.getViewCount());

		return thesisResponseLevel1;
	}

	public static ThesisResponseLevel2 createThesisResponseLevel2(Thesis thesis) {
		if (thesis == null) {
			return null;
		}
		ThesisResponseLevel2 thesisResponseLevel2 = new ThesisResponseLevel2();
		Set<Long> commentIDs = new HashSet<Long>();
		for (ThesisComment comment : thesis.getComments()) {
			commentIDs.add(comment.getId());
		}
		thesisResponseLevel2.setCommentIDs(commentIDs);
		thesisResponseLevel2.setFile(thesis.getFile());
		thesisResponseLevel2.setDatePosted(thesis.getDatePosted());
		thesisResponseLevel2.setDefenseDate(thesis.getDefenseDate());
		thesisResponseLevel2.setDescription(thesis.getDescription());
		thesisResponseLevel2.setGrade(thesis.getGrade());
		thesisResponseLevel2.setId(thesis.getId());
		User mentor = thesis.getMentor();
		if (mentor != null) {
			thesisResponseLevel2.setMentorId(mentor.getId());
		}
		thesisResponseLevel2.setName(thesis.getName());
		Course course = thesis.getCourse();
		if (course != null) {
			thesisResponseLevel2.setCourseId(course.getId());
		}
		thesisResponseLevel2.setTags(thesis.getTags());
		thesisResponseLevel2.setFieldsOfStudy(thesis.getFieldOfStudies());
		User user = thesis.getUser();
		if (user != null) {
			thesisResponseLevel2.setUserId(user.getId());
		}
		thesisResponseLevel2.setUserEmail(thesis.getUserEmail());
		thesisResponseLevel2.setUserName(thesis.getUserName());
		thesisResponseLevel2.setViewCount(thesis.getViewCount());
		return thesisResponseLevel2;
	}

	public static ThesisCommentResponseLevel1 createThesisCommentResponseLevel1(
			ThesisComment comment) {
		if (comment == null) {
			return null;
		}
		ThesisCommentResponseLevel1 thesisCommentResponseLevel1 = new ThesisCommentResponseLevel1();
		thesisCommentResponseLevel1.setAuthor(createUserResponseLevel2(
				comment.getAuthor()));
		thesisCommentResponseLevel1.setDatePosted(comment.getDatePosted());
		thesisCommentResponseLevel1.setId(comment.getId());
		thesisCommentResponseLevel1.setMessage(comment.getMessage());
		thesisCommentResponseLevel1.setThesis(createThesisResponseLevel2(
				comment.getThesis()));

		return thesisCommentResponseLevel1;
	}

	public static ThesisCommentResponseLevel2 createThesisCommentResponseLevel2(
			ThesisComment comment) {
		if (comment == null) {
			return null;
		}
		ThesisCommentResponseLevel2 thesisCommentResponseLevel2 = new ThesisCommentResponseLevel2();
		User author = comment.getAuthor();
		if (author != null) {
			thesisCommentResponseLevel2.setAuthorId(author.getId());
		}
		thesisCommentResponseLevel2.setDatePosted(comment.getDatePosted());
		thesisCommentResponseLevel2.setId(comment.getId());
		thesisCommentResponseLevel2.setMessage(comment.getMessage());
		Thesis thesis = comment.getThesis();
		if (thesis != null) {
			thesisCommentResponseLevel2.setThesisId(thesis.getId());
		}

		return thesisCommentResponseLevel2;

	}

	public static UserResponseLevel1 createUserResponseLevel1(User user) {
		if (user == null) {
			return null;
		}
		UserResponseLevel1 userResponseLevel1 = new UserResponseLevel1();
		userResponseLevel1.setAdmin(user.isAdmin());
		userResponseLevel1.setBiography(user.getBiography());
		userResponseLevel1.setCourse(createCourseResponseLevel2(user
				.getCourse()));
		userResponseLevel1.setEmail(user.getEmail());
		userResponseLevel1.setFirstName(user.getFirstName());
		userResponseLevel1.setId(user.getId());
		userResponseLevel1.setInterests(user.getInterests());
		userResponseLevel1.setLastName(user.getLastName());
		userResponseLevel1.setStudentsTranscript(user.getStudentsTranscript());
		Set<ThesisResponseLevel2> thesisResponseLevel2 = new HashSet<ThesisResponseLevel2>();
		for(Thesis thesis: user.getTheses()){
			thesisResponseLevel2.add(createThesisResponseLevel2(thesis));
		}
		userResponseLevel1.setTheses(thesisResponseLevel2);

		return userResponseLevel1;

	}

	public static UserResponseLevel2 createUserResponseLevel2(User user) {
		if (user == null) {
			return null;
		}
		UserResponseLevel2 userResponseLevel2 = new UserResponseLevel2();
		userResponseLevel2.setAdmin(user.isAdmin());
		userResponseLevel2.setBiography(user.getBiography());
		Course course = user.getCourse();
		if (course != null) {
			userResponseLevel2.setCourseId(course.getId());
		}
		userResponseLevel2.setEmail(user.getEmail());
		userResponseLevel2.setFirstName(user.getFirstName());
		userResponseLevel2.setId(user.getId());
		userResponseLevel2.setInterests(user.getInterests());
		userResponseLevel2.setLastName(user.getLastName());
		userResponseLevel2.setStudentsTranscript(user.getStudentsTranscript());
		List<Long> thesisIDs = new ArrayList<Long>();
		for(Thesis thesis: user.getTheses()){
			thesisIDs.add(thesis.getId());
		}
		userResponseLevel2.setThesisIDs(thesisIDs);
		
		return userResponseLevel2;
	}
	

	public static CourseResponseLevel1 createCourseResponseLevel1(Course course) {
		if (course == null) {
			return null;
		}
		CourseResponseLevel1 courseResponseLevel1 = new CourseResponseLevel1();
		courseResponseLevel1.setId(course.getId());
		courseResponseLevel1.setName(course.getName());
		courseResponseLevel1.setNameShort(course.getNameShort());		
		Set<Studies> studies = course.getStudies();
		if(studies!=null){
			Set<StudiesResponseLevel2> studiesResponseLevel2 = new HashSet<StudiesResponseLevel2>();
			for (Studies studiesLevel : studies) {
				studiesResponseLevel2
						.add(createStudiesResponseLevel2(studiesLevel));
			}
			courseResponseLevel1.setStudies(studiesResponseLevel2);
		}
		return courseResponseLevel1;

	}

	public static CourseResponseLevel2 createCourseResponseLevel2(Course course) {
		if (course == null) {
			return null;
		}
		CourseResponseLevel2 courseResponseLevel2 = new CourseResponseLevel2();
		courseResponseLevel2.setId(course.getId());
		courseResponseLevel2.setName(course.getName());
		courseResponseLevel2.setNameShort(course.getNameShort());	
		Set<Studies> studies = course.getStudies();
		if (studies != null) {
			Set<Long> studiesIDs = new HashSet<Long>();
			for (Studies studiesLevel : studies) {
				studiesIDs.add(studiesLevel.getId());
			}
			courseResponseLevel2.setStudiesIDs(studiesIDs);
		}
		return courseResponseLevel2;

	}
	
	public static StudiesResponseLevel1 createStudiesResponseLevel1(Studies studies) {
		if (studies == null) {
			return null;
		}
		StudiesResponseLevel1 studiesResponseLevel1 = new StudiesResponseLevel1();
		studiesResponseLevel1.setId(studies.getId());
		studiesResponseLevel1.setName(studies.getName());
		studiesResponseLevel1.setNameShort(studies.getNameShort());
		Set<Course> courses = studies.getCourses();
		if (courses != null) {
			Set<CourseResponseLevel2> courseResponseLevel1 = new HashSet<CourseResponseLevel2>();
			for (Course course : courses) {
				courseResponseLevel1.add(createCourseResponseLevel2(course));
			}
			studiesResponseLevel1.setCourses(courseResponseLevel1);
		}
		return studiesResponseLevel1;

	}
	
	public static StudiesResponseLevel2 createStudiesResponseLevel2(Studies studies) {
		if (studies == null) {
			return null;
		}
		StudiesResponseLevel2 studiesResponseLevel2 = new StudiesResponseLevel2();
		studiesResponseLevel2.setId(studies.getId());
		studiesResponseLevel2.setName(studies.getName());
		studiesResponseLevel2.setNameShort(studies.getNameShort());
		Set<Course> courses = studies.getCourses();
		if (courses != null) {
			Set<Long> courseIDs = new HashSet<Long>();
			for (Course subject : courses) {
				courseIDs.add(subject.getId());
			}
			studiesResponseLevel2.setCourseIDs(courseIDs);
		}
		return studiesResponseLevel2;

	}	
}
