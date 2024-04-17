package ime.book_app.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import ime.book_app.entity.Bookshop;
import ime.book_app.service.BookshopService;


@WebMvcTest(BookshopController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class BookshopControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BookshopService bookshopService;
	
	@Test
	void BookshopController_getAllPaged_ReturnView() throws Exception{

		@SuppressWarnings("unchecked")
		Page<Bookshop> pageMock = Mockito.mock(Page.class);
		when(bookshopService.getAllPaged(Mockito.anyInt(), Mockito.anyString(), Mockito.anyString())).thenReturn(pageMock);
		doReturn(new ArrayList<Bookshop>()).when(pageMock).getContent();
		when(pageMock.getContent()).thenReturn(new ArrayList<Bookshop>());
		when(pageMock.getTotalPages()).thenReturn(8);
		when(pageMock.getTotalElements()).thenReturn(7L);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/bookshops"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("bookshops"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("currentPage"))
		.andExpect(MockMvcResultMatchers.model().attribute("currentPage", 1))
		.andExpect(MockMvcResultMatchers.model().attributeExists("totalPages"))
		.andExpect(MockMvcResultMatchers.model().attribute("totalPages", 8))
		.andExpect(MockMvcResultMatchers.model().attributeExists("totalItems"))
		.andExpect(MockMvcResultMatchers.model().attribute("totalItems", 7L))
		.andExpect(MockMvcResultMatchers.model().attributeExists("bookshops"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("sortField"))
		.andExpect(MockMvcResultMatchers.model().attribute("sortField","bookshopId"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("sortDir"))
		.andExpect(MockMvcResultMatchers.model().attribute("sortDir", "asc"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("reverseSortDir"))
		.andExpect(MockMvcResultMatchers.model().attribute("reverseSortDir", "desc"));
		
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
		doReturn(bookshop).when(bookshopService).findBookshopById(Mockito.anyLong());
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/editBookshop/{id}", Mockito.anyLong()))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.view().name("edit/editBookshop"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("bookshop"))
		.andExpect(MockMvcResultMatchers.model().attribute("bookshop", bookshop));
		
		verify(bookshopService,times(1)).findBookshopById(Mockito.anyLong());
	}

	@Test
	void BookshopController_updateBookshop_ReturnView() throws Exception{

		Bookshop bookshop = Mockito.mock(Bookshop.class);
		doReturn(bookshop).when(bookshopService).findBookshopById(Mockito.anyLong());
		doReturn(bookshop).when(bookshopService).updateBookshop(Mockito.any(Bookshop.class));
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.post("/updateBookshop/{id}", Mockito.anyLong()))
		.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
		.andExpect(MockMvcResultMatchers.view().name("redirect:/bookshops"))
		.andExpect(MockMvcResultMatchers.redirectedUrl("/bookshops"));
		
		verify(bookshopService,times(1)).findBookshopById(Mockito.anyLong());
		verify(bookshopService,times(1)).updateBookshop(Mockito.any(Bookshop.class));
	}

	@Test
	void BookshopController_deleteBookshop_ReturnView() throws Exception{
		
		Bookshop bookshop = Mockito.mock(Bookshop.class);
		doReturn(bookshop).when(bookshopService).findBookshopById(Mockito.anyLong());
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/deleteBookshop/{id}", Mockito.anyLong()))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("delete/confirmDeleteBookshop"));
		
		verify(bookshopService,times(1)).findBookshopById(Mockito.anyLong());
	}

	@Test
	void BookshopController_confirmDeleteBookshop_ReturnView() throws Exception{
		
		doNothing().when(bookshopService).deleteBookshopById(Mockito.anyLong());
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/confirmDeleteBookshop/{id}", Mockito.anyLong()))
		.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
		.andExpect(MockMvcResultMatchers.view().name("redirect:/bookshops"))
		.andExpect(MockMvcResultMatchers.redirectedUrl("/bookshops"));
		
		verify(bookshopService, times(1)).deleteBookshopById(Mockito.anyLong());
	}

}
