package ime.book_app.service.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import ime.book_app.dto.*;
import ime.book_app.entity.Book;
import ime.book_app.repository.AuthorRepository;
import ime.book_app.repository.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

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
	void BookServiceImpl_getAllBookDTO_ReturnZeroOrMoreDTO() {
		
		List<BookDTO> bookDTOList = List.of(Mockito.mock(BookDTO.class));
		when(bookRepository.getAllBookDTO()).thenReturn(bookDTOList);
		
		List<AuthorDTO> authorDTOList = List.of(Mockito.mock(AuthorDTO.class));
		when(authorRepository.getAuthorDTOByBookIdWithConstructor(Mockito.any())).thenReturn(authorDTOList);
		
		List<BookDTO> list = bookService.getAllBookDTO();
		
		assertAll(
				()->Assertions.assertThat(list).isNotNull(),
				()->Assertions.assertThat(list).hasSize(1)
				);
	}
	
	@Test
	void BookServiceImpl_getAllBooksDTOWithQueryToTuple_ReturnZeroOrMoreDTO() {
		
		List<Tuple> tupleList = List.of(Mockito.mock(Tuple.class));
		when(bookRepository.givemeListTuple()).thenReturn(tupleList);
		
		List<AuthorDTO> authorDTOList = List.of(Mockito.mock(AuthorDTO.class));
		when(authorRepository.getAuthorDTOByBookIdWithConstructor(Mockito.any())).thenReturn(authorDTOList);
		
		List<BookDTO> list = bookService.getAllBooksDTOWithQueryToTuple();
		
		assertAll(
				()->Assertions.assertThat(list).isNotNull(),
				()->Assertions.assertThat(list).hasSize(1)
				);
	}
	
	@Test
	void BookServiceImpl_findBookById_ReturnBook() {
		Long bookId = 1L;
		Book book = new Book();
		book.setBookId(bookId);
		Optional<Book>opt = Optional.of(book);
		
		doReturn(opt).when(bookRepository).findById(bookId);
		Book bookFound = bookService.findBookById(bookId);
		
		assertAll(
				()->Assertions.assertThat(bookFound).isNotNull(),
				()->Assertions.assertThat(bookFound.getBookId()).isEqualTo(bookId)
				);
	}

	@Test
	void BookServiceImpl_updateBook_ReturnBook() {
		Long bookId = 1L;
		Book book = new Book();
		book.setBookId(bookId);
		
		doReturn(book).when(bookRepository).save(book);
		Book bookFound = bookService.updateBook(book);
		
		assertAll(
				()->Assertions.assertThat(bookFound).isNotNull(),
				()->Assertions.assertThat(bookFound.getBookId()).isEqualTo(bookId)
				);
	}

	@Test
	void BookServiceImpl_saveBook_ReturnBook() {
		Long bookId = 1L;
		Book book = new Book();
		book.setBookId(bookId);
		
		doReturn(book).when(bookRepository).save(book);
		Book bookFound = bookService.saveBook(book);
		
		assertAll(
				()->Assertions.assertThat(bookFound).isNotNull(),
				()->Assertions.assertThat(bookFound.getBookId()).isEqualTo(bookId)
				);
	}

	@Test
	void BookServiceImpl_deleteBookById_ReturnVoid() {
		Long bookId = 1L;
		
		bookService.deleteBookById(bookId);
		
		verify(bookRepository,times(1)).deleteById(bookId);
	}

	@Test
	void BookServiceImpl_getAllPaged_ReturnPageBookAsc() {
		
		Page<Book>page = Page.empty();
		when(bookRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(page);
		
		Page<Book>pageGenreResult = bookService.getAllPaged(1, "bookId", "asc");
		
		assertAll(
				()->Assertions.assertThat(pageGenreResult).isNotNull(),
				()->Assertions.assertThat(pageGenreResult.isEmpty())
				);		
	}
	
	@Test
	void BookServiceImpl_getAllPaged_ReturnPageBookDesc() {
		
		Page<Book>page = Page.empty();
		when(bookRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(page);
		
		Page<Book>pageGenreResult = bookService.getAllPaged(1, "bookId", "d");
		
		assertAll(
				()->Assertions.assertThat(pageGenreResult).isNotNull(),
				()->Assertions.assertThat(pageGenreResult.isEmpty())
				);		
	}

}
