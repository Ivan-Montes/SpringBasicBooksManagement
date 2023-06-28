package ime.BookApp.service;
import java.util.List;

import org.springframework.stereotype.Service;

import ime.BookApp.dto.BookshopDTO;
import ime.BookApp.entity.Bookshop;

@Service
public interface BookshopService {

	public List<BookshopDTO>getAllBookshopDTO();
	
	public Bookshop findBookshopById(Long id);
	
	public void updateBookshop(Bookshop bookshop);
	
	public Bookshop saveBookshop(Bookshop bookshop);
	
	public void deleteBookshopById(Long id);
}
