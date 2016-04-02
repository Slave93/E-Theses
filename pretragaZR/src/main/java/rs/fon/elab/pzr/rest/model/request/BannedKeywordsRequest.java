package rs.fon.elab.pzr.rest.model.request;

import java.util.List;

public class BannedKeywordsRequest {
	protected List<String> keywords;

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

}
