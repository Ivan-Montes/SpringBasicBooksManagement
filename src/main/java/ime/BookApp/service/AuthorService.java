package ime.BookApp.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import ime.BookApp.dto.AuthorDTO;
import ime.BookApp.entity.Author;

@Service
public interface AuthorService {

	List<AuthorDTO> getAllAuthorDTO();
	
	Author findAuthorById(Long id);
	
	Author updateAuthor(Author author);
	
	Author saveAuthor(Author author);
	
	void deleteAuthorById(Long id);
	
	Set<Author> findAllById(Set<Long> ids);
	
	List<AuthorDTO>getAuthorDTOByBookIdWithConstructor(Long id);
}
