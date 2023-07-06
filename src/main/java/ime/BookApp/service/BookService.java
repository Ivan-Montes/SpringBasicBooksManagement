package ime.BookApp.service;
import org.springframework.stereotype.Service;
import java.util.List;

import ime.BookApp.dto.BookDTO;
import ime.BookApp.entity.Book;

@Service
public interface BookService {

	public List<BookDTO> getAllBookDTO();
	
	public List<BookDTO> getAllBooksDTOWithQueryToTuple();	
	
	public Book findBookById(Long id);
	
	public Book updateBook(Book book);
	
	public Book saveBook(Book book);
	
	public void deleteBookById(Long id);
	 
}
