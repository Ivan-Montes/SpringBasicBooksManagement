package ime.book_app.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(MainController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class MainControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	void MainController_mainIndex_ReturnView() throws Exception{
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/index"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("index"));
	}

	@Test
	void MainController_login_ReturnView() throws Exception{
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/login"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("login"));
	}

	@Test
	void MainController_successLogin_ReturnView() throws Exception{
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/success"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("success"));
	}

}
