package rs.fon.elab.pzr.core.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import rs.fon.elab.pzr.core.exception.InvalidArgumentException;
import rs.fon.elab.pzr.core.exception.InvalidTicketException;
import rs.fon.elab.pzr.core.exception.PzrRuntimeException;
import rs.fon.elab.pzr.core.model.TFile;
import rs.fon.elab.pzr.core.model.Thesis;
import rs.fon.elab.pzr.core.model.ThesisComment;
import rs.fon.elab.pzr.core.model.User;
import rs.fon.elab.pzr.core.repository.CommentRepository;
import rs.fon.elab.pzr.core.repository.FileRepository;
import rs.fon.elab.pzr.core.repository.TagRepository;
import rs.fon.elab.pzr.core.repository.ThesisRepository;
import rs.fon.elab.pzr.core.repository.UserRepository;

public class ThesisServiceImpl implements ThesisService {

	Logger logger = Logger.getLogger(ThesisServiceImpl.class);
	public static final int DEFAULT_PAGE_SIZE = 10;

	private String thesisFilesFolder;

	ThesisRepository thesisRepository;
	TagRepository tagRepository;
	CommentRepository commentRepository;
	UserRepository userRepository;
	UserService userService;
	FileRepository fileRepository;

	@Override
	public Thesis getThesis(Long id) {
		return thesisRepository.findOne(id);
	}

	@Override
	public Thesis getThesisByUserId(Long userId) {
		User user = userRepository.findOne(userId);
		if (user == null) {
			throw new InvalidArgumentException("Korisnik sa id-em " + userId
					+ " ne postoji u bazi!.");
		}
		return thesisRepository.findByUser(user);
	}

	@Override
	public List<Thesis> getAllThesis() {
		return thesisRepository.findAll();
	}

	@Override
	public Page<Thesis> advancedSearch(Integer pageNumber, Integer pageSize,
			String thesisName, List<String> tagValues, Long matchLimit,
			String courseName, String studiesName,
			String sortField) {
		if (pageSize == null) {
			if (pageNumber == null) {
				pageSize = thesisRepository.findAll().size();
				if(pageSize<1){
					pageSize=1;
				}
			} else {
				pageSize = DEFAULT_PAGE_SIZE;
			}
		}		
		if (pageNumber == null) {
			pageNumber = 0;
		} else {
			pageNumber = pageNumber - 1;
		}
		if (thesisName == null) {
			thesisName = "%";
		} else {
			thesisName = "%" + thesisName + "%";
		}
		if (tagValues == null || tagValues.isEmpty()) {
			matchLimit = 0l;
		} else {
			if (matchLimit == null) {
				matchLimit = (long) tagValues.size();
			}
		}
		PageRequest pageRequest = null;
		if (sortField == null || sortField.equals("")) {
			pageRequest = new PageRequest(pageNumber, pageSize, new Sort(
					Sort.Direction.DESC, "datePosted"));
		} else {
			pageRequest = new PageRequest(pageNumber, pageSize, new Sort(
					Sort.Direction.DESC, sortField).and(new Sort(
					Sort.Direction.DESC, "datePosted")));
		}		

		try {
			if (courseName != null && courseName != "") {
				return thesisRepository.findByNameLikeTagsAndCoursePagable(
						pageRequest, thesisName, tagValues, matchLimit,
						courseName);
			} else if (studiesName != null && studiesName != "") {
				return thesisRepository.findByNameLikeTagsAndStudiesPagable(
						pageRequest, thesisName, tagValues, matchLimit,
						studiesName);
			} else {
				return thesisRepository.findByNameLikeAndTagsPagable(
						pageRequest, thesisName, tagValues, matchLimit);
			}
		} catch (Exception e) {
			logger.error(e);
			throw new InvalidArgumentException(
					"Prosledjeni parametri nisu ispravni.");
		}
	}

	@Override
	@Transactional
	public Thesis addThesis(Thesis thesis) {
		if (thesisRepository.findByName(thesis.getName()) != null) {
			throw new InvalidArgumentException("Rad " + thesis.getName()
					+ "već postoji u bazi!");
		}
		if (thesis.getUser() != null) {
			Thesis existingUserThesis = getThesisByUserId(thesis.getUser()
					.getId());
			if (existingUserThesis != null) {
				throw new InvalidArgumentException("Korisnik "
						+ thesis.getUser().getEmail() + " već ima rad: "
						+ existingUserThesis.getName());
			}
		}
		return thesisRepository.save(thesis);
	}

