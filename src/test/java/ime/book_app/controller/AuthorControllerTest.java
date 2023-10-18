package ime.book_app.controller;

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

import ime.book_app.controller.AuthorController;
import ime.book_app.dto.AuthorDTO;
import ime.book_app.entity.Author;
import ime.book_app.service.AuthorService;

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


	@Test
	void AuthorController_addAuthor_ReturnView() throws Exception{
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/addAuthor"))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.view().name("add/addAuthor"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("newAuthor"));		
	}

	@Test
	void AuthorController_saveAuthor_ReturnView() throws Exception{
		
		Author author = Mockito.mock(Author.class);
		doReturn(author).when(authorService).saveAuthor(Mockito.any(Author.class));
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.post("/addAuthor")
				.param("name", "nameTest"))
		.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
		.andExpect(MockMvcResultMatchers.view().name("redirect:/authors"))
		.andExpect(MockMvcResultMatchers.redirectedUrl("/authors"));
	
		verify(authorService,times(1)).saveAuthor(Mockito.any(Author.class));
	}

	@Test
	void AuthorController_editAuthor_ReturnView() throws Exception{
		
		Author author = Mockito.mock(Author.class);
		doReturn(author).when(authorService).findAuthorById(Mockito.anyLong());
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/editAuthor/{id}", Mockito.anyLong()))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.view().name("edit/editAuthor"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("author"))
		.andExpect(MockMvcResultMatchers.model().attribute("author", author));
		
		verify(authorService,times(1)).findAuthorById(Mockito.anyLong());
	}

	@Test
	void AuthorController_updateAuthor_ReturnView() throws Exception{

		Author author = Mockito.mock(Author.class);
		doReturn(author).when(authorService).findAuthorById(Mockito.anyLong());
		doReturn(author).when(authorService).updateAuthor(Mockito.any(Author.class));
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.post("/updateAuthor/{id}", Mockito.anyLong())
				.param("authorId","1")
				.param("name", "nameTest")
		)
		.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
		.andExpect(MockMvcResultMatchers.view().name("redirect:/authors"))
		.andExpect(MockMvcResultMatchers.redirectedUrl("/authors"));
		
		verify(authorService,times(1)).findAuthorById(Mockito.anyLong());
		verify(authorService,times(1)).updateAuthor(Mockito.any(Author.class));
	}

	@Test
	void AuthorController_deleteAuthor_ReturnView() throws Exception{
		
		Author author = Mockito.mock(Author.class);
		doReturn(author).when(authorService).findAuthorById(Mockito.anyLong());
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/deleteAuthor/{id}", Mockito.anyLong()))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("delete/confirmDeleteAuthor"));
		
		verify(authorService,times(1)).findAuthorById(Mockito.anyLong());
	}

	@Test
	void AuthorController_confirmDeleteAuthor_ReturnView() throws Exception{
		
		doNothing().when(authorService).deleteAuthorById(Mockito.anyLong());
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/confirmDeleteAuthor/{id}", Mockito.anyLong()))
		.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
		.andExpect(MockMvcResultMatchers.view().name("redirect:/authors"))
		.andExpect(MockMvcResultMatchers.redirectedUrl("/authors"));
		
		verify(authorService, times(1)).deleteAuthorById(Mockito.anyLong());
	}
	
}
