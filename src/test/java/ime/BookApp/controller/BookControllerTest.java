package ime.BookApp.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import ime.BookApp.dto.AuthorDTO;
import ime.BookApp.dto.BookDTO;
import ime.BookApp.dto.GenreDTO;
import ime.BookApp.dto.PublisherDTO;
import ime.BookApp.entity.Book;
import ime.BookApp.service.AuthorService;
import ime.BookApp.service.BookService;
import ime.BookApp.service.GenreService;
import ime.BookApp.service.PublisherService;

@WebMvcTest(BookController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class BookControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BookService bookService;
	
	@MockBean
	private PublisherService publisherService;
	
	@MockBean
	private AuthorService authorService;
	
	@MockBean
	private GenreService genreService;
	
	@Test
	void BookController_getAllBookDTO_ReturnView() throws Exception{
		
		List<BookDTO>bookDTOList = List.of(Mockito.mock(BookDTO.class));
		when(bookService.getAllBookDTO()).thenReturn(bookDTOList);
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/books"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("books"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("books"))
		.andExpect(MockMvcResultMatchers.model().attribute("books", bookDTOList));
	}
	
	@Test
	void BookController_addBook_ReturnView() throws Exception{
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/addBook"))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.view().name("add/addBook"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("newBook"));		
	}

	@Test
	void BookController_saveBook_ReturnView() throws Exception{
		
		Book book = Mockito.mock(Book.class);
		doReturn(book).when(bookService).saveBook(Mockito.any(Book.class));
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.post("/addBook")
				.param("name", "nameTest"))
		.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
		.andExpect(MockMvcResultMatchers.view().name("redirect:/books"))
		.andExpect(MockMvcResultMatchers.redirectedUrl("/books"));
	
		verify(bookService,times(1)).saveBook(Mockito.any(Book.class));
	}

	@Test
	void BookController_editBook_ReturnView() throws Exception{
		
		Book book = Mockito.mock(Book.class);
		List<GenreDTO> genreDTOList = List.of(Mockito.mock(GenreDTO.class));
		List<PublisherDTO> publisherDTOList = List.of(Mockito.mock(PublisherDTO.class));
		List<AuthorDTO>authorDTOList = List.of(Mockito.mock(AuthorDTO.class));
		//List<AuthorDTO>author = List.of(Mockito.mock(AuthorDTO.class));
		
		doReturn(book).when(bookService).findBookById(Mockito.any(Long.class));
		when(genreService.getAllGenreDTO()).thenReturn(genreDTOList);
		when(publisherService.getAllPublisherDTO()).thenReturn(publisherDTOList);
		when(authorService.getAllAuthorDTO()).thenReturn(authorDTOList);
		doReturn(authorDTOList).when(authorService).getAuthorDTOByBookIdWithConstructor(Mockito.mock(Long.class));
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/editBook/{id}",Mockito.any(Long.class)))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.view().name("edit/editBook"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("book"))
		.andExpect(MockMvcResultMatchers.model().attribute("book", book))
		.andDo(print());
		
		verify(bookService,times(1)).findBookById(Mockito.any(Long.class));		
	}
/*
	@Test
	void BookController_updateBook_ReturnView() throws Exception{

		Book book = Mockito.mock(Book.class);
		doReturn(book).when(bookService).findBookById(Mockito.any(Long.class));
		doReturn(book).when(bookService).updateBook(Mockito.any(Book.class));
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.post("/updateBook/{id}", Mockito.any(Long.class)))
		.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
		.andExpect(MockMvcResultMatchers.view().name("redirect:/books"))
		.andExpect(MockMvcResultMatchers.redirectedUrl("/books"));
		
		verify(bookService,times(1)).findBookById(Mockito.any(Long.class));
		verify(bookService,times(1)).updateBook(Mockito.any(Book.class));
	}*/

	@Test
	void BookController_deleteBook_ReturnView() throws Exception{
		
		Book book = Mockito.mock(Book.class);
		doReturn(book).when(bookService).findBookById(Mockito.any(Long.class));
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/deleteBook/{id}",Mockito.any(Long.class)))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("delete/confirmDeleteBook"));
		
		verify(bookService,times(1)).findBookById(Mockito.any(Long.class));
	}

	@Test
	void BookController_confirmDeleteBook_ReturnView() throws Exception{
		
		doNothing().when(bookService).deleteBookById(Mockito.any(Long.class));
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/confirmDeleteBook/{id}",Mockito.any(Long.class)))
		.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
		.andExpect(MockMvcResultMatchers.view().name("redirect:/books"))
		.andExpect(MockMvcResultMatchers.redirectedUrl("/books"));
		
		verify(bookService, times(1)).deleteBookById(Mockito.any(Long.class));		
	}
	

}
