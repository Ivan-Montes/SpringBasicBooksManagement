package ime.BookApp.controller;

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

import ime.BookApp.service.BookBookshopService;
import ime.BookApp.service.BookService;
import ime.BookApp.service.BookshopService;
import ime.BookApp.dto.BookBookshopDTO;
import ime.BookApp.dto.BookDTO;
import ime.BookApp.dto.BookshopDTO;
import ime.BookApp.entity.Book;
import ime.BookApp.entity.BookBookshop;
import ime.BookApp.entity.Bookshop;

@WebMvcTest(BookBookshopController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class BookBookshopControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BookBookshopService bookbookshopService;
	
	@MockBean
	private BookService bookService;
	
	@MockBean
	private BookshopService bookshopService;
	
	@Test
	void BookBookshopController_getAllBookBookshopDTO_ReturnView()  throws Exception{
		
		List<BookBookshopDTO>list = List.of(Mockito.mock(BookBookshopDTO.class));
		doReturn(list).when(bookbookshopService).getAllBookBookshopDTO();
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/bookBookshops"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("bookBookshops"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("bookBookshops"));
		
		verify(bookbookshopService,times(1)).getAllBookBookshopDTO();
		
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
		requestParams.add("isbn", "123456789123456");
		requestParams.add("title", "Test title");
		requestParams.add("bookshopId", "1");
		requestParams.add("name", "Test name");
		requestParams.add("price", "2");
		requestParams.add("units", "3");
		
		doReturn(bbs).when(bookbookshopService).saveBookBookshop(Mockito.any(BookBookshop.class));
		doReturn(bs).when(bookshopService).findBookshopById(Mockito.anyLong());
		doReturn(book).when(bookService).findBookById(Mockito.anyLong());
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.post("/addBookBookshop")
				.params(requestParams))
		.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
		.andExpect(MockMvcResultMatchers.view().name("redirect:/bookBookshops"))
		.andExpect(MockMvcResultMatchers.redirectedUrl("/bookBookshops"));
		
		verify(bookbookshopService,times(1)).saveBookBookshop(Mockito.any());
		verify(bookshopService,times(1)).findBookshopById(Mockito.any());
		verify(bookService,times(1)).findBookById(Mockito.any());
		
	}

	@Test
	void BookBookshopController_prepareAllBookshopDTO_ReturnListOfBookshopDTO()  throws Exception {
		List<BookshopDTO> list = List.of(Mockito.mock(BookshopDTO.class));
		doReturn(list).when(bookshopService).getAllBookshopDTO();
		List<BookshopDTO>bookshopDTOs = bookshopService.getAllBookshopDTO();		
		
		verify(bookshopService,times(1)).getAllBookshopDTO();
		
		Assertions.assertThat(bookshopDTOs).isNotNull();
		Assertions.assertThat(bookshopDTOs).isEqualTo(list);
		
	}

	@Test
	void BookBookshopController_prepareAllBookDTO_ReturnListOfBookDTO()  throws Exception {
		List<BookDTO> list = List.of(Mockito.mock(BookDTO.class));
		doReturn(list).when(bookService).getAllBookDTO();
		List<BookDTO>bookDTOs = bookService.getAllBookDTO();		
		
		verify(bookService,times(1)).getAllBookDTO();
		
		Assertions.assertThat(bookDTOs).isNotNull();
		Assertions.assertThat(bookDTOs).isEqualTo(list);
		
	}
	
}
