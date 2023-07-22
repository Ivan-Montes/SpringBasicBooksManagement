package ime.BookApp.service;
import java.util.List;

import org.springframework.stereotype.Service;

import ime.BookApp.dto.BookshopDTO;
import ime.BookApp.entity.Bookshop;

@Service
public interface BookshopService {

	List<BookshopDTO>getAllBookshopDTO();
	
	Bookshop findBookshopById(Long id);
	
	Bookshop updateBookshop(Bookshop bookshop);
	
	Bookshop saveBookshop(Bookshop bookshop);
	
	void deleteBookshopById(Long id);
}
