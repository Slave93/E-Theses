package rs.fon.elab.pzr.core.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import rs.fon.elab.pzr.core.model.Studies;

public interface StudiesRepository extends CrudRepository<Studies, Long> {
	
	public List<Studies> findAll();
	public Studies findByName(String name);
	public Studies findByNameShort(String nameShort);
}
