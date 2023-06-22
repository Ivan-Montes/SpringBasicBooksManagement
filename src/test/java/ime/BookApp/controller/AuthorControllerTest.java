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

import ime.BookApp.dto.AuthorDTO;
import ime.BookApp.service.AuthorService;

@WebMvcTest(AuthorController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class AuthorControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AuthorService authorService;
	
	@Test
	void AuthorController_getAllAuthorDTO_ReturnView() throws Exception{
		List<AuthorDTO>authorDTOList = List.of(Mockito.mock(AuthorDTO.class));
		when(authorService.getAllAuthorDTO()).thenReturn(authorDTOList);
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/authors"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("authors"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("authors"))
		.andExpect(MockMvcResultMatchers.model().attribute("authors", authorDTOList));
	}

}
