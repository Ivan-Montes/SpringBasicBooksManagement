package ime.BookApp.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import ime.BookApp.dto.AuthorDTO;
import ime.BookApp.entity.Author;
import ime.BookApp.repository.AuthorRepository;
import ime.BookApp.service.AuthorService;

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
	public void updateAuthor(Author author) {
		authorRepository.save(author);		
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
		return Set.copyOf(authorRepository.findAllById(ids));
	}


	@Override
	public List<AuthorDTO> getAuthorDTOByBookIdWithConstructor(Long id) {
		return authorRepository.getAuthorDTOByBookIdWithConstructor(id);
	}
	
}
