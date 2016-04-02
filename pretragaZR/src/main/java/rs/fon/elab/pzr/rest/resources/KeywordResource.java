package rs.fon.elab.pzr.rest.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rs.fon.elab.pzr.core.exception.InvalidArgumentException;
import rs.fon.elab.pzr.core.exception.PzrRuntimeException;
import rs.fon.elab.pzr.core.model.Keyword;
import rs.fon.elab.pzr.core.model.Tag;
import rs.fon.elab.pzr.core.repository.KeywordRepository;
import rs.fon.elab.pzr.core.service.KeywordService;
import rs.fon.elab.pzr.core.service.TagService;
import rs.fon.elab.pzr.rest.model.request.BannedKeywordsRequest;
import rs.fon.elab.pzr.rest.model.request.KeywordRequest;

@RestController
@RequestMapping(value = "/keywords")
public class KeywordResource {

	private KeywordService keywordService;

	// READ
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Set<Keyword> getKeywords() {
		return keywordService.getAllKeywords();
	}

	//CREATE
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody List<Keyword> addBannedKeywords(@RequestBody BannedKeywordsRequest bannedKeywords) {
		List<Keyword> keywords = new ArrayList<Keyword>();
		for(String keywordValue : bannedKeywords.getKeywords()){
			keywords.add(keywordService.addBannedKeyword(keywordValue));
		}
		return keywords;
	}

	//UPDATE
	@RequestMapping(method = RequestMethod.PUT, value = "/{keywordID}")
	public @ResponseBody Keyword updateKeywordBannedStatus(@PathVariable("keywordID") Long keywordID, @RequestBody KeywordRequest keywordRequest) {
		Keyword existingKeyword = keywordService.getKeyword(keywordID);
		if(existingKeyword==null){
			throw new InvalidArgumentException("Ključna reč sa id-em: "+keywordID+" ne postoji u bazi!");
		}
		existingKeyword.setBanned(keywordRequest.getBanned());
		return keywordService.updateKeyword(existingKeyword);
	}

	// DELETE
	@RequestMapping(method = RequestMethod.DELETE, value = "/{keywordID}")
	public void deleteKeyword(@PathVariable("keywordID") Long keywordID) {
		keywordService.removeKeyword(keywordID);
	}

	public KeywordService getKeywordService() {
		return keywordService;
	}

	public void setKeywordService(KeywordService keywordService) {
		this.keywordService = keywordService;
	}
}
