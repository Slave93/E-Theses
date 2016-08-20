package rs.fon.elab.pzr.rest.model.response.level1;

import java.util.List;
import java.util.Set;

import rs.fon.elab.pzr.rest.model.response.level2.CourseResponseLevel2;
import rs.fon.elab.pzr.rest.model.response.level2.ThesisResponseLevel2;

public class UserResponseLevel1 {

	protected Long id;

	protected String firstName;

	protected String lastName;

	protected String email;

	protected String studentsTranscript;

	protected boolean isAdmin;

	protected CourseResponseLevel2 course;

	protected Set<ThesisResponseLevel2> theses;

	protected String biography;

	protected String interests;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStudentsTranscript() {
		return studentsTranscript;
	}

	public void setStudentsTranscript(String studentsTranscript) {
		this.studentsTranscript = studentsTranscript;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public CourseResponseLevel2 getCourse() {
		return course;
	}

	public void setCourse(CourseResponseLevel2 course) {
		this.course = course;
	}

	public Set<ThesisResponseLevel2> getTheses() {
		return theses;
	}

	public void setTheses(Set<ThesisResponseLevel2> theses) {
		this.theses = theses;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getInterests() {
		return interests;
	}

	public void setInterests(String interests) {
		this.interests = interests;
	}
}
