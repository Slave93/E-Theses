package rs.fon.elab.pzr.core.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import rs.fon.elab.pzr.core.exception.InvalidArgumentException;
import rs.fon.elab.pzr.core.model.Studies;
import rs.fon.elab.pzr.core.repository.StudiesRepository;

public class StudiesServiceImpl implements StudiesService {

	private StudiesRepository studiesRepository;

	@Override
	public Studies getStudies(Long id) {
		return studiesRepository.findOne(id);
	}

	@Override
	public Studies getStudiesByName(String name) {
		return studiesRepository.findByName(name);
	}

	@Override
	public List<Studies> getAllStudies() {
		return studiesRepository.findAll();
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
	public Studies addStudies(Studies studies) {
		if (studiesRepository.findByName(studies.getName()) != null) {
			throw new InvalidArgumentException("Nivo studija " + studies.getName()
					+ " već postoji u bazi!");
		}
		;
		if (studiesRepository.findByNameShort(studies.getNameShort()) != null) {
			throw new InvalidArgumentException("Nivo studija " + studies.getNameShort()
					+ " već postoji u bazi!");
		}
		;
		return studiesRepository.save(studies);
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
	public Studies updateStudies(Studies studies) {
		Studies existingStudies = studiesRepository.findOne(studies.getId());
		if (existingStudies == null) {
			throw new InvalidArgumentException("Nivo studija sa id-em "
					+ studies.getId() + " ne postoji u bazi!");
		}

		if (!existingStudies.getName().equals(studies.getName())) {
			if (studiesRepository.findByName(studies.getName()) != null) {
				throw new InvalidArgumentException("Nivo studija " + studies.getName()
						+ " već postoji u bazi!");
			}
		}

		if (!existingStudies.getNameShort().equals(studies.getNameShort())) {
			if (studiesRepository.findByNameShort(studies.getNameShort()) != null) {
				throw new InvalidArgumentException("Nivo studija "
						+ studies.getNameShort() + " već postoji u bazi!");
			}
		}
		return studiesRepository.save(studies);
	}

	@Transactional
	@Override
	public void removeStudies(Long studiesID) {
		Studies studies = studiesRepository.findOne(studiesID);
		if (studies == null) {
			throw new InvalidArgumentException("Nivo studija sa id-em " + studiesID
					+ " ne postoji u bazi!");
		}
		studiesRepository.delete(studies);
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
	public StudiesRepository getStudiesRepository() {
		return studiesRepository;
	}

	public void setStudiesRepository(StudiesRepository studiesRepository) {
		this.studiesRepository = studiesRepository;
	}

}
