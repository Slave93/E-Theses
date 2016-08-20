package rs.fon.elab.pzr.core.service;

import java.util.Set;

import rs.fon.elab.pzr.core.model.FieldOfStudy;
import rs.fon.elab.pzr.core.model.Tag;

public interface FieldOfStudyService {

	public abstract FieldOfStudy getFieldOfStudy(Long id);
	
	public abstract FieldOfStudy getgetFieldOfStudyByName(String name);
	
	public abstract Set<FieldOfStudy> getAllFieldsOfStudy();
	
	public abstract FieldOfStudy addFieldOfStudy(String name);
	
	public abstract void removeFieldOfStudy(Long id);

	public abstract FieldOfStudy updateFieldOfStudy(FieldOfStudy fieldOfStudy);
	
}
