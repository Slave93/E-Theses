package rs.fon.elab.pzr.core.service;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import rs.fon.elab.pzr.core.model.TFile;
import rs.fon.elab.pzr.core.model.Thesis;
import rs.fon.elab.pzr.core.model.ThesisComment;

public interface ThesisService {
	
	//TODO: advanced search methods
	
	public abstract Thesis getThesis(Long id);
	
	public abstract Thesis getThesisByUserId(Long userId);

	public abstract List<Thesis> getAllThesis();
	
	public abstract Page<Thesis> advancedSearch(Integer pageNumber,Integer pageSize,String thesisName,List<String> tagValues,Long matchLimit, String courseName, String studiesName, String sortField);

	public abstract Thesis addThesis(Thesis thesis);
	
	public abstract Thesis updateThesis(Thesis thesis);	
	
	public abstract void removeThesis(Long thesisId);
	
	public abstract TFile addFile(Long thesisId, MultipartFile file);
	
	public abstract File getThesisFile(Long thesisId);
	
	public abstract File getFileById(Long fileId);
	
	public abstract Set<TFile> getAllFileRecords();
	
	public abstract void removeFile(Long fileId);
	
	//chiled record services
	
	public abstract Set<ThesisComment> getAllComments(Long thesisId);	
	
	public abstract ThesisComment addComment(ThesisComment thesisComment);	
	
	public abstract void removeComment(Long commentId);
	
	
	
}
