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

import ime.BookApp.dto.PublisherDTO;
import ime.BookApp.service.PublisherService;

@WebMvcTest(PublisherController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class PublisherControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
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

}
