package rs.fon.elab.pzr.core.service;

import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import rs.fon.elab.pzr.core.exception.InvalidArgumentException;
import rs.fon.elab.pzr.core.model.Course;
import rs.fon.elab.pzr.core.model.FieldOfStudy;
import rs.fon.elab.pzr.core.model.Thesis;
import rs.fon.elab.pzr.core.repository.FieldOfStudyRepository;

public class FieldOfStudyServiceImpl implements FieldOfStudyService {

	FieldOfStudyRepository fieldOfStudyRepository;
	
	@Override
	public FieldOfStudy getFieldOfStudy(Long id) {
		return fieldOfStudyRepository.findOne(id);
	}

	@Override
	public FieldOfStudy getgetFieldOfStudyByName(String name) {
		return fieldOfStudyRepository.findByName(name);
	}

	@Override
	public Set<FieldOfStudy> getAllFieldsOfStudy() {
		return fieldOfStudyRepository.findAll();
	}

	@Transactional
	@Override
	public FieldOfStudy addFieldOfStudy(String name) {
		FieldOfStudy fieldOfStudy = fieldOfStudyRepository.findByName(name);
		if(fieldOfStudy!=null){
			return fieldOfStudy;
		}
		fieldOfStudy = new FieldOfStudy();
		fieldOfStudy.setName(name);
		return fieldOfStudyRepository.save(fieldOfStudy);
	}
	
	@Override
	@Transactional
	public FieldOfStudy updateFieldOfStudy(FieldOfStudy fieldOfStudy) {
		FieldOfStudy oldFieldOfStudy = fieldOfStudyRepository.findOne(fieldOfStudy.getId());
		if (oldFieldOfStudy == null) {
			throw new InvalidArgumentException("Oblast sa id-em " + fieldOfStudy.getId()
					+ " ne postoji u bazi!");
		}
		if (!fieldOfStudy.getName().equals(oldFieldOfStudy.getName())) {
			if (fieldOfStudyRepository.findByName(fieldOfStudy.getName()) != null) {
				throw new InvalidArgumentException("Oblast " + fieldOfStudy.getName()
						+ "već postoji u bazi!");
			}
		}
		return fieldOfStudyRepository.save(fieldOfStudy);
	}

	@Transactional
	@Override
	public void removeFieldOfStudy(Long id) {
		FieldOfStudy fieldOfStudy = fieldOfStudyRepository.findOne(id);
		if (fieldOfStudy == null) {
			throw new InvalidArgumentException("Oblast sa id-em " + id
					+ " ne postoji u bazi!");
		}
		fieldOfStudyRepository.delete(id);
	}

	public FieldOfStudyRepository getFieldOfStudyRepository() {
		return fieldOfStudyRepository;
	}

	public void setFieldOfStudyRepository(
			FieldOfStudyRepository fieldOfStudyRepository) {
		this.fieldOfStudyRepository = fieldOfStudyRepository;
	}

}
