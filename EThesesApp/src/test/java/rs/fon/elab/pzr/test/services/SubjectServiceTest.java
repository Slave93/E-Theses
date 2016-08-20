package rs.fon.elab.pzr.test.services;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import rs.fon.elab.pzr.core.exception.InvalidArgumentException;
import rs.fon.elab.pzr.core.model.Course;
import rs.fon.elab.pzr.core.model.Studies;

import rs.fon.elab.pzr.core.model.Tag;
import rs.fon.elab.pzr.core.model.Thesis;
import rs.fon.elab.pzr.core.repository.CourseRepository;
import rs.fon.elab.pzr.core.repository.StudiesRepository;

import rs.fon.elab.pzr.core.repository.TagRepository;
import rs.fon.elab.pzr.core.repository.ThesisRepository;
import rs.fon.elab.pzr.core.service.CourseService;


//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath*:context/core/core-context.xml" })
public class SubjectServiceTest {
/*
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private CourseService courseService;
	
	private int numSubjectsBeforeMethod;
	private int numCoursesBeforeMethod;
	private List<Subject> addedSubjects = new ArrayList<Subject>();
	private List<Course> addedCourses = new ArrayList<Course>();

	@Before
	public void beforeMethod(){
		numSubjectsBeforeMethod = subjectService.getAllSubjects().size();
		numCoursesBeforeMethod = courseService.getAllCourses().size();
	}
	
	@After
	public void afterMethod(){
		for(Subject subject : addedSubjects){
			subjectService.removeSubject(subject.getId());
		}
		assertEquals(numSubjectsBeforeMethod, subjectService.getAllSubjects().size());
		
		for(Course course : addedCourses){
			courseService.removeCourse(course.getId());
		}
		assertEquals(numCoursesBeforeMethod, courseService.getAllCourses().size());
	}
	
	@Test
	public void addSubject() {
		Subject subject = new Subject();
		subject.setName("TestSubject1");
		
		subjectService.addSubject(subject);
		addedSubjects.add(subject);
		
		assertEquals("TestSubject1", subjectService.getSubject(subject.getId()).getName());
		assertEquals(numSubjectsBeforeMethod+1, subjectService.getAllSubjects().size());
	}
	
	@Test
	public void addMultipleSubjects() {
		Subject subject1 = new Subject();
		subject1.setName("TestSubject1M");
		Subject subject2 = new Subject();
		subject2.setName("TestSubject2M");
		Subject subject3 = new Subject();
		subject3.setName("TestSubject3M");
		Subject subject4 = new Subject();
		subject4.setName("TestSubject4M");
		
		subjectService.addSubject(subject1);
		subjectService.addSubject(subject2);
		subjectService.addSubject(subject3);
		subjectService.addSubject(subject4);
		
		addedSubjects.add(subject1);
		addedSubjects.add(subject2);
		addedSubjects.add(subject3);
		addedSubjects.add(subject4);
		
		assertEquals(numSubjectsBeforeMethod+4, subjectService.getAllSubjects().size());
	}
	
	@Test
	public void addSubjectWithCourse(){
		Course course1 = new Course();
		course1.setName("TestCourse1");
		course1.setNameShort("TC1");		
		
		courseService.addCourse(course1);
		addedCourses.add(course1);
		
		Subject subject = new Subject();
		subject.setName("TestSubject1");
		Set<Course> subjectCourses = new HashSet<Course>();
		subjectCourses.add(course1);
		subject.setCourses(subjectCourses);
		
		subjectService.addSubject(subject);
		addedSubjects.add(subject);
		
		subject = subjectService.getSubject(subject.getId());
		
		assertEquals(1, subject.getCourses().size());
		assertEquals(course1.getId(), ((Course)(subject.getCourses().toArray()[0])).getId());
		assertEquals(course1.getName(), ((Course)(subject.getCourses().toArray()[0])).getName());
		assertEquals(course1.getNameShort(), ((Course)(subject.getCourses().toArray()[0])).getNameShort());		
	}
	
	@Test
	public void addSubjectWithMultipleCourses(){
		Course course1 = new Course();
		course1.setName("TestCourse1");
		course1.setNameShort("TC1");
		Course course2 = new Course();
		course2.setName("TestCourse2");
		course2.setNameShort("TC2");	
		Course course3 = new Course();
		course3.setName("TestCourse3");
		course3.setNameShort("TC3");
		
		courseService.addCourse(course1);
		courseService.addCourse(course2);
		courseService.addCourse(course3);
		addedCourses.add(course1);
		addedCourses.add(course2);
		addedCourses.add(course3);
		
		Subject subject = new Subject();
		subject.setName("TestSubject1");
		Set<Course> subjectCourses = new HashSet<Course>();
		subjectCourses.add(course1);
		subjectCourses.add(course2);
		subjectCourses.add(course3);
		subject.setCourses(subjectCourses);
		
		subjectService.addSubject(subject);
		addedSubjects.add(subject);
		
		subject = subjectService.getSubject(subject.getId());
		
		assertEquals(3, subject.getCourses().size());		
	}
	
	@Test
	public void addSubjectWithCoursesRemoveCourse(){
		Course course1 = new Course();
		course1.setName("TestCourse1");
		course1.setNameShort("TC1");
		Course course2 = new Course();
		course2.setName("TestCourse2");
		course2.setNameShort("TC2");	
		Course course3 = new Course();
		course3.setName("TestCourse3");
		course3.setNameShort("TC3");
		
		courseService.addCourse(course1);
		courseService.addCourse(course2);
		courseService.addCourse(course3);
		addedCourses.add(course1);
		addedCourses.add(course2);
		addedCourses.add(course3);
		
		List<Course> courses = courseService.getAllCourses();
		for(Course course: courses){
			System.out.println("Course " +course.getName()+" ID: "+ course.getId());
			System.out.println(courseService.getCourse(course.getId()));
		}
		
		Subject subject = new Subject();
		subject.setName("TestSubject1");
		Set<Course> subjectCourses = new HashSet<Course>();
		subjectCourses.add(course1);
		subjectCourses.add(course2);
		subjectCourses.add(course3);
		subject.setCourses(subjectCourses);
		
		subjectService.addSubject(subject);
		addedSubjects.add(subject);
		
		subject = subjectService.getSubject(subject.getId());		
		assertEquals(3, subject.getCourses().size());
		
		courseService.removeCourse(course2.getId());
		addedCourses.remove(course2);
		
		subject = subjectService.getSubject(subject.getId());
		assertEquals(2, subject.getCourses().size());
	}
	
	@Test(expected = InvalidArgumentException.class)	
	public void addSubjectAmbiguousName(){
		Subject subject = new Subject();
		subject.setName("TestSubject");
		
		subjectService.addSubject(subject);
		addedSubjects.add(subject);
		
		assertEquals(numSubjectsBeforeMethod+1, subjectService.getAllSubjects().size());
		
		Subject subject2 = new Subject();
		subject2.setName("TestSubject");	
		
		subjectService.addSubject(subject2);
	}
	
	@Test
	public void updateSubject(){
		Subject subject = new Subject();
		subject.setName("TestSubject1");
		
		subjectService.addSubject(subject);
		addedSubjects.add(subject);
		
		assertEquals("TestSubject1", subjectService.getSubject(subject.getId()).getName());
		assertEquals(numSubjectsBeforeMethod+1, subjectService.getAllSubjects().size());
		
		subject.setName("Changed Name");
		subjectService.updateSubject(subject);
		
		assertEquals("Changed Name", subjectService.getSubject(subject.getId()).getName());
		assertEquals(numSubjectsBeforeMethod+1, subjectService.getAllSubjects().size());
	}
	
	@Test(expected = InvalidArgumentException.class)
	public void updateSubjectAmbiguousName(){
		Subject subject = new Subject();
		subject.setName("TestSubject1");		
		subjectService.addSubject(subject);
		addedSubjects.add(subject);	
		
		Subject subject2 = new Subject();
		subject2.setName("TestSubject2");		
		subjectService.addSubject(subject2);
		addedSubjects.add(subject2);
		
		assertEquals(numSubjectsBeforeMethod+2, subjectService.getAllSubjects().size());
		
		subject.setName("TestSubject2");
		subjectService.updateSubject(subject);		
	}
	
	public void deleteSubject(){
		Subject subject1 = new Subject();
		subject1.setName("TestSubject1");
		Subject subject2 = new Subject();
		subject2.setName("TestSubject2");
		Subject subject3 = new Subject();
		subject3.setName("TestSubject3");
		Subject subject4 = new Subject();
		subject4.setName("TestSubject4");
		
		subjectService.addSubject(subject1);
		subjectService.addSubject(subject2);
		subjectService.addSubject(subject3);
		subjectService.addSubject(subject4);
		
		addedSubjects.add(subject1);
		addedSubjects.add(subject2);
		addedSubjects.add(subject3);
		addedSubjects.add(subject4);
		
		assertEquals(numSubjectsBeforeMethod+4, subjectService.getAllSubjects().size());
		
		subjectService.removeSubject(subject3.getId());
		addedSubjects.remove(subject3);
		
		assertEquals(numSubjectsBeforeMethod+3, subjectService.getAllSubjects().size());
		assertEquals(null, subjectService.getSubject(subject3.getId()));		
	}
	
	//GETTERS AND SETTERS
	public SubjectService getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(SubjectService subjectService) {
		this.subjectService = subjectService;
	}

	public CourseService getCourseService() {
		return courseService;
	}

	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	
	@Autowired
	private ThesisRepository thesisRepository;
	@Autowired
	private SubjectRepository subjectRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private StudiesRepository studiesRepository;
	@Autowired
	private TagRepository tagRepository;
	
	//@Test
	//@Transactional
	public void testThesisAdvancedSearch(){
		Studies studies1 = new Studies();
		studies1.setName("Nivo1");
		studies1.setNameShort("N1");
		studiesRepository.save(studies1);
		
		Studies studies2 = new Studies();
		studies2.setName("Nivo2");
		studies2.setNameShort("N2");
		studiesRepository.save(studies2);
		
		Course course1 = new Course();
		course1.setName("Kurs1");
		course1.setNameShort("K1");
		course1.getStudies().add(studies1);
		courseRepository.save(course1);
		Course course2 = new Course();
		course2.setName("Kurs2");
		course2.setNameShort("K2");
		course2.getStudies().add(studies2);
		courseRepository.save(course2);
		Course course3 = new Course();
		course3.setName("Kurs3");
		course3.setNameShort("K3");
		course3.getStudies().add(studies1);
		courseRepository.save(course3);
		
		Subject subject1 = new Subject();
		subject1.setName("Predmet1");
		subject1.getCourses().add(course2);
		subjectRepository.save(subject1);
		Subject subject2 = new Subject();
		subject2.setName("Predmet2");
		subject2.getCourses().add(course3);
		subjectRepository.save(subject2);
		Subject subject3 = new Subject();
		subject3.setName("Predmet3");
		subject3.getCourses().add(course1);
		subjectRepository.save(subject3);
		Subject subject4 = new Subject();
		subject4.setName("Predmet4");
		subject4.getCourses().add(course1);
		subjectRepository.save(subject4);
		
		Tag tag1 = new Tag();
		tag1.setValue("tag1");
		tagRepository.save(tag1);
		Tag tag2 = new Tag();
		tag2.setValue("tag2");
		tagRepository.save(tag2);
		Tag tag3 = new Tag();
		tag3.setValue("tag3");
		tagRepository.save(tag3);
		Tag tag4 = new Tag();
		tag4.setValue("tag4");
		tagRepository.save(tag4);
		Tag tag5 = new Tag();
		tag5.setValue("tag5");
		tagRepository.save(tag5);
		Tag tag6 = new Tag();
		tag6.setValue("tag6");
		tagRepository.save(tag6);
		
		
		
		Thesis thesis1 = new Thesis();
		thesis1.setName("PrviRad");
		thesis1.setDatePosted(new Date());
		thesis1.setSubject(subject3);
		thesis1.getTags().add(tag6);
		thesis1.getTags().add(tag5);
		thesis1.getTags().add(tag4);
		thesis1.getTags().add(tag1);
		thesisRepository.save(thesis1);
		Thesis thesis2 = new Thesis();
		thesis2.setName("DrugiRad");
		thesis2.setDatePosted(new Date());
		thesis2.setSubject(subject1);
		thesis2.getTags().add(tag5);
		thesis2.getTags().add(tag3);
		thesis2.getTags().add(tag2);
		thesis2.getTags().add(tag1);
		thesisRepository.save(thesis2);
		Thesis thesis3 = new Thesis();
		thesis3.setName("TreciiRad");
		thesis3.setDatePosted(new Date());
		thesis3.setSubject(subject1);
		thesis3.getTags().add(tag3);
		thesis3.getTags().add(tag2);
		thesis3.getTags().add(tag1);
		thesisRepository.save(thesis3);
		Thesis thesis4 = new Thesis();
		thesis4.setName("CetvrtiiRad");
		thesis4.setDatePosted(new Date());
		thesis4.setSubject(subject3);
		thesisRepository.save(thesis4);
		Thesis thesis5 = new Thesis();
		thesis5.setName("PetiRad");
		thesis5.setDatePosted(new Date());
		thesis5.setSubject(subject4);
		thesis5.getTags().add(tag5);
		thesis5.getTags().add(tag3);
		thesis5.getTags().add(tag2);
		thesisRepository.save(thesis5);
		
		
		System.out.println("#########");
		List<Thesis> thesisListSubject = thesisRepository.findByNameLikeTagsAndSubject("%ad%",null, 0l,"Predmet1");
//		assertEquals(2, thesisListSubject.size());
		for (Thesis thesis : thesisListSubject) {
			System.out.println(thesis.getName());
		}
		System.out.println("#########");
		List<Thesis> thesisListCourse = thesisRepository.findByNameLikeTagsAndCourse("%v%",null, 0l,"Kurs1");
//		assertEquals(2, thesisListCourse.size());
		for (Thesis thesis : thesisListCourse) {
			System.out.println(thesis.getName());
		}
		System.out.println("#########");
		List<Thesis> thesisListStudies = thesisRepository.findByNameLikeTagsAndStudies("%",null, 0l,"Nivo2");
//		assertEquals(2, thesisListStudies.size());
		for (Thesis thesis : thesisListStudies) {
			System.out.println(thesis.getName());
		}
		System.out.println("#########");
		List<Thesis> thesisListTagsWithoutTags = thesisRepository.findByNameLikeAndTags("%",null, 0l);
//		assertEquals(6, thesisListTagsWithoutTags.size());
		for (Thesis thesis : thesisListTagsWithoutTags) {
			System.out.println(thesis.getName());
		}
		System.out.println("#########");
		List<String> tagsToMatch = new ArrayList<String>();
		tagsToMatch.add(tag1.getValue());
		tagsToMatch.add(tag5.getValue());
		List<Thesis> thesisListTags = thesisRepository.findByNameLikeAndTags("%",tagsToMatch, (long)tagsToMatch.size());
//		assertEquals(2, thesisListTags.size());
		for (Thesis thesis : thesisListTags) {
			System.out.println(thesis.getName());
		}
		System.out.println("#########");
		System.out.println("#########");
		PageRequest pageRequest = new PageRequest(0,2);
		Page<Thesis> thesisListSubjectPagable = thesisRepository.findByNameLikeTagsAndSubjectPagable(pageRequest,"%ad%",null, 0l,"Predmet1");
//		assertEquals(2, thesisListSubjectPagable.getTotalElements());
		
		Page<Thesis> thesisListCoursePagable = thesisRepository.findByNameLikeTagsAndCoursePagable(pageRequest,"%v%",null, 0l,"Kurs1");
//		assertEquals(2, thesisListCoursePagable.getTotalElements());
		
		Page<Thesis> thesisListStudiesPagable = thesisRepository.findByNameLikeTagsAndStudiesPagable(pageRequest,"%",null, 0l,"Nivo2");
//		assertEquals(2, thesisListStudiesPagable.getTotalElements());
				
		Page<Thesis> thesisListTagsWithoutTagsPagable = thesisRepository.findByNameLikeAndTagsPagable(pageRequest,"%PetiRad%",null, 0l);
		System.out.println(thesisListTagsWithoutTagsPagable.getTotalElements());
//		assertEquals(1, thesisListTagsWithoutTagsPagable.getTotalElements());
		for (Thesis thesis : thesisListTagsWithoutTagsPagable.getContent()) {
			System.out.println(thesis.getName());
		}
		
		Page<Thesis> thesisListTagsPagable = thesisRepository.findByNameLikeAndTagsPagable(pageRequest,"%",tagsToMatch, (long)tagsToMatch.size());
//		assertEquals(2, thesisListTagsPagable.getTotalElements());
		
		for (int i = 0; i < 10; i++) {
			Thesis thesis = new Thesis();
			thesis.setDatePosted(new Date());
			thesis.setName(getRandomString(10));
			try{
			thesisRepository.save(thesis);
			}catch(Exception e){
				//probbably same random string
				i--;
			}
		}
		
		System.out.println("#########");
		
	}
	
	public ThesisRepository getThesisRepository() {
		return thesisRepository;
	}

	public void setThesisRepository(ThesisRepository thesisRepository) {
		this.thesisRepository = thesisRepository;
	}

	public SubjectRepository getSubjectRepository() {
		return subjectRepository;
	}

	public void setSubjectRepository(SubjectRepository subjectRepository) {
		this.subjectRepository = subjectRepository;
	}

	public CourseRepository getCourseRepository() {
		return courseRepository;
	}

	public void setCourseRepository(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	public StudiesRepository getStudiesRepository() {
		return studiesRepository;
	}

	public void setStudiesRepository(StudiesRepository studiesRepository) {
		this.studiesRepository = studiesRepository;
	}

	public TagRepository getTagRepository() {
		return tagRepository;
	}

	public void setTagRepository(TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}
	
	private String getRandomString(int stringLength) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < stringLength) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
	*/
}