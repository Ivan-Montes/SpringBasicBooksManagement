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

import ime.BookApp.dto.BookshopDTO;
import ime.BookApp.service.BookshopService;


@WebMvcTest(BookshopController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class BookshopControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BookshopService bookshopService;
	
	@Test
	void BookshopController_getAllBookshopDTO_ReturnView() throws Exception{
		
		List<BookshopDTO>bookshopDTOList = List.of(Mockito.mock(BookshopDTO.class));
		when(bookshopService.getAllBookshopDTO()).thenReturn(bookshopDTOList);
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/bookshops"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("bookshops"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("bookshops"))
		.andExpect(MockMvcResultMatchers.model().attribute("bookshops", bookshopDTOList));
		
	}

}
