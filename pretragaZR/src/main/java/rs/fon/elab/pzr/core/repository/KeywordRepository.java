package rs.fon.elab.pzr.core.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import rs.fon.elab.pzr.core.model.Keyword;

public interface KeywordRepository extends CrudRepository<Keyword, Long> {
	public Keyword findByValue(String value);

	public Set<Keyword> findAll();
	
	@Query("SELECT count(tk.keyword) from ThesisKeyword tk where tk.keyword.id=?1")
	public Long numberOfConnectedTheses(Long keywordId);
	
	@Modifying  
	@Transactional
	@Query("DELETE from Keyword k where k.banned is false and k.id not in(SELECT distinct tk.keyword.id from ThesisKeyword tk)")
	public Integer deleteUnconnectedUnBannedKeywords();
	
}
