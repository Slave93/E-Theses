package rs.fon.elab.pzr.rest.model.response.level2;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import rs.fon.elab.pzr.core.model.TFile;
import rs.fon.elab.pzr.core.model.Tag;

public class ThesisResponseLevel2 {
	protected Long id;

	protected String name;

	protected Integer grade;

	protected Date datePosted;

	protected Date defenseDate;

	protected String description;

	protected Long courseId;

	protected Long userId;

	protected String userName;

	protected String userEmail;
	
	protected Integer viewCount = 0;

	protected Long mentorId;

	protected Set<Tag> tags = new HashSet<Tag>();

	protected Set<Long> commentIDs = new HashSet<Long>();

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

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Long getMentorId() {
		return mentorId;
	}

	public void setMentorId(Long mentorId) {
		this.mentorId = mentorId;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public Set<Long> getCommentIDs() {
		return commentIDs;
	}

	public void setCommentIDs(Set<Long> commentIDs) {
		this.commentIDs = commentIDs;
	}

	public TFile getFile() {
		return file;
	}

	public void setFile(TFile file) {
		this.file = file;
	}

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}	
}
