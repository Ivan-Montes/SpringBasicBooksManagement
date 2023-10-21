package ime.book_app.controller;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.util.List;

import org.assertj.core.api.Assertions;
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
import org.springframework.util.LinkedMultiValueMap;

import ime.book_app.dto.BookBookshopDTO;
import ime.book_app.dto.BookDTO;
import ime.book_app.dto.BookshopDTO;
import ime.book_app.entity.Book;
import ime.book_app.entity.BookBookshop;
import ime.book_app.entity.BookBookshopId;
import ime.book_app.entity.Bookshop;
import ime.book_app.service.BookBookshopService;
import ime.book_app.service.BookService;
import ime.book_app.service.BookshopService;

@WebMvcTest(BookBookshopController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class BookBookshopControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BookBookshopService bookBookshopService;
	
	@MockBean
	private BookService bookService;
	
	@MockBean
	private BookshopService bookshopService;
	
	@Test
	void BookBookshopController_getAllBookBookshopDTO_ReturnView()  throws Exception{
		
		List<BookBookshopDTO>list = List.of(Mockito.mock(BookBookshopDTO.class));
		doReturn(list).when(bookBookshopService).getAllBookBookshopDTO();
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/bookBookshops"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("bookBookshops"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("bookBookshops"));
		
		verify(bookBookshopService,times(1)).getAllBookBookshopDTO();
		
	}
	
	@Test
	void BookBookshopController_addBookBookshop_ReturnView() throws Exception{
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/addBookBookshop"))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.view().name("add/addBookBookshop"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("allBookshopDTO"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("allBookDTO"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("newBbsDTO"));	
	}
	
	@Test
	void BookBookshopController_saveBookBookshop_ReturnView() throws Exception {
		
		BookBookshop bbs = Mockito.mock(BookBookshop.class);
		Bookshop bs = Mockito.mock(Bookshop.class);
		Book book = Mockito.mock(Book.class);
				
		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("bookId", "1");
		requestParams.add("isbn", "1234567891234");
		requestParams.add("title", "Test title");
		requestParams.add("bookshopId", "1");
		requestParams.add("name", "Test name");
		requestParams.add("price", "2");
		requestParams.add("units", "3");
		
		doReturn(bbs).when(bookBookshopService).saveBookBookshop(Mockito.any(BookBookshop.class));
		doReturn(bs).when(bookshopService).findBookshopById(Mockito.anyLong());
		doReturn(book).when(bookService).findBookById(Mockito.anyLong());
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.post("/addBookBookshop")
				.params(requestParams))
		.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
		.andExpect(MockMvcResultMatchers.view().name("redirect:/bookBookshops"))
		.andExpect(MockMvcResultMatchers.redirectedUrl("/bookBookshops"));
		
		verify(bookBookshopService,times(1)).saveBookBookshop(Mockito.any());
		verify(bookshopService,times(1)).findBookshopById(Mockito.any());
		verify(bookService,times(1)).findBookById(Mockito.any());
		
	}

	@Test
	void BookBookshopController_prepareAllBookshopDTO_ReturnListOfBookshopDTO()  throws Exception {
		List<BookshopDTO> list = List.of(Mockito.mock(BookshopDTO.class));
		doReturn(list).when(bookshopService).getAllBookshopDTO();
		List<BookshopDTO>bookshopDTOs = bookshopService.getAllBookshopDTO();		
		
		verify(bookshopService,times(1)).getAllBookshopDTO();
		
		assertAll(
				()->Assertions.assertThat(bookshopDTOs).isNotNull(),
				()->Assertions.assertThat(bookshopDTOs).isEqualTo(list)
				);
	}

	@Test
	void BookBookshopController_prepareAllBookDTO_ReturnListOfBookDTO()  throws Exception {
		List<BookDTO> list = List.of(Mockito.mock(BookDTO.class));
		doReturn(list).when(bookService).getAllBookDTO();
		List<BookDTO>bookDTOs = bookService.getAllBookDTO();		
		
		verify(bookService,times(1)).getAllBookDTO();
		
		assertAll(
				()->Assertions.assertThat(bookDTOs).isNotNull(),
				()->Assertions.assertThat(bookDTOs).isEqualTo(list)
				);		
	}
	
	@Test
	void BookBookshopController_deleteBookBookshop_ReturnView() throws Exception{
	
		BookBookshop bbs = new BookBookshop();
		bbs.setBook(Mockito.mock(Book.class));
		bbs.setBookshop(Mockito.mock(Bookshop.class));
		bbs.setBookBookshopId(Mockito.mock(BookBookshopId.class));
		
		doReturn(bbs).when(bookBookshopService).findBookBookshopById(Mockito.any(BookBookshopId.class));
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/deleteBookBookshop")
				  .param("bookId", "1")
				  .param("bookshopId","1")				 
				)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("delete/confirmDeleteBookBookshop"));
		
		verify(bookBookshopService,times(1)).findBookBookshopById(Mockito.any(BookBookshopId.class));
	}
	
	@Test
	void BookBookshopController_confirmDeleteBookBookshop_ReturnView() throws Exception{
	
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/confirmDeleteBookBookshop")
				  .param("bookId", "1")
				  .param("bookshopId","1")				 
				)
		.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
		.andExpect(MockMvcResultMatchers.view().name("redirect:/bookBookshops"))
		.andExpect(MockMvcResultMatchers.redirectedUrl("/bookBookshops"));
		
		verify(bookBookshopService,times(1)).deleteBookBookshop(Mockito.any(BookBookshopId.class));
		
	}
	
	@Test
	void BookBookshopController_editBookBookshop_ReturnView() throws Exception{
		
		BookBookshopDTO bbs = Mockito.mock(BookBookshopDTO.class);
		
		doReturn(bbs).when(bookBookshopService).getBookBookshopDTOById(Mockito.anyLong(),Mockito.anyLong());
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/editBookBookshop")
				  .param("bookId", "1")
				  .param("bookshopId","1")				 
				)
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.model().attributeExists("thebbs"))
		.andExpect(MockMvcResultMatchers.model().attribute("thebbs", bbs))
		.andExpect(MockMvcResultMatchers.view().name("edit/editBookBookshop"));
		
		verify(bookBookshopService,times(1)).getBookBookshopDTOById(Mockito.anyLong(),Mockito.anyLong());
	}
	
	@Test
	void BookBookshopController_updateBookshop_ReturnView() throws Exception{
		
		BookBookshop bbs = Mockito.mock(BookBookshop.class);		
		
		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("bookId", "1");
		requestParams.add("isbn", "1234567891234");
		requestParams.add("title", "Test title");
		requestParams.add("bookshopId", "1");
		requestParams.add("name", "Test name");
		requestParams.add("price", "2");
		requestParams.add("units", "3");
		
		doReturn(bbs).when(bookBookshopService).findBookBookshopById(Mockito.any());
		doReturn(bbs).when(bookBookshopService).saveBookBookshop(Mockito.any());
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.post("/updateBookBookshop")
				.params(requestParams)
				)
		.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
		.andExpect(MockMvcResultMatchers.view().name("redirect:/bookBookshops"))
		.andExpect(MockMvcResultMatchers.redirectedUrl("/bookBookshops"));
		
		verify(bookBookshopService,times(1)).findBookBookshopById(Mockito.any(BookBookshopId.class));
		verify(bookBookshopService,times(1)).saveBookBookshop(Mockito.any());
	}
 
	
	
}
