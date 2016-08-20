package rs.fon.elab.pzr.rest.model.response.level2;

import java.util.HashSet;
import java.util.Set;

public class StudiesResponseLevel2 {
	protected Long id;
	
	protected String name;
	
	protected String nameShort;
	
	protected Set<Long> courseIDs = new HashSet<Long>();

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

	public String getNameShort() {
		return nameShort;
	}

	public void setNameShort(String nameShort) {
		this.nameShort = nameShort;
	}

	public Set<Long> getCourseIDs() {
		return courseIDs;
	}

	public void setCourseIDs(Set<Long> courseIDs) {
		this.courseIDs = courseIDs;
	}	
}
