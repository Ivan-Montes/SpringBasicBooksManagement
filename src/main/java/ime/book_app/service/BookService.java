package ime.book_app.service;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import ime.book_app.dto.BookDTO;
import ime.book_app.entity.Book;

import java.util.List;

@Service
public interface BookService {

	List<BookDTO> getAllBookDTO();
	
	List<BookDTO> getAllBooksDTOWithQueryToTuple();	
	
	Book findBookById(Long id);
	
	Book updateBook(Book book);
	
	Book saveBook(Book book);
	
	void deleteBookById(Long id);
	
	Page<Book>getAllPaged(int page, String sortField, String sortDir);
	 
}
