package ime.BookApp.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.HashSet;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import ime.BookApp.dto.PublisherDTO;
import ime.BookApp.entity.Book;
import ime.BookApp.entity.Publisher;
import ime.BookApp.service.PublisherService;

@WebMvcTest(PublisherController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class PublisherControllerTest {

	@Autowired
	private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;
    
	@MockBean
	private PublisherService publisherService;	
	
	
	@Test
	void PublisherController_getAllPublisherDTO_ReturnView() throws Exception {
		List<PublisherDTO> publisherDTOList = List.of(Mockito.mock(PublisherDTO.class));
		when(publisherService.getAllPublisherDTO()).thenReturn(publisherDTOList);
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/publishers"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("publishers"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("publishers"))
		.andExpect(MockMvcResultMatchers.model().attribute("publishers", publisherDTOList));
		
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
		Long publisherId = 1L;
		Publisher publisher = Mockito.mock(Publisher.class);
		doReturn(publisher).when(publisherService).findPublisherById(publisherId);
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/editPublisher/{id}", publisherId))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("edit/editPublisher"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("publisher"))
		.andExpect(MockMvcResultMatchers.model().attribute("publisher", publisher));		

		verify(publisherService, times(1)).findPublisherById(Mockito.any(Long.class));
	}
	
	
	@Test
	void PublisherController_updatePublisher_ReturnView() throws Exception{
		
	}
	
	
	@Test
	void PublisherController_deletePublisher_ReturnView() throws Exception{
		Long publisherId = 1L;
		Publisher publisher = Mockito.mock(Publisher.class);
		doReturn(publisher).when(publisherService).findPublisherById(publisherId);
			
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/deletePublisher/{id}",publisherId))
		.andDo(print())
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("delete/confirmDeletePublisher"));
	}
	

	@Test
	void PublisherController_confirmDeletePublisher_ReturnView() throws Exception{
		
	}
}
