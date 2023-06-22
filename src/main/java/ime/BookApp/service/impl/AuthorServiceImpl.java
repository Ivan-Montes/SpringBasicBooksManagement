package ime.BookApp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ime.BookApp.dto.AuthorDTO;
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

}
