package rs.fon.elab.pzr.core.service;

import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import rs.fon.elab.pzr.core.exception.InvalidArgumentException;
import rs.fon.elab.pzr.core.model.Keyword;
import rs.fon.elab.pzr.core.model.Tag;
import rs.fon.elab.pzr.core.repository.TagRepository;

public class TagServiceImpl implements TagService {

	TagRepository tagRepository;
	
	@Override
	public Tag getTag(Long id) {
		return tagRepository.findOne(id);
	}

	@Override
	public Tag getTagByValue(String value) {
		return tagRepository.findByValue(value);
	}

	@Override
	public Set<Tag> getAllTags() {
		return tagRepository.findAll();
	}

	@Transactional
	@Override
	public Tag addTag(String value) {
		value = value.toLowerCase();
		//remove white spaces and tabs
		value = value.replaceAll("\\s+","");
		Tag tag = tagRepository.findByValue(value);
		if(tag!=null){
			return tag;
		}
		tag = new Tag();
		tag.setValue(value);
		return tagRepository.save(tag);
	}

	@Transactional
	@Override
	public void removeTag(Long id) {
		Tag tag = tagRepository.findOne(id);
		if (tag == null) {
			throw new InvalidArgumentException("Tag sa id-em " + id
					+ " ne postoji u bazi!");
		}
		tagRepository.delete(id);
	}

	public TagRepository getTagRepository() {
		return tagRepository;
	}

	public void setTagRepository(TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}	

}
