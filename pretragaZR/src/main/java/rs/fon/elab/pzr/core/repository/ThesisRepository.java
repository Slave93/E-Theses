package rs.fon.elab.pzr.core.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import rs.fon.elab.pzr.core.model.Thesis;
import rs.fon.elab.pzr.core.model.User;

public interface ThesisRepository extends PagingAndSortingRepository<Thesis, Long>  {
	
	public List<Thesis> findAll();
	public Thesis findByUser(User user);
	public Thesis findByName(String name);		
	
	
	@Query("SELECT distinct t from Thesis t where t.name like ?1 and ?3  <= ("
			+ "SELECT count(tag2.id) from Thesis t2 left join t2.tags tag2 where t2.id=t.id and tag2.value in(?2)"
			+ ")")
	public Page<Thesis> findByNameLikeAndTagsPagable(Pageable pageRequest,String thesisName,List<String> tagValues,Long matchLimit);
	
	@Query("SELECT distinct t from Thesis t where t.name like ?1 and t.course.name = ?4 and ?3 <= ("
			+ "SELECT count(tag2.id) from Thesis t2 left join t2.tags tag2 where t2.id=t.id and tag2.value in(?2)"
			+ ")")
	public Page<Thesis> findByNameLikeTagsAndCoursePagable(Pageable pageRequest,String thesisName,List<String> tagValues,Long matchLimit,String courseName);
	
	@Query("SELECT distinct t from Thesis t join t.course.studies s where t.name like ?1 and s.name = ?4 and ?3 <= ("
			+ "SELECT count(tag2.id) from Thesis t2 left join t2.tags tag2 where t2.id=t.id and tag2.value in(?2)"
			+ ")")
	public Page<Thesis> findByNameLikeTagsAndStudiesPagable(Pageable pageRequest,String thesisName,List<String> tagValues,Long matchLimit,String studiesName);		
	
	
	
	
	/*@Query("SELECT distinct t from Thesis t where t.name like ?1 and ?3  <= ("
			+ "SELECT count(tag2.id) from Thesis t2 left join t2.tags tag2 where t2.id=t.id and tag2.value in(?2)"
			+ ")")
	public List<Thesis> findByNameLikeAndTags(String thesisName,List<String> tagValues,Long matchLimit);
	
	@Query("SELECT distinct t from Thesis t where t.name like ?1 and t.subject.name = ?4 and ?3 <= ("
			+ "SELECT count(tag2.id) from Thesis t2 left join t2.tags tag2 where t2.id=t.id and tag2.value in(?2)"
			+ ")")
	public List<Thesis> findByNameLikeTagsAndSubject(String thesisName,List<String> tagValues,Long matchLimit,String subjectName);
	
	@Query("SELECT distinct t from Thesis t join t.subject.courses c where t.name like ?1 and c.name = ?4 and ?3 <= ("
			+ "SELECT count(tag2.id) from Thesis t2 left join t2.tags tag2 where t2.id=t.id and tag2.value in(?2)"
			+ ")")
	public List<Thesis> findByNameLikeTagsAndCourse(String thesisName,List<String> tagValues,Long matchLimit,String courseName);
		
	@Query("SELECT distinct t from Thesis t join t.subject.courses c join c.studies s where t.name like ?1 and s.name = ?4 and ?3 <= ("
			+ "SELECT count(tag2.id) from Thesis t2 left join t2.tags tag2 where t2.id=t.id and tag2.value in(?2)"
			+ ")")
	public List<Thesis> findByNameLikeTagsAndStudies(String thesisName,List<String> tagValues,Long matchLimit,String studiesName);
	*/
	
	
}
