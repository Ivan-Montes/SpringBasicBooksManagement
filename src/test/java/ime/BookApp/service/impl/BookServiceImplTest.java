package ime.BookApp.service.impl;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.mockito.junit.jupiter.MockitoExtension;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import ime.BookApp.dto.*;
import ime.BookApp.entity.Book;
import ime.BookApp.repository.AuthorRepository;
import ime.BookApp.repository.BookRepository;


import jakarta.persistence.Tuple;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

	@Mock
	private BookRepository bookRepository;
	
	@Mock
	private AuthorRepository authorRepository;
	
	@InjectMocks
	private BookServiceImpl bookService;
	
	@Test
	public void BookServiceImpl_getAllBookDTO_ReturnZeroOrMoreDTO() {
		
		List<BookDTO> bookDTOList = List.of(Mockito.mock(BookDTO.class));
		when(bookRepository.getAllBookDTO()).thenReturn(bookDTOList);
		
		List<AuthorDTO> authorDTOList = List.of(Mockito.mock(AuthorDTO.class));
		when(authorRepository.getAuthorDTOByBookIdWithConstructor(Mockito.any())).thenReturn(authorDTOList);
		
		List<BookDTO> list = bookService.getAllBookDTO();
		
		Assertions.assertThat(list).isNotNull();
		Assertions.assertThat(list.size()).isEqualTo(1);
		
	}
	
	@Test
	public void BookServiceImpl_getAllBooksDTOWithQueryToTuple_ReturnZeroOrMoreDTO() {
		
		List<Tuple> tupleList = List.of(Mockito.mock(Tuple.class));
		when(bookRepository.givemeListTuple()).thenReturn(tupleList);
		
		List<AuthorDTO> authorDTOList = List.of(Mockito.mock(AuthorDTO.class));
		when(authorRepository.getAuthorDTOByBookIdWithConstructor(Mockito.any())).thenReturn(authorDTOList);
		
		List<BookDTO> list = bookService.getAllBooksDTOWithQueryToTuple();
		
		Assertions.assertThat(list).isNotNull();
		Assertions.assertThat(list.size()).isEqualTo(1);
		
	}
	
	@Test
	public void BookServiceImpl_findBookById_ReturnBook() {
		Long bookId = 1L;
		Book book = new Book();
		book.setBookId(bookId);
		Optional<Book>opt = Optional.of(book);
		
		doReturn(opt).when(bookRepository).findById(bookId);
		Book bookFound = bookService.findBookById(bookId);
		
		Assertions.assertThat(bookFound).isNotNull();
		Assertions.assertThat(bookFound.getBookId()).isEqualTo(bookId);
		
	}

	@Test
	public void BookServiceImpl_updateBook_ReturnBook() {
		Long bookId = 1L;
		Book book = new Book();
		book.setBookId(bookId);
		
		doReturn(book).when(bookRepository).save(book);
		Book bookFound = bookService.updateBook(book);
		
		Assertions.assertThat(bookFound).isNotNull();
		Assertions.assertThat(bookFound.getBookId()).isEqualTo(bookId);
	}

	@Test
	public void BookServiceImpl_saveBook_ReturnBook() {
		Long bookId = 1L;
		Book book = new Book();
		book.setBookId(bookId);
		
		doReturn(book).when(bookRepository).save(book);
		Book bookFound = bookService.saveBook(book);
		
		Assertions.assertThat(bookFound).isNotNull();
		Assertions.assertThat(bookFound.getBookId()).isEqualTo(bookId);
	}

	@Test
	public void BookServiceImpl_deleteBookById_ReturnVoid() {
		Long bookId = 1L;
		
		bookService.deleteBookById(bookId);
		
		verify(bookRepository,times(1)).deleteById(bookId);
	}
	
	
}
