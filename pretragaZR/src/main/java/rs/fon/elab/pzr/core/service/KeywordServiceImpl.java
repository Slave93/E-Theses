package rs.fon.elab.pzr.core.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.transaction.annotation.Transactional;

import rs.fon.elab.pzr.core.model.Keyword;
import rs.fon.elab.pzr.core.repository.KeywordRepository;

public class KeywordServiceImpl implements KeywordService {

	KeywordRepository keywordRepository;
	ThesisService thesisService;

	@Override
	public Keyword getKeyword(Long id) {
		return keywordRepository.findOne(id);
	}

	@Override
	public Keyword getKeywordByValue(String value) {
		return keywordRepository.findByValue(value);
	}

	@Override
	public Set<Keyword> getAllKeywords() {
		return keywordRepository.findAll();
	}

	@Override
	@Transactional
	public Keyword addKeyword(String value) {
		value = value.toLowerCase();
		// remove white spaces and tabs
		value = value.replaceAll("\\s+", "");

		Keyword keyword = keywordRepository.findByValue(value);
		if (keyword != null) {
			return keyword;
		}
		keyword = new Keyword();
		keyword.setValue(value);
		return keywordRepository.save(keyword);
	}

	@Override
	@Transactional
	public void removeKeyword(Long id) {
		keywordRepository.delete(id);
	}

	public Map<String, Integer> extractWordsWithCount(String input) {
		Map<String, Integer> words = new HashMap<String, Integer>();
		Pattern p = Pattern.compile("[\\w']+");
		Matcher m = p.matcher(input);

		while (m.find()) {
			String nextWord = input.substring(m.start(), m.end());
			if (words.containsKey(nextWord)) {
				words.put(nextWord, words.get(nextWord) + 1);
			} else {
				words.put(input.substring(m.start(), m.end()), 1);
			}
		}
		return words;
	}	


	// GETTERS AND SETTERS
	public KeywordRepository getKeywordRepository() {
		return keywordRepository;
	}

	public void setKeywordRepository(KeywordRepository keywordRepository) {
		this.keywordRepository = keywordRepository;
	}

	public ThesisService getThesisService() {
		return thesisService;
	}

	public void setThesisService(ThesisService thesisService) {
		this.thesisService = thesisService;
	}
	
	

}
