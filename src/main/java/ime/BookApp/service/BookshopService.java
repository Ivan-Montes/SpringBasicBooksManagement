package ime.BookApp.service;
import java.util.List;

import org.springframework.stereotype.Service;

import ime.BookApp.dto.BookshopDTO;

@Service
public interface BookshopService {

	public List<BookshopDTO>getAllBookshopDTO();
}
