package rs.fon.elab.pzr.core.service;

import java.util.List;

import rs.fon.elab.pzr.core.model.Studies;

public interface StudiesService {

	public abstract Studies getStudies(Long id);
	
	public abstract Studies getStudiesByName(String name);

	public abstract List<Studies> getAllStudies();

	public abstract Studies addStudies(Studies studies);
	
	public abstract Studies updateStudies(Studies studies);
	
	public abstract void removeStudies(Long studiesID);
	
}