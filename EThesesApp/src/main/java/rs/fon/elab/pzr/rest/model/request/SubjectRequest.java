package rs.fon.elab.pzr.rest.model.request;

import java.util.List;
import java.util.Set;

public class SubjectRequest {
	
	private String name;
	
	private Set<Long> courseIDs;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Long> getCourseIDs() {
		return courseIDs;
	}

	public void setCourseIDs(Set<Long> courseIDs) {
		this.courseIDs = courseIDs;
	}	
	
}
