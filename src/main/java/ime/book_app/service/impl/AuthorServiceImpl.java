package ime.book_app.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ime.book_app.dto.AuthorDTO;
import ime.book_app.entity.Author;
import ime.book_app.repository.AuthorRepository;
import ime.book_app.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService{

	
	private AuthorRepository authorRepository;
	
	 
	
	public AuthorServiceImpl(AuthorRepository authorRepository) {
		super();
		this.authorRepository = authorRepository;
	}



	@Override
	public List<AuthorDTO> getAllAuthorDTO() {
		
		return authorRepository.getAllAuthorsDTO();
	}



	@Override
	public Author findAuthorById(Long id) {
		return authorRepository.findById(id).orElse(new Author());
	}



	@Override
	public Author updateAuthor(Author author) {
		return authorRepository.save(author);		
	}



	@Override
	public Author saveAuthor(Author author) {
		return authorRepository.save(author);		
	}



	@Override
	public void deleteAuthorById(Long id) {
		authorRepository.deleteById(id);
	}


	@Override
	public Set<Author> findAllById(Set<Long> ids) {		
		return authorRepository.findAllById(ids).stream().collect(Collectors.toSet());
	}


	@Override
	public List<AuthorDTO> getAuthorDTOByBookIdWithConstructor(Long id) {
		return authorRepository.getAuthorDTOByBookIdWithConstructor(id);
	}
	
}
