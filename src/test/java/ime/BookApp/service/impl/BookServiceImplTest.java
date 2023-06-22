package ime.BookApp.service.impl;

import static org.mockito.Mockito.when;

import java.util.List;

import org.mockito.junit.jupiter.MockitoExtension;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import ime.BookApp.dto.*;
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
	

}
