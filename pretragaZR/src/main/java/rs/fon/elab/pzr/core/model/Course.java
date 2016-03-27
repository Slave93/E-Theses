package rs.fon.elab.pzr.core.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "course")
public class Course {

	@Id
	@Column(name = "course_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Column(name = "name")
	protected String name;
	
	@Column(name = "name_short")
	protected String nameShort;	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "course_studies", joinColumns = { @JoinColumn(name = "course_id") }, inverseJoinColumns = { @JoinColumn(name = "studies_id") })
	protected Set<Studies> studies = new HashSet<Studies>();
	
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

	public Set<Studies> getStudies() {
		return studies;
	}

	public void setStudies(Set<Studies> studies) {
		this.studies = studies;
	}	
}
