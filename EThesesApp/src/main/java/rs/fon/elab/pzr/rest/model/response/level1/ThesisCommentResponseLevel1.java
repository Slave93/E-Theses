package rs.fon.elab.pzr.rest.model.response.level1;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import rs.fon.elab.pzr.core.model.Thesis;
import rs.fon.elab.pzr.core.model.User;
import rs.fon.elab.pzr.rest.model.response.level2.ThesisResponseLevel2;
import rs.fon.elab.pzr.rest.model.response.level2.UserResponseLevel2;

public class ThesisCommentResponseLevel1 {

	protected Long id;

	protected String message;
	
	protected Date datePosted;
	
	protected UserResponseLevel2 author;	
	
	protected ThesisResponseLevel2 thesis;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDatePosted() {
		return datePosted;
	}

	public void setDatePosted(Date datePosted) {
		this.datePosted = datePosted;
	}

	public UserResponseLevel2 getAuthor() {
		return author;
	}

	public void setAuthor(UserResponseLevel2 author) {
		this.author = author;
	}

	public ThesisResponseLevel2 getThesis() {
		return thesis;
	}

	public void setThesis(ThesisResponseLevel2 thesis) {
		this.thesis = thesis;
	}
	
}
