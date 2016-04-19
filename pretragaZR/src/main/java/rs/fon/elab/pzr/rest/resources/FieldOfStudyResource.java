package rs.fon.elab.pzr.rest.resources;

import java.util.Set;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rs.fon.elab.pzr.core.exception.InvalidArgumentException;
import rs.fon.elab.pzr.core.model.FieldOfStudy;
import rs.fon.elab.pzr.core.model.Tag;
import rs.fon.elab.pzr.core.service.FieldOfStudyService;
import rs.fon.elab.pzr.core.service.TagService;
import rs.fon.elab.pzr.core.service.util.ParamaterCheck;
import rs.fon.elab.pzr.rest.model.request.FieldOfStudyRequest;

@RestController
@RequestMapping(value = "/fields-of-study")
public class FieldOfStudyResource {

	private FieldOfStudyService fieldOfStudyService;

	// READ
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Set<FieldOfStudy> getFieldOfStudies() {
		return fieldOfStudyService.getAllFieldsOfStudy();
	}

	// CREATE
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody FieldOfStudy addFieldOfStudy(
			@RequestBody FieldOfStudyRequest fieldOfStudyRequest) {
		ParamaterCheck.mandatory("Naziv oblasti", fieldOfStudyRequest.getName());
		return fieldOfStudyService.addFieldOfStudy(fieldOfStudyRequest.getName());
	}

	// UPDATE
	@RequestMapping(method = RequestMethod.PUT, value = "/{fieldOfStudyID}")
	public @ResponseBody FieldOfStudy updateFieldOfStudy(@PathVariable("fieldOfStudyID") Long fieldOfStudyID,
			@RequestBody FieldOfStudyRequest fieldOfStudyRequest) {
		FieldOfStudy fieldOfStudy = fieldOfStudyService.getFieldOfStudy(fieldOfStudyID);
		if (fieldOfStudy == null) {
			throw new InvalidArgumentException("Oblast sa id-em " + fieldOfStudyID
					+ " ne postoji u bazi!");
		}
		if(fieldOfStudyRequest.getName()!=null){
			fieldOfStudy.setName(fieldOfStudyRequest.getName());
		}
		return fieldOfStudyService.updateFieldOfStudy(fieldOfStudy);
		
	}

	// DELETE
	@RequestMapping(method = RequestMethod.DELETE, value = "/{fieldOfStudyID}")
	public void deleteTag(@PathVariable("fieldOfStudyID") Long fieldOfStudyID) {
		fieldOfStudyService.removeFieldOfStudy(fieldOfStudyID);
	}

	public FieldOfStudyService getFieldOfStudyService() {
		return fieldOfStudyService;
	}

	public void setFieldOfStudyService(FieldOfStudyService fieldOfStudyService) {
		this.fieldOfStudyService = fieldOfStudyService;
	}
}
