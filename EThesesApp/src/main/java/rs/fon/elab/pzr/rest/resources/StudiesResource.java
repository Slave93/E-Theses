package rs.fon.elab.pzr.rest.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rs.fon.elab.pzr.core.exception.InvalidArgumentException;
import rs.fon.elab.pzr.core.model.Studies;
import rs.fon.elab.pzr.core.service.StudiesService;
import rs.fon.elab.pzr.core.service.util.ParamaterCheck;
import rs.fon.elab.pzr.rest.model.request.StudiesRequest;
import rs.fon.elab.pzr.rest.model.response.level1.StudiesResponseLevel1;
import rs.fon.elab.pzr.rest.model.util.RestFactory;

@RestController
@RequestMapping(value = "/studies")
public class StudiesResource {

	private StudiesService studiesService;

	// READ
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<StudiesResponseLevel1> getStudies(
			@RequestParam(value = "studiesName", required = false) String studiesName) {
		List<Studies> studiesList = studiesService.getAllStudies();
		List<StudiesResponseLevel1> studiesResponseLevel1 = new ArrayList<StudiesResponseLevel1>();
		if (studiesName != null) {
			studiesResponseLevel1.add(RestFactory
					.createStudiesResponseLevel1(studiesService
							.getStudiesByName(studiesName)));
			return studiesResponseLevel1;
		}
		for (Studies studies : studiesList) {
			studiesResponseLevel1.add(RestFactory.createStudiesResponseLevel1(studies));
		}
		return studiesResponseLevel1;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{studiesID}")
	public @ResponseBody StudiesResponseLevel1 getStudiesById(
			@PathVariable("studiesID") Long id) {
		return RestFactory.createStudiesResponseLevel1(studiesService.getStudies(id));
	}

	/*
	 * @RequestMapping(method = RequestMethod.GET, value =
	 * "/{courseID}/subjects") public @ResponseBody List<SubjectResponse>
	 * getCourseSubjects(@PathVariable("courseID") Long courseID) {
	 * List<SubjectResponse> subjectResponseList = new
	 * ArrayList<SubjectResponse>(); Set<Subject> subjectList =
	 * courseService.getCourseSubjects(courseID); for(Subject
	 * subject:subjectList){
	 * subjectResponseList.add(RestFactory.createSubjectResponse(subject)); }
	 * return subjectResponseList; }
	 */

	// CREATE
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody StudiesResponseLevel1 addStudies(
			@RequestBody StudiesRequest studiesRequest) {
		ParamaterCheck.mandatory("Naziv nivoa studija", studiesRequest.getName());
		ParamaterCheck.mandatory("Skraćeni naziv nivoa studija",
				studiesRequest.getNameShort());
		Studies studies = new Studies();
		studies.setName(studiesRequest.getName());
		studies.setNameShort(studiesRequest.getNameShort());
		return RestFactory.createStudiesResponseLevel1(studiesService.addStudies(studies));
	}

	/*
	 * @RequestMapping(method = RequestMethod.POST, value =
	 * "/{courseID}/subjects") public @ResponseBody CourseResponse
	 * addCourseSubjects(@RequestBody CourseSubjectsRequest
	 * courseSubjectsRequest, @PathVariable("courseID") Long courseID) {
	 * ParamaterCheck.mandatory("Predmeti",
	 * courseSubjectsRequest.getSubjectIDs()); for(Long subjectID:
	 * courseSubjectsRequest.getSubjectIDs()){
	 * ParamaterCheck.mandatory("Id predmeta ", subjectID.toString()); } Course
	 * courseNew =
	 * courseService.addCourseSubjects(courseID,courseSubjectsRequest
	 * .getSubjectIDs()); return RestFactory.createCourseResponse(courseNew); }
	 */

	// UPDATE
	@RequestMapping(method = RequestMethod.PUT, value = "/{studiesID}")
	public @ResponseBody StudiesResponseLevel1 updateStudies(
			@RequestBody StudiesRequest studiesRequest,
			@PathVariable("studiesID") Long studiesID) {
		Studies studies = studiesService.getStudies(studiesID);
		if (studies == null) {
			throw new InvalidArgumentException("Nivo studija sa id-em " + studiesID
					+ " ne postoji u bazi!");
		}
		if (studiesRequest.getName() != null) {
			studies.setName(studiesRequest.getName());
		}
		if (studiesRequest.getNameShort() != null) {
			studies.setNameShort(studiesRequest.getNameShort());
		}
		return RestFactory.createStudiesResponseLevel1(studiesService.updateStudies(studies));
	}

	// DELETE
	@RequestMapping(method = RequestMethod.DELETE, value = "/{studiesID}")
	public void removeStudies(@PathVariable("studiesID") Long studiesID) {
		studiesService.removeStudies(studiesID);
	}

	/*
	 * @RequestMapping(method = RequestMethod.DELETE,
	 * value="/{courseID}/subjects") public CourseResponse
	 * removeCourseSubjects(@RequestBody CourseSubjectsRequest
	 * courseSubjectsRequest,@PathVariable("courseID") Long courseID) {
	 * ParamaterCheck.mandatory("Predmeti",
	 * courseSubjectsRequest.getSubjectIDs()); for(Long subjectID:
	 * courseSubjectsRequest.getSubjectIDs()){
	 * ParamaterCheck.mandatory("Id predmeta ", subjectID.toString()); } Course
	 * courseDeleted =
	 * courseService.removeCourseSubjects(courseID,courseSubjectsRequest
	 * .getSubjectIDs()); return
	 * RestFactory.createCourseResponse(courseDeleted); }
	 */

	// GETTERS AND SETTERS
	public StudiesService getStudiesService() {
		return studiesService;
	}

	public void setStudiesService(StudiesService studiesService) {
		this.studiesService = studiesService;
	}

}
