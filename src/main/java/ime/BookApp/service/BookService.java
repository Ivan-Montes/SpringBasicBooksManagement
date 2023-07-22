package ime.BookApp.service;
import org.springframework.stereotype.Service;
import java.util.List;

import ime.BookApp.dto.BookDTO;
import ime.BookApp.entity.Book;

@Service
public interface BookService {

	List<BookDTO> getAllBookDTO();
	
	List<BookDTO> getAllBooksDTOWithQueryToTuple();	
	
	Book findBookById(Long id);
	
	Book updateBook(Book book);
	
	Book saveBook(Book book);
	
	void deleteBookById(Long id);
	 
}
