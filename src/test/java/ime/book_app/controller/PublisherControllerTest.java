package ime.book_app.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.assertj.core.api.Assertions;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import ime.book_app.entity.Publisher;
import ime.book_app.service.PublisherService;

@WebMvcTest(PublisherController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class PublisherControllerTest {

	@Autowired
	private MockMvc mockMvc;
    
	@MockBean
	private PublisherService publisherService;	
	
	
	@Test
	void PublisherController_getAllPaged_ReturnView() throws Exception {

		@SuppressWarnings("unchecked")
		Page<Publisher> pageMock = Mockito.mock(Page.class);
		when(publisherService.getAllPaged(Mockito.anyInt(), Mockito.anyString(), Mockito.anyString())).thenReturn(pageMock);
		doReturn(new ArrayList<Publisher>()).when(pageMock).getContent();
		when(pageMock.getContent()).thenReturn(new ArrayList<Publisher>());
		when(pageMock.getTotalPages()).thenReturn(8);
		when(pageMock.getTotalElements()).thenReturn(7L);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/publishers"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("publishers"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("currentPage"))
		.andExpect(MockMvcResultMatchers.model().attribute("currentPage", 1))
		.andExpect(MockMvcResultMatchers.model().attributeExists("totalPages"))
		.andExpect(MockMvcResultMatchers.model().attribute("totalPages", 8))
		.andExpect(MockMvcResultMatchers.model().attributeExists("totalItems"))
		.andExpect(MockMvcResultMatchers.model().attribute("totalItems", 7L))
		.andExpect(MockMvcResultMatchers.model().attributeExists("publishers"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("sortField"))
		.andExpect(MockMvcResultMatchers.model().attribute("sortField","publisherId"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("sortDir"))
		.andExpect(MockMvcResultMatchers.model().attribute("sortDir", "asc"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("reverseSortDir"))
		.andExpect(MockMvcResultMatchers.model().attribute("reverseSortDir", "desc"));
		
	}
	
	@Test
	void PublisherController_addPublisher_ReturnView() throws Exception{		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/addPublisher"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("add/addPublisher"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("newPublisher"));
	}
	

	@Test
	void PublisherController_savePublisher_ReturnView() throws Exception{		
		
		Publisher publisher = Mockito.mock(Publisher.class);
		
		doReturn(publisher).when(publisherService).savePublisher(Mockito.any(Publisher.class));
		
		MvcResult result =this.mockMvc
		.perform(MockMvcRequestBuilders.post("/addPublisher")
				.param("name", "PubliTest")
				)
	      .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
	      .andExpect(MockMvcResultMatchers.view().name("redirect:/publishers"))
	      .andExpect(MockMvcResultMatchers.redirectedUrl("/publishers"))
		.andReturn();
		
		Assertions.assertThat(result).isNotNull();
		verify(publisherService, times(1)).savePublisher(Mockito.any(Publisher.class));
	}
	
	
	@Test
	void PublisherController_editPublisher_ReturnView() throws Exception{
		
		Publisher publisher = Mockito.mock(Publisher.class);
		doReturn(publisher).when(publisherService).findPublisherById(Mockito.anyLong());
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/editPublisher/{id}", Mockito.anyLong()))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("edit/editPublisher"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("publisher"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("newPublisher"));		

		verify(publisherService, times(1)).findPublisherById(Mockito.anyLong());
	}
	
	
	@Test
	void PublisherController_updatePublisher_ReturnView() throws Exception{
		
		Publisher publisher = Mockito.mock(Publisher.class);
		doReturn(publisher).when(publisherService).findPublisherById(Mockito.anyLong());
		doReturn(publisher).when(publisherService).updatePublisher(Mockito.any(Publisher.class));
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.post("/updatePublisher/{id}", Mockito.anyLong())
				.param("publisherId","1")
				.param("name","nameTest")
				)
		.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
		.andExpect(MockMvcResultMatchers.view().name("redirect:/publishers"))
		.andExpect(MockMvcResultMatchers.redirectedUrl("/publishers"));
		
		verify(publisherService, times(1)).findPublisherById(Mockito.anyLong());
		verify(publisherService, times(1)).updatePublisher(publisher);
	}
	
	
	@Test
	void PublisherController_deletePublisher_ReturnView() throws Exception{
		
		Publisher publisher = Mockito.mock(Publisher.class);
		doReturn(publisher).when(publisherService).findPublisherById(Mockito.anyLong());
			
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/deletePublisher/{id}", Mockito.anyLong()))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("delete/confirmDeletePublisher"));
		
		verify(publisherService, times(1)).findPublisherById(Mockito.anyLong());
	}
	

	@Test
	void PublisherController_confirmDeletePublisher_ReturnView() throws Exception{
		
		doNothing().when(publisherService).deletePublisherById(Mockito.anyLong());
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/confirmDeletePublisher/{id}",Mockito.anyLong()))
		.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
		.andExpect(MockMvcResultMatchers.view().name("redirect:/publishers"))
		.andExpect(MockMvcResultMatchers.redirectedUrl("/publishers"));
		
		verify(publisherService, times(1)).deletePublisherById(Mockito.anyLong());

	}
}
