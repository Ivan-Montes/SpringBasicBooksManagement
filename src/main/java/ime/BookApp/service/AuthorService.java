package ime.BookApp.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import ime.BookApp.dto.AuthorDTO;
import ime.BookApp.entity.Author;

@Service
public interface AuthorService {

	public List<AuthorDTO> getAllAuthorDTO();
	
	public Author findAuthorById(Long id);
	
	public Author updateAuthor(Author author);
	
	public Author saveAuthor(Author author);
	
	public void deleteAuthorById(Long id);
	
	public Set<Author> findAllById(Set<Long> ids);
	
	public List<AuthorDTO>getAuthorDTOByBookIdWithConstructor(Long id);
}
