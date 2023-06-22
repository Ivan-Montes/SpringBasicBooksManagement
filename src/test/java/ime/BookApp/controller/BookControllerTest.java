package ime.BookApp.controller;

import static org.mockito.Mockito.when;

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

import ime.BookApp.dto.BookDTO;
import ime.BookApp.service.BookService;

@WebMvcTest(BookController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class BookControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BookService bookService;
	
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

}
