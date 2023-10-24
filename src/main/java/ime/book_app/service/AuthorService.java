package ime.book_app.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import ime.book_app.dto.AuthorDTO;
import ime.book_app.entity.Author;

@Service
public interface AuthorService {

	List<AuthorDTO> getAllAuthorDTO();
	
	Author findAuthorById(Long id);
	
	Author saveAuthor(Author author);
	
	void deleteAuthorById(Long id);
	
	Set<Author> findAllById(Set<Long> ids);
	
	List<AuthorDTO>getAuthorDTOByBookIdWithConstructor(Long id);
}
