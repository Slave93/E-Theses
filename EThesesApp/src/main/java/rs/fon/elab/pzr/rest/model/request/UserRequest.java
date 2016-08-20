package rs.fon.elab.pzr.rest.model.request;

public class UserRequest {

	protected String firstName;

	protected String lastName;

	protected String studentsTranscript;

	protected String courseName;	

	protected String biography;

	protected String interests;	

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

	public String getStudentsTranscript() {
		return studentsTranscript;
	}

	public void setStudentsTranscript(String studentsTranscript) {
		this.studentsTranscript = studentsTranscript;
	}	

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
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
