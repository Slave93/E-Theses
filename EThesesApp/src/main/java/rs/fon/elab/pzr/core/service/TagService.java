package rs.fon.elab.pzr.core.service;

import java.util.Set;

import rs.fon.elab.pzr.core.model.Tag;

public interface TagService {

	public abstract Tag getTag(Long id);
	
	public abstract Tag getTagByValue(String value);
	
	public abstract Set<Tag> getAllTags();
	
	public abstract Tag addTag(String value);
	
	public abstract void removeTag(Long tagId);
	
}
