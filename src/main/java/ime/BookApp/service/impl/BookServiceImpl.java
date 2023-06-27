package ime.BookApp.service.impl;

import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ime.BookApp.dto.BookDTO;
import ime.BookApp.entity.Book;
import ime.BookApp.service.BookService;
import jakarta.persistence.Tuple;
import ime.BookApp.repository.BookRepository;
import ime.BookApp.repository.AuthorRepository;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;	
	
	@Override
	public List<BookDTO> getAllBookDTO() {
		List<BookDTO> listDTO = bookRepository.getAllBookDTO();
		listDTO.forEach(b->b.setAuthors(new HashSet<>(authorRepository.getAuthorDTOByBookIdWithConstructor(b.getBookId()))));
		return listDTO;
	}	
	
	@Override
	public List<BookDTO> getAllBooksDTOWithQueryToTuple() {
		List<Tuple> TupleList = bookRepository.givemeListTuple();
		List<BookDTO> BookDTOList = TupleList.stream()
				.map( (t) -> {
					return BookDTO.builder()
					.bookId((Long)t.get(0))
					.isbn((String) t.get(1))
					.title((String) t.get(2))
					.publisher((String) t.get(3))
					.genre((String) t.get(4))
					.authors(new HashSet<>(authorRepository.getAuthorDTOByBookIdWithConstructor((Long)t.get(0))))
					.build();
				})
				.toList();		 
		
		return BookDTOList;
	}

	@Override
	public Book findBookById(Long id) {
		return bookRepository.findById(id).orElse(new Book());
	}

	@Override
	public void deleteBookById(Long id) {
		bookRepository.deleteById(id);		
	}
	
	

	
}