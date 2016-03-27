package rs.fon.elab.pzr.rest.model.response.level1;

import java.util.HashSet;
import java.util.Set;

import rs.fon.elab.pzr.rest.model.response.level2.StudiesResponseLevel2;
import rs.fon.elab.pzr.rest.model.response.level2.ThesisResponseLevel2;

public class CourseResponseLevel1 {
	
	protected Long id;
	
	protected String name;
	
	protected String nameShort;	
	
	protected Set<StudiesResponseLevel2> studies = new HashSet<StudiesResponseLevel2>();

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

	public Set<StudiesResponseLevel2> getStudies() {
		return studies;
	}

	public void setStudies(Set<StudiesResponseLevel2> studies) {
		this.studies = studies;
	}		
}
