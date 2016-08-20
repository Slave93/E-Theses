package rs.fon.elab.pzr.core.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import rs.fon.elab.pzr.core.model.TFile;

public interface FileRepository extends CrudRepository<TFile, Long>{
	
	public abstract TFile findByFileName(String fileName);
	public abstract Set<TFile> findAll(); 
}
