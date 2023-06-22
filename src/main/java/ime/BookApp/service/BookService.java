package ime.BookApp.service;
import org.springframework.stereotype.Service;
import java.util.List;

import ime.BookApp.dto.BookDTO;

@Service
public interface BookService {

	public List<BookDTO> getAllBookDTO();	
	public List<BookDTO> getAllBooksDTOWithQueryToTuple();

}
