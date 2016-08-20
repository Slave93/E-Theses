package rs.fon.elab.pzr.core.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import rs.fon.elab.pzr.core.exception.InvalidArgumentException;
import rs.fon.elab.pzr.core.model.Course;
import rs.fon.elab.pzr.core.repository.CourseRepository;

public class CourseServiceImpl implements CourseService {

	private CourseRepository courseRepository;

	@Override
	public Course getCourse(Long id) {
		return courseRepository.findOne(id);
	}

	@Override
	public Course getCourseByName(String name) {
		return courseRepository.findByName(name);
	}

	@Override
	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	/*
	 * @Transactional
	 * 
	 * @Override public Set<Subject> getCourseSubjects(Long courseID) { Course
	 * course = courseRepository.findOne(courseID); if(course==null){ throw new
	 * InvalidArgumentException
	 * ("Smer sa id-em "+courseID+" ne postoji u bazi!"); } return
	 * course.getSubjects(); }
	 */

	@Transactional
	@Override
	public Course addCourse(Course course) {
		if (courseRepository.findByName(course.getName()) != null) {
			throw new InvalidArgumentException("Kurs " + course.getName()
					+ " već postoji u bazi!");
		}
		;
		if (courseRepository.findByNameShort(course.getNameShort()) != null) {
			throw new InvalidArgumentException("Kurs " + course.getNameShort()
					+ " već postoji u bazi!");
		}
		;
		return courseRepository.save(course);
	}

	/*
	 * @Transactional
	 * 
	 * @Override public Course addCourseSubjects(Long courseID, List<Long>
	 * subjectIDs){ Course course = courseRepository.findOne(courseID);
	 * if(course==null){ throw new
	 * InvalidArgumentException("Kurs sa id-em "+courseID+" ne postoji!"); }
	 * Set<Subject> subjects = new HashSet<Subject>();
	 * subjects.addAll(course.getSubjects()); for(Long subjectID: subjectIDs){
	 * Subject subject = subjectRepository.findOne(subjectID);
	 * if(subject==null){ throw new
	 * InvalidArgumentException("Predmet sa id-em "+subjectID+" ne postoji!"); }
	 * for(Subject exsistingSubject : subjects){
	 * if(subject.getName().equals(exsistingSubject.getName())){ throw new
	 * InvalidArgumentException
	 * ("Predmet "+subject.getName()+" je već dodat na smer "
	 * +course.getName()+"!"); } } subjects.add(subject);
	 * course.getSubjects().add(subject); } return
	 * courseRepository.save(course); }
	 */

	@Transactional
	@Override
	public Course updateCourse(Course course) {
		Course existingCourse = courseRepository.findOne(course.getId());
		if (existingCourse == null) {
			throw new InvalidArgumentException("Smer sa id-em "
					+ course.getId() + " ne postoji u bazi!");
		}

		if (!existingCourse.getName().equals(course.getName())) {
			if (courseRepository.findByName(course.getName()) != null) {
				throw new InvalidArgumentException("Kurs " + course.getName()
						+ " već postoji u bazi!");
			}
		}

		if (!existingCourse.getNameShort().equals(course.getNameShort())) {
			if (courseRepository.findByNameShort(course.getNameShort()) != null) {
				throw new InvalidArgumentException("Kurs "
						+ course.getNameShort() + " već postoji u bazi!");
			}
		}
		return courseRepository.save(course);
	}

	@Transactional
	@Override
	public void removeCourse(Long courseID) {
		Course course = courseRepository.findOne(courseID);
		if (course == null) {
			throw new InvalidArgumentException("Smer sa id-em " + courseID
					+ " ne postoji u bazi!");
		}
		courseRepository.delete(course);
	}

	/*
	 * @Override public Course removeCourseSubjects(Long courseID, List<Long>
	 * subjectIDs) { Course course = courseRepository.findOne(courseID);
	 * if(course==null){ throw new
	 * InvalidArgumentException("Kurs sa id-em "+courseID+" ne postoji!"); }
	 * for(Long subjectID: subjectIDs){ Subject subject =
	 * subjectRepository.findOne(subjectID); if(subject==null){ throw new
	 * InvalidArgumentException
	 * ("Predmet sa id-em "+subjectID+" ne postoji u bazi!"); }
	 * System.out.println("Removing subject"+subject.getName()+
	 * "for course "+course.getName()); boolean removed = false; for
	 * (Iterator<Subject> iter = course.getSubjects().iterator();
	 * iter.hasNext(); ) { Subject s = iter.next(); if
	 * (s.getId()==subject.getId()) { iter.remove(); removed = true; } }
	 * if(!removed){ throw new
	 * InvalidArgumentException("Predmet "+subject.getName
	 * ()+" nije na smeru "+course.getName()+"!"); } } return
	 * courseRepository.save(course); }
	 */

	// GETTERS AND SETTERS
	public CourseRepository getCourseRepository() {
		return courseRepository;
	}

	public void setCourseRepository(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}



}
