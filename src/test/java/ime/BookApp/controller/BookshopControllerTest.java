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

import ime.BookApp.dto.BookshopDTO;
import ime.BookApp.entity.Bookshop;
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
	
	@Test
	void BookshopController_addBookshop_ReturnView() throws Exception{
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/addBookshop"))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.view().name("add/addBookshop"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("newBookshop"));		
	}

	@Test
	void BookshopController_saveBookshop_ReturnView() throws Exception{
		
		Bookshop genre = Mockito.mock(Bookshop.class);
		doReturn(genre).when(bookshopService).saveBookshop(Mockito.any(Bookshop.class));
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.post("/addBookshop")
				.param("name", "nameTest"))
		.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
		.andExpect(MockMvcResultMatchers.view().name("redirect:/bookshops"))
		.andExpect(MockMvcResultMatchers.redirectedUrl("/bookshops"));
	
		verify(bookshopService,times(1)).saveBookshop(Mockito.any(Bookshop.class));
	}

	@Test
	void BookshopController_editBookshop_ReturnView() throws Exception{
		
		Bookshop bookshop = Mockito.mock(Bookshop.class);
		doReturn(bookshop).when(bookshopService).findBookshopById(Mockito.any(Long.class));
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/editBookshop/{id}",Mockito.any(Long.class)))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.view().name("edit/editBookshop"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("bookshop"))
		.andExpect(MockMvcResultMatchers.model().attribute("bookshop", bookshop));
		
		verify(bookshopService,times(1)).findBookshopById(Mockito.any(Long.class));		
	}

	@Test
	void BookshopController_updateBookshop_ReturnView() throws Exception{

		Bookshop bookshop = Mockito.mock(Bookshop.class);
		doReturn(bookshop).when(bookshopService).findBookshopById(Mockito.any(Long.class));
		doReturn(bookshop).when(bookshopService).updateBookshop(Mockito.any(Bookshop.class));
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.post("/updateBookshop/{id}", Mockito.any(Long.class)))
		.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
		.andExpect(MockMvcResultMatchers.view().name("redirect:/bookshops"))
		.andExpect(MockMvcResultMatchers.redirectedUrl("/bookshops"));
		
		verify(bookshopService,times(1)).findBookshopById(Mockito.any(Long.class));
		verify(bookshopService,times(1)).updateBookshop(Mockito.any(Bookshop.class));
	}

	@Test
	void BookshopController_deleteBookshop_ReturnView() throws Exception{
		
		Bookshop bookshop = Mockito.mock(Bookshop.class);
		doReturn(bookshop).when(bookshopService).findBookshopById(Mockito.any(Long.class));
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/deleteBookshop/{id}",Mockito.any(Long.class)))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("delete/confirmDeleteBookshop"));
		
		verify(bookshopService,times(1)).findBookshopById(Mockito.any(Long.class));
	}

	@Test
	void BookshopController_confirmDeleteBookshop_ReturnView() throws Exception{
		
		doNothing().when(bookshopService).deleteBookshopById(Mockito.any(Long.class));
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/confirmDeleteBookshop/{id}",Mockito.any(Long.class)))
		.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
		.andExpect(MockMvcResultMatchers.view().name("redirect:/bookshops"))
		.andExpect(MockMvcResultMatchers.redirectedUrl("/bookshops"));
		
		verify(bookshopService, times(1)).deleteBookshopById(Mockito.any(Long.class));		
	}

}
