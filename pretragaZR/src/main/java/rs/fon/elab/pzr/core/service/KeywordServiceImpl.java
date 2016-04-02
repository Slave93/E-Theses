package rs.fon.elab.pzr.core.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import rs.fon.elab.pzr.core.exception.InvalidArgumentException;
import rs.fon.elab.pzr.core.model.Keyword;
import rs.fon.elab.pzr.core.repository.KeywordRepository;

public class KeywordServiceImpl implements KeywordService {

	Logger logger = Logger.getLogger(KeywordServiceImpl.class);

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
	public Keyword addKeyword(Keyword keyword) {
		keyword.setValue(keyword.getValue().toLowerCase());
		// remove white spaces and tabs
		keyword.setValue(keyword.getValue().replaceAll("\\s+", ""));

		Keyword existingKeyword = keywordRepository.findByValue(keyword
				.getValue());
		if (existingKeyword != null) {
			return existingKeyword;
		}
		return keywordRepository.save(keyword);
	}

	@Override
	@Transactional
	public Keyword updateKeyword(Keyword keyword) {
		Keyword existingKeyword = keywordRepository.findOne(keyword.getId());
		if (existingKeyword == null) {
			throw new InvalidArgumentException("Ključna reč sa id-em "
					+ keyword.getId() + " ne postoji u bazi!");
		}
		return keywordRepository.save(keyword);
	}

	@Transactional
	@Override
	public Keyword addBannedKeyword(String value) {
		value = value.toLowerCase();
		value = value.replaceAll("\\s+", "");

		Keyword keyword = keywordRepository.findByValue(value);
		if (keyword != null) {
			keyword.setBanned(true);
			return keywordRepository.save(keyword);
		}
		keyword = new Keyword();
		keyword.setValue(value);
		keyword.setBanned(true);
		return keywordRepository.save(keyword);
	}

	@Override
	@Transactional
	public void removeKeyword(Long id) {
		Keyword keyword = keywordRepository.findOne(id);
		if (keyword == null) {
			throw new InvalidArgumentException("Ključna reč sa id-em " + id
					+ " ne postoji u bazi!");
		}
		if (keywordRepository.numberOfConnectedTheses(id) > 0) {
			throw new InvalidArgumentException(
					"Ne može se obrisati ključna reč koja je povezana sa postojećim radovima");
		}
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

	@Override
	@Transactional
	public Integer deleteUnConnectedUnBannedKeywords() {
		logger.info("Deleting unconnected and unbanned keywords.");
		Integer numDeleted = 0;
		try {
			numDeleted = keywordRepository.deleteUnconnectedUnBannedKeywords();
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		logger.info("Deleted " + numDeleted
				+ " unconnected and unbanned keywords.");
		return numDeleted;
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
