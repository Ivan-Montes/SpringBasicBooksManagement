package ime.BookApp.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

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

import ime.BookApp.service.BookBookshopService;
import ime.BookApp.dto.BookBookshopDTO;

@WebMvcTest(BookBookshopController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class BookBookshopControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BookBookshopService bookbookshopService;
	
	@Test
	void BookBookshopController_getAllBookBookshopDTO_ReturnView()  throws Exception{
		
		List<BookBookshopDTO>list = List.of(Mockito.mock(BookBookshopDTO.class));
		doReturn(list).when(bookbookshopService).getAllBookBookshopDTO();
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/bookbookshops"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("bookbookshops"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("bookbookshops"));
		
		verify(bookbookshopService,times(1)).getAllBookBookshopDTO();
		
	}
	
	@Test
	void BookBookshopController_addBookBookshop_ReturnView() throws Exception{
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/addBookBookshop"))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.view().name("add/addBookBookshop"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("bookshops"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("books"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("newBbsDTO"));	
	}

}
