package ime.BookApp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ime.BookApp.dto.AuthorDTO;

@Service
public interface AuthorService {

	public List<AuthorDTO> getAllAuthorDTO();
	
}
