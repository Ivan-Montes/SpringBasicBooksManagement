package ime.BookApp.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
import ime.BookApp.entity.Genre;
import ime.BookApp.service.GenreService;

@WebMvcTest(GenreController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class GenreControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private GenreService genreService;
	
	@Test
	void GenreController_getAllGenreDTO_ReturnView() throws Exception{
		
		List<GenreDTO> genreDTOList = List.of(Mockito.mock(GenreDTO.class));
		when(genreService.getAllGenreDTO()).thenReturn(genreDTOList);
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/genres"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("genres"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("genres"))
		.andExpect(MockMvcResultMatchers.model().attribute("genres", genreDTOList));
	}
	
	@Test
	void GenreController_addGenre_ReturnView() throws Exception{
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/addGenre"))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.view().name("add/addGenre"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("newGenre"));		
	}

	@Test
	void GenreController_saveGenre_ReturnView() throws Exception{
		
		Genre genre = Mockito.mock(Genre.class);
		doReturn(genre).when(genreService).saveGenre(Mockito.any(Genre.class));
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.post("/addGenre")
				.param("name", "nameTest"))
		.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
		.andExpect(MockMvcResultMatchers.view().name("redirect:/genres"))
		.andExpect(MockMvcResultMatchers.redirectedUrl("/genres"));
	
		verify(genreService,times(1)).saveGenre(Mockito.any(Genre.class));
	}

	@Test
	void GenreController_editGenre_ReturnView() throws Exception{
		
		Genre genre = Mockito.mock(Genre.class);
		doReturn(genre).when(genreService).findGenreById(Mockito.anyLong());
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/editGenre/{id}",Mockito.anyLong()))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.view().name("edit/editGenre"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("genre"))
		.andExpect(MockMvcResultMatchers.model().attribute("genre", genre));
		
		verify(genreService,times(1)).findGenreById(Mockito.anyLong());		
	}

	@Test
	void GenreController_updateGenre_ReturnView() throws Exception{

		Genre genre = Mockito.mock(Genre.class);
		doReturn(genre).when(genreService).findGenreById(Mockito.anyLong());
		doReturn(genre).when(genreService).updateGenre(Mockito.any(Genre.class));
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.post("/updateGenre/{id}", Mockito.anyLong())
				.param("genreId", "1")
				.param("name", "nameTest")
		)
		.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
		.andExpect(MockMvcResultMatchers.view().name("redirect:/genres"))
		.andExpect(MockMvcResultMatchers.redirectedUrl("/genres"));
		
		verify(genreService,times(1)).findGenreById(Mockito.anyLong());
		verify(genreService,times(1)).updateGenre(Mockito.any(Genre.class));
	}

	@Test
	void GenreController_deleteGenre_ReturnView() throws Exception{
		
		Genre genre = Mockito.mock(Genre.class);
		doReturn(genre).when(genreService).findGenreById(Mockito.anyLong());
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/deleteGenre/{id}", Mockito.anyLong()))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("delete/confirmDeleteGenre"));
		
		verify(genreService,times(1)).findGenreById(Mockito.anyLong());
	}

	@Test
	void GenreController_confirmDeleteGenre_ReturnView() throws Exception{
		
		doNothing().when(genreService).deleteGenreById(Mockito.anyLong());
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/confirmDeleteGenre/{id}", Mockito.anyLong()))
		.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
		.andExpect(MockMvcResultMatchers.view().name("redirect:/genres"))
		.andExpect(MockMvcResultMatchers.redirectedUrl("/genres"));
		
		verify(genreService, times(1)).deleteGenreById(Mockito.anyLong());		
	}
	
	

}
