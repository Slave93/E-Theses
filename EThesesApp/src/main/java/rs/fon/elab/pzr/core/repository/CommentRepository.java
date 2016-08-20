package rs.fon.elab.pzr.core.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import rs.fon.elab.pzr.core.model.Thesis;
import rs.fon.elab.pzr.core.model.ThesisComment;

public interface CommentRepository extends CrudRepository<ThesisComment, Long>{
	
	
}
