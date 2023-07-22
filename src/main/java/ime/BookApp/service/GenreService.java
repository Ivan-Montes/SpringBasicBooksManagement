package ime.BookApp.service;
import org.springframework.stereotype.Service;
import java.util.List;
import ime.BookApp.dto.GenreDTO;
import ime.BookApp.entity.Genre;

@Service
public interface GenreService {

	List<GenreDTO> getAllGenreDTO();
	
	Genre findGenreById(Long id);
	
	Genre updateGenre(Genre genre);
	
	Genre saveGenre(Genre genre);
	
	void deleteGenreById(Long id);
}
