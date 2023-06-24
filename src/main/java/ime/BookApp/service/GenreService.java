package ime.BookApp.service;
import org.springframework.stereotype.Service;
import java.util.List;
import ime.BookApp.dto.GenreDTO;
import ime.BookApp.entity.Genre;

@Service
public interface GenreService {

	public List<GenreDTO> getAllGenreDTO();
	
	public Genre findGenreById(Long id);
	
}
