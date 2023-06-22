package ime.BookApp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ime.BookApp.dto.GenreDTO;
import ime.BookApp.repository.GenreRepository;
import ime.BookApp.service.GenreService;

@Service
public class GenreServiceImpl implements GenreService {

	@Autowired
	private GenreRepository genreRepository;
	
	@Override
	public List<GenreDTO> getAllGenreDTO() {
		return genreRepository.getAllGenreDTO();
	}

}
