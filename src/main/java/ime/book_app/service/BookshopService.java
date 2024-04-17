package ime.book_app.service;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import ime.book_app.dto.BookshopDTO;
import ime.book_app.entity.Bookshop;

@Service
public interface BookshopService {

	List<BookshopDTO>getAllBookshopDTO();
	
	Bookshop findBookshopById(Long id);
	
	Bookshop updateBookshop(Bookshop bookshop);
	
	Bookshop saveBookshop(Bookshop bookshop);
	
	void deleteBookshopById(Long id);
	
	Page<Bookshop>getAllPaged(int page, String sortField, String sortDir);
}
