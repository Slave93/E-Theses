package rs.fon.elab.pzr.core.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import rs.fon.elab.pzr.core.model.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {
	
	public List<Course> findAll();
	public Course findByName(String name);
	public Course findByNameShort(String nameShort);
}
