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

import ime.BookApp.dto.GenreDTO;
import ime.BookApp.service.GenreService;

@WebMvcTest(GenreController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class GenreControllerTest {

	@Autowired
	private MockMvc mock;
	
	@MockBean
	private GenreService genreService;
	
	@Test
	void GenreController_getAllGenreDTO_ReturnView() throws Exception{
		
		List<GenreDTO> genreDTOList = List.of(Mockito.mock(GenreDTO.class));
		when(genreService.getAllGenreDTO()).thenReturn(genreDTOList);
		
		this.mock
		.perform(MockMvcRequestBuilders.get("/genres"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("genres"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("genres"))
		.andExpect(MockMvcResultMatchers.model().attribute("genres", genreDTOList));
	}

}
