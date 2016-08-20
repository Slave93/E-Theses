package rs.fon.elab.pzr.rest.model.response.level2;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import rs.fon.elab.pzr.core.model.Thesis;
import rs.fon.elab.pzr.core.model.User;

public class ThesisCommentResponseLevel2 {

	protected Long id;

	protected String message;
	
	protected Date datePosted;
	
	protected Long authorId;	
	
	protected Long thesisId;

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

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public Long getThesisId() {
		return thesisId;
	}

	public void setThesisId(Long thesisId) {
		this.thesisId = thesisId;
	}
	
}