	@Transactional
	@Override
	public Thesis updateThesis(Thesis thesis) {
		Thesis oldThesis = thesisRepository.findOne(thesis.getId());
		if (oldThesis == null) {
			throw new InvalidArgumentException("Rad sa id-em " + thesis.getId()
					+ " ne postoji u bazi!");
		}
		if (!thesis.getName().equals(oldThesis.getName())) {
			if (thesisRepository.findByName(thesis.getName()) != null) {
				throw new InvalidArgumentException("Rad " + thesis.getName()
						+ "već postoji u bazi!");
			}
		}
		if (thesis.getUser() != null) {
			Long newUserId = thesis.getUser().getId();
			Long oldUserId = null;
			if (oldThesis.getUser() != null) {
				oldUserId = oldThesis.getUser().getId();
			}
			if (newUserId != oldUserId) {
				Thesis existingUserThesis = getThesisByUserId(newUserId);
				if (existingUserThesis != null) {
					throw new InvalidArgumentException("Korisnik "
							+ thesis.getUser().getEmail() + " već ima rad: "
							+ existingUserThesis.getName());
				}
			}
		}
		return thesisRepository.save(thesis);
	}

	@Transactional
	@Override
	public void removeThesis(Long thesisId) {
		Thesis thesis = thesisRepository.findOne(thesisId);
		if (thesis == null) {
			throw new InvalidArgumentException("Rad sa id-em " + thesisId
					+ " ne postoji u bazi!");
		}
		thesisRepository.delete(thesis);
	}

