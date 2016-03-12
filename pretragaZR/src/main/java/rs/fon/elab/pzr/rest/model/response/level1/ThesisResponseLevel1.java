package rs.fon.elab.pzr.rest.model.response.level1;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import rs.fon.elab.pzr.core.model.TFile;
import rs.fon.elab.pzr.core.model.Tag;
import rs.fon.elab.pzr.rest.model.response.level2.SubjectResponseLevel2;
import rs.fon.elab.pzr.rest.model.response.level2.ThesisCommentResponseLevel2;
import rs.fon.elab.pzr.rest.model.response.level2.UserResponseLevel2;

public class ThesisResponseLevel1 {
	protected Long id;

	protected String name;

	protected Integer grade;

	protected Date datePosted;

	protected Date defenseDate;

	protected String description;

	protected SubjectResponseLevel2 subject;

	protected UserResponseLevel2 user;

	protected String userName;

	protected String userEmail;
	
	protected Integer viewCount = 0;

	protected UserResponseLevel2 mentor;

	protected Set<Tag> tags = new HashSet<Tag>();

	protected Set<ThesisCommentResponseLevel2> comments = new HashSet<ThesisCommentResponseLevel2>();

	protected TFile file;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Date getDatePosted() {
		return datePosted;
	}

	public void setDatePosted(Date datePosted) {
		this.datePosted = datePosted;
	}

	public Date getDefenseDate() {
		return defenseDate;
	}

	public void setDefenseDate(Date defenseDate) {
		this.defenseDate = defenseDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SubjectResponseLevel2 getSubject() {
		return subject;
	}

	public void setSubject(SubjectResponseLevel2 subject) {
		this.subject = subject;
	}

	public UserResponseLevel2 getUser() {
		return user;
	}

	public void setUser(UserResponseLevel2 user) {
		this.user = user;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public UserResponseLevel2 getMentor() {
		return mentor;
	}

	public void setMentor(UserResponseLevel2 mentor) {
		this.mentor = mentor;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public TFile getFile() {
		return file;
	}

	public void setFile(TFile file) {
		this.file = file;
	}

	public Set<ThesisCommentResponseLevel2> getComments() {
		return comments;
	}

	public void setComments(Set<ThesisCommentResponseLevel2> comments) {
		this.comments = comments;
	}

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}	

}
