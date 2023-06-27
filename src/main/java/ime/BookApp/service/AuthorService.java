package ime.BookApp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ime.BookApp.dto.AuthorDTO;
import ime.BookApp.entity.Author;
import ime.BookApp.entity.Genre;

@Service
public interface AuthorService {

	public List<AuthorDTO> getAllAuthorDTO();
	
	public Author findAuthorById(Long id);
	
	public void updateAuthor(Author author);
	
	public Author saveAuthor(Author author);
	
	public void deleteAuthorById(Long id);
}