	@Override
	public TFile addFile(Long thesisId, MultipartFile file) {
		if (file.isEmpty()) {
			throw new InvalidArgumentException("Fajl je prazan!");
		}
		Thesis thesis = getThesis(thesisId);
		if (thesis == null) {
			throw new InvalidArgumentException("Rad sa id-em " + thesisId
					+ " ne postoji u bazi!");
		}
		TFile existingTFile = thesis.getFile();
		if (existingTFile != null) {
			throw new InvalidArgumentException("Rad " + thesis.getName()
					+ " već ima upload-ovan fajl "
					+ existingTFile.getFileName());
		}
		if (fileRepository.findByFileName(file.getOriginalFilename()) != null) {
			throw new InvalidArgumentException("Fajl sa imenom "
					+ file.getOriginalFilename()
					+ " već postoji. Molimo izaberite drugo ime fajla.");
		}
		TFile tFile = new TFile();
		tFile.setThesisName(thesis.getName());
		tFile.setFileName(file.getOriginalFilename());
		thesis.setFile(tFile);
		logger.debug(tFile.getFileName());
		try {
			byte[] bytes = file.getBytes();
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(thesisFilesFolder
							+ tFile.getFileName()));
			stream.write(bytes);
			stream.close();
		} catch (Exception e) {
			throw new PzrRuntimeException("Fajl nije uspešno postavljen: "
					+ e.getMessage());
		}
		fileRepository.save(tFile);
		thesisRepository.save(thesis);
		return tFile;

	}

	@Override
	public Set<TFile> getAllFileRecords() {
		return fileRepository.findAll();
	}

	@Override
	public void removeFile(Long fileId) {
		TFile existingFile = fileRepository.findOne(fileId);
		if (existingFile == null) {
			throw new InvalidArgumentException("Fajl sa id-em " + fileId
					+ " ne postoji u bazi!");
		}
		File file = new File(thesisFilesFolder + existingFile.getFileName());
		if (!file.exists()) {
			fileRepository.delete(existingFile);
			throw new PzrRuntimeException("Fajl " + existingFile.getFileName()
					+ " se ne nalazi na lokaciji " + thesisFilesFolder
					+ existingFile.getFileName() + "!");
		}
		if (!file.delete()) {
			throw new PzrRuntimeException("Fajl " + existingFile.getFileName()
					+ " nije uspešno izbrisan!");
		}
		fileRepository.delete(existingFile);

	}

	@Override
	public File getThesisFile(Long thesisId) {
		Thesis thesis = getThesis(thesisId);
		if (thesis == null) {
			throw new InvalidArgumentException("Rad sa id-em " + thesisId
					+ " ne postoji u bazi!");
		}
		TFile existingTFile = thesis.getFile();
		if (existingTFile == null) {
			throw new InvalidArgumentException("Rad " + thesis.getName()
					+ " nema upload-ovan fajl ");
		}
		File file = new File(thesisFilesFolder + existingTFile.getFileName());

		if (!file.exists()) {
			logger.error("Thesis " + thesis.getName() + " has attached file "
					+ existingTFile.getFileName()
					+ " but file doesnot exist on disc at location: "
					+ thesisFilesFolder + existingTFile.getFileName());
			throw new PzrRuntimeException("Fajl " + existingTFile.getFileName()
					+ " se ne nalazi na lokaciji " + thesisFilesFolder
					+ existingTFile.getFileName() + "!");
		}
		existingTFile.setDownloadCount(existingTFile.getDownloadCount() + 1);
		fileRepository.save(existingTFile);
		return file;
	}

	@Override
	public File getFileById(Long fileId) {
		TFile tFile = fileRepository.findOne(fileId);
		if (tFile == null) {
			throw new PzrRuntimeException("Fajl sa id-em " + fileId
					+ " ne postoji u bazi.");
		}
		File file = new File(thesisFilesFolder + tFile.getFileName());
		if (!file.exists()) {
			logger.error("There is a file record in database"
					+ " but file doesnot exist on disc at location: "
					+ thesisFilesFolder + tFile.getFileName());
			throw new PzrRuntimeException("Fajl " + tFile.getFileName()
					+ " se ne nalazi na lokaciji " + thesisFilesFolder
					+ tFile.getFileName() + "!");
		}
		tFile.setDownloadCount(tFile.getDownloadCount() + 1);
		fileRepository.save(tFile);
		return file;
	}

	/*
	 * @Override public Thesis setTags(Long thesisId, List<String> tags) {
	 * Thesis thesis = thesisRepository.findOne(thesisId); if(thesis==null){
	 * throw new InvalidArgumentException("Rad sa id-em "
	 * +thesisId+" ne postoji u bazi!"); } Set<Tag> tagList = new
	 * HashSet<Tag>(); for(String tagValue: tags){ tagValue =
	 * tagValue.toLowerCase(); Tag tag = tagRepository.findByValue(tagValue);
	 * if(tag==null){ Tag tagNew = new Tag(); tagNew.setValue(tagValue);
	 * tagList.add(tagRepository.save(tagNew)); tagRepository.save(tagNew);
	 * }else{ tagList.add(tag); } } thesis.setTags(tagList); return
	 * thesisRepository.save(thesis); }
	 */

	@Override
	public Set<ThesisComment> getAllComments(Long thesisId) {
		Thesis thesis = thesisRepository.findOne(thesisId);
		if (thesis == null) {
			throw new InvalidArgumentException("Rad sa id-em " + thesisId
					+ " ne postoji u bazi!");
		}
		return thesis.getComments();
	}

	@Transactional
	@Override
	public ThesisComment addComment(ThesisComment thesisComment) {
		try {
			org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			String email = user.getUsername();
			User loggedInUser = userService.getUser(email);
			thesisComment.setAuthor(loggedInUser);
		} catch (Exception e) {
			throw new InvalidTicketException(
					"Morate biti ulogovani kako bi ostavili komentar!");
		}
		return commentRepository.save(thesisComment);
	}

	@Transactional
	@Override
	public void removeComment(Long commentId) {
		if (commentRepository.findOne(commentId) == null) {
			throw new InvalidArgumentException("Komentar sa id-em " + commentId
					+ " ne postoji u bazi!");
		}
		try {
			org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			String email = user.getUsername();
			User loggedInUser = userService.getUser(email);
			ThesisComment thesisComment = commentRepository.findOne(commentId);
			if (!loggedInUser.isAdmin()
					&& thesisComment.getAuthor().getId() != loggedInUser
							.getId()) {
				throw new Exception();
			}
		} catch (Exception e) {
			throw new InvalidArgumentException(
					"Morate biti ulogovani da bi ste mogli obrisati komentar!");
		}
		commentRepository.delete(commentRepository.findOne(commentId));
	}

	// GETTERS AND SETTERS
	public ThesisRepository getThesisRepository() {
		return thesisRepository;
	}

	public void setThesisRepository(ThesisRepository thesisRepository) {
		this.thesisRepository = thesisRepository;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public TagRepository getTagRepository() {
		return tagRepository;
	}

	public void setTagRepository(TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}

	public CommentRepository getCommentRepository() {
		return commentRepository;
	}

	public void setCommentRepository(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public FileRepository getFileRepository() {
		return fileRepository;
	}

	public void setFileRepository(FileRepository fileRepository) {
		this.fileRepository = fileRepository;
	}

	public String getThesisFilesFolder() {
		return thesisFilesFolder;
	}

	public void setThesisFilesFolder(String thesisFilesFolder) {
		this.thesisFilesFolder = thesisFilesFolder;
	}

}
