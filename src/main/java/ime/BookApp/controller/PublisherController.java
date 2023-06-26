package ime.BookApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ime.BookApp.entity.Publisher;
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

	@GetMapping("/addPublisher")
	public String addPublisher(Model model) {
		model.addAttribute("newPublisher", new Publisher() );
		return "add/addPublisher";
	}	
	
	@PostMapping("/addPublisher")
	public String savePublisher(@ModelAttribute("newPublisher")Publisher newPublisher){
		publisherService.savePublisher(newPublisher);
		return "redirect:/publishers";
	}
	
	@GetMapping("/editPublisher/{id}")
	public String editPublisher(Model model, @PathVariable Long id) {
		model.addAttribute("publisher", publisherService.findPublisherById(id));
		return "/edit/editPublisher";
	}
	
	@PostMapping("/updatePublisher/{id}")
	public String updatePublisher(@PathVariable Long id, @ModelAttribute("newPublisher") Publisher newPublisher) {
		Publisher publisher = publisherService.findPublisherById(id);
		publisher.setName(newPublisher.getName());
		publisherService.updatePublisher(publisher);
		
		return "redirect:/publishers";
	}
	
	@GetMapping("/deletePublisher/{id}")
	public String deletePublisher(Model model, @PathVariable Long id) {
		Publisher publisher = publisherService.findPublisherById(id);
		model.addAttribute("publisher",publisher);
		return "/delete/confirmDeletePublisher";
	}
	
	@GetMapping("/confirmDeletePublisher/{id}")
	public String confirmDeletePublisher(@PathVariable Long id) {
		publisherService.deletePublisherById(id);
		return "redirect:/publishers";
	}
}
