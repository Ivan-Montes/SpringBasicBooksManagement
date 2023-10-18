package ime.book_app.service;
import org.springframework.stereotype.Service;

import ime.book_app.dto.GenreDTO;
import ime.book_app.entity.Genre;

import java.util.List;

@Service
public interface GenreService {

	List<GenreDTO> getAllGenreDTO();
	
	Genre findGenreById(Long id);
	
	Genre updateGenre(Genre genre);
	
	Genre saveGenre(Genre genre);
	
	void deleteGenreById(Long id);
}
