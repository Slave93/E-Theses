package rs.fon.elab.pzr.core.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rs.fon.elab.pzr.core.model.Keyword;

public interface KeywordService {

	public abstract Keyword getKeyword(Long id);

	public abstract Keyword getKeywordByValue(String value);

	public abstract Set<Keyword> getAllKeywords();

	public abstract Keyword addKeyword(String value);

	public abstract void removeKeyword(Long id);

	public Map<String, Integer> extractWordsWithCount(String text);

}
