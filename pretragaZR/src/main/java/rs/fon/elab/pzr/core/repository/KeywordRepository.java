package rs.fon.elab.pzr.core.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import rs.fon.elab.pzr.core.model.Keyword;

public interface KeywordRepository extends CrudRepository<Keyword, Long> {
	public Keyword findByValue(String value);

	public Set<Keyword> findAll();
	
	@Query("SELECT count(tk.keyword) from ThesisKeyword tk where tk.keyword.id=?1")
	public Long numberOfConnectedTheses(Long keywordId);
	
}
