package rs.fon.elab.pzr.rest.model.response.level2;

import java.util.List;

public class UserResponseLevel2 {

	protected Long id;

	protected String firstName;

	protected String lastName;

	protected String email;

	protected String studentsTranscript;

	protected boolean isAdmin;

	protected Long courseId;

	protected List<Long> thesisIDs;

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

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public List<Long> getThesisIDs() {
		return thesisIDs;
	}

	public void setThesisIDs(List<Long> thesisIDs) {
		this.thesisIDs = thesisIDs;
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
