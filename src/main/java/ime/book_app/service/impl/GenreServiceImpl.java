package ime.book_app.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ime.book_app.dto.GenreDTO;
import ime.book_app.entity.Genre;
import ime.book_app.repository.GenreRepository;
import ime.book_app.service.GenreService;

@Service
public class GenreServiceImpl implements GenreService {

	private final GenreRepository genreRepository;	
	
	public GenreServiceImpl(GenreRepository genreRepository) {
		this.genreRepository = genreRepository;
	}

	@Override
	public List<GenreDTO> getAllGenreDTO() {
		return genreRepository.getAllGenreDTO();
	}

	@Override
	public Genre findGenreById(Long id) {		
		return genreRepository.findById(id).orElse(new Genre());
	}

	@Override
	public Genre updateGenre(Genre genre) {		
		return genreRepository.save(genre);		
	}

	@Override
	public Genre saveGenre(Genre genre) {		
		return genreRepository.save(genre);
	}

	@Override
	public void deleteGenreById(Long id) {
		genreRepository.deleteById(id);		
	}
	
	

}
