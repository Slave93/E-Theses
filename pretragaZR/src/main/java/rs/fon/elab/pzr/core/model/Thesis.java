package rs.fon.elab.pzr.core.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "thesis")
public class Thesis {
	
		
		@Id
		@Column(name = "thesis_id")
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		protected Long id;

		@Column(name = "name")
		protected String name;
		
		@Column(name="grade")
		protected Integer grade;
		
		@Column(name="date_posted")
		protected Date datePosted;
		
		@Column(name="defense_date")
		protected Date defenseDate;
		
		@Column(name="description")
		protected String description;
		
		@ManyToOne
		@JoinColumn(name = "course_id")
		protected Course course;
		
		@ManyToOne
		@JoinColumn(name = "file_id")
		protected TFile file;
		
		@ManyToOne
		@JoinColumn(name = "user_id")
		protected User user;
		
		@Column(name="user_name")
		protected String userName;
		
		@Column(name="user_email")
		protected String userEmail;
		
		@Column(name="view_count")
		protected Integer viewCount = 0;
				
		@ManyToOne
		@JoinColumn(name = "user_mentor_id")
		protected User mentor;
		
		@OneToMany(mappedBy = "thesis", fetch = FetchType.EAGER)
		protected Set<ThesisComment> comments = new HashSet<ThesisComment>();
		
		@ManyToMany(fetch = FetchType.EAGER)
		@JoinTable(name = "thesis_tag", joinColumns = { @JoinColumn(name = "thesis_id") }, inverseJoinColumns = { @JoinColumn(name = "tag_id") })
		protected Set<Tag> tags = new HashSet<Tag>();
		
		@ManyToMany(fetch = FetchType.EAGER)
		@JoinTable(name = "thesis_field_of_study", joinColumns = { @JoinColumn(name = "thesis_id") }, inverseJoinColumns = { @JoinColumn(name = "field_of_study_id") })
		protected Set<FieldOfStudy> fieldOfStudies = new HashSet<>(); 
		
		@OneToMany(mappedBy = "thesis", fetch = FetchType.EAGER, cascade=CascadeType.ALL,orphanRemoval=true)
	    private Set<ThesisKeyword> thesisKeywords = new HashSet<ThesisKeyword>();
		

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

		public Course getCourse() {
			return course;
		}

		public void setCourse(Course course) {
			this.course = course;
		}

		public User getMentor() {
			return mentor;
		}

		public void setMentor(User mentor) {
			this.mentor = mentor;
		}		

		public Set<ThesisComment> getComments() {
			return comments;
		}

		public void setComments(Set<ThesisComment> comments) {
			this.comments = comments;
		}

		public Set<Tag> getTags() {
			return tags;
		}

		public void setTags(Set<Tag> tags) {
			this.tags = tags;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public TFile getFile() {
			return file;
		}

		public void setFile(TFile file) {
			this.file = file;
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

		public Integer getViewCount() {
			return viewCount;
		}

		public void setViewCount(Integer viewCount) {
			this.viewCount = viewCount;
		}

		public Set<ThesisKeyword> getThesisKeywords() {
			return thesisKeywords;
		}

		public void setThesisKeywords(Set<ThesisKeyword> thesisKeywords) {
			this.thesisKeywords = thesisKeywords;
		}

		public Set<FieldOfStudy> getFieldOfStudies() {
			return fieldOfStudies;
		}

		public void setFieldOfStudies(Set<FieldOfStudy> fieldOfStudies) {
			this.fieldOfStudies = fieldOfStudies;
		}		
}
