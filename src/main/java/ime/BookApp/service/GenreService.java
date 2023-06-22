package ime.BookApp.service;
import org.springframework.stereotype.Service;
import java.util.List;
import ime.BookApp.dto.GenreDTO;

@Service
public interface GenreService {

	public List<GenreDTO> getAllGenreDTO();
}
