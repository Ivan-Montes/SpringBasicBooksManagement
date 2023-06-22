package ime.BookApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ime.BookApp.service.PublisherService;

@Controller
public class PublisherController {

	@Autowired
	private PublisherService publisherService;
	
	@GetMapping("/publishers")
	public String getAllPublisherDTO(Model model) {
		model.addAttribute("publishers", publisherService.getAllPublisherDTO());
		return "publishers";
	}
}
