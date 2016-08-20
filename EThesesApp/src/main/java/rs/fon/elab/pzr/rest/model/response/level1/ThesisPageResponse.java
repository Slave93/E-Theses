package rs.fon.elab.pzr.rest.model.response.level1;

import java.util.ArrayList;
import java.util.List;

public class ThesisPageResponse {
	Integer number;
	Integer numberOfElements;
	Integer size;
	Long totalElements;
	Integer totalPages;
	List<ThesisResponseLevel1> content = new ArrayList<ThesisResponseLevel1>();

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getNumberOfElements() {
		return numberOfElements;
	}

	public void setNumberOfElements(Integer numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public List<ThesisResponseLevel1> getContent() {
		return content;
	}

	public void setContent(List<ThesisResponseLevel1> content) {
		this.content = content;
	}
}
