package rs.fon.elab.pzr.rest.model.response.level1;

import java.util.HashSet;
import java.util.Set;

import rs.fon.elab.pzr.rest.model.response.level2.CourseResponseLevel2;

public class StudiesResponseLevel1 {
	
	protected Long id;
	
	protected String name;
	
	protected String nameShort;
	
	protected Set<CourseResponseLevel2> courses = new HashSet<CourseResponseLevel2>();

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

	public Set<CourseResponseLevel2> getCourses() {
		return courses;
	}

	public void setCourses(Set<CourseResponseLevel2> courses) {
		this.courses = courses;
	}	
}
