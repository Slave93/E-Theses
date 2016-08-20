package rs.fon.elab.pzr.rest.model.request;

import java.util.Set;

public class CourseRequest {
	
	private String name;
	
	private String nameShort;
	
	private Set<Long> studiesIDs;

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

	public Set<Long> getStudiesIDs() {
		return studiesIDs;
	}

	public void setStudiesIDs(Set<Long> studiesIDs) {
		this.studiesIDs = studiesIDs;
	}	
	
}
