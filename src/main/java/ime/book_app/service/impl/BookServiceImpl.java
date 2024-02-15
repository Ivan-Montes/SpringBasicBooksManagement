package ime.book_app.service.impl;

import java.util.HashSet;
import java.util.List;
import org.springframework.stereotype.Service;

import ime.book_app.dto.BookDTO;
import ime.book_app.entity.Book;
import ime.book_app.repository.AuthorRepository;
import ime.book_app.repository.BookRepository;
import ime.book_app.service.BookService;
import jakarta.persistence.Tuple;

@Service
public class BookServiceImpl implements BookService{

	private final BookRepository bookRepository;
	
	private final AuthorRepository authorRepository;	
	
	public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
	}

	@Override
	public List<BookDTO> getAllBookDTO() {
		List<BookDTO> listDTO = bookRepository.getAllBookDTO();
		listDTO.forEach(b->b.setAuthors(new HashSet<>(authorRepository.getAuthorDTOByBookIdWithConstructor(b.getBookId()))));
		return listDTO;
	}	
	
	@Override
	public List<BookDTO> getAllBooksDTOWithQueryToTuple() {
		List<Tuple> tupleList = bookRepository.givemeListTuple();
		return tupleList.stream()
				.map( t -> 
					BookDTO.builder()
					.bookId((Long)t.get(0))
					.isbn((String) t.get(1))
					.title((String) t.get(2))
					.publisher((String) t.get(3))
					.genre((String) t.get(4))
					.authors(new HashSet<>(authorRepository.getAuthorDTOByBookIdWithConstructor((Long)t.get(0))))
					.build()
				)
				.toList();
	}

	@Override
	public Book findBookById(Long id) {
		return bookRepository.findById(id).orElse(new Book());
	}

	@Override
	public void deleteBookById(Long id) {
		bookRepository.deleteById(id);		
	}

	@Override
	public Book updateBook(Book book) {
		return bookRepository.save(book);		
	}

	@Override
	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}
	
	

	
}