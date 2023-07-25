package ime.BookApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ime.BookApp.dto.PublisherCreationDTO;
import ime.BookApp.dto.PublisherDTO;
import ime.BookApp.entity.Publisher;
import ime.BookApp.service.PublisherService;
import jakarta.validation.Valid;

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
	public String savePublisher(@Valid @ModelAttribute("newPublisher")PublisherCreationDTO newPublisher, BindingResult result){

		if (result.hasErrors()) {
		    return "add/addPublisher";
		  }
		
		Publisher publisher = new Publisher();
		publisher.setName(newPublisher.getName());
		publisherService.savePublisher(publisher);
		return "redirect:/publishers";
	}
	
	@GetMapping("/editPublisher/{id}")
	public String editPublisher(Model model, @PathVariable Long id) {
		Publisher publi = publisherService.findPublisherById(id);
		PublisherDTO publiDTO = new PublisherDTO();
		publiDTO.setPublisherId(publi.getPublisherId());
		publiDTO.setName(publi.getName());
		model.addAttribute("publisher", publiDTO);
		model.addAttribute("newPublisher", new PublisherDTO());
		return "edit/editPublisher";
	}
	
	@PostMapping("/updatePublisher/{id}")
	public String updatePublisher(@PathVariable Long id,@Valid @ModelAttribute("newPublisher") PublisherDTO newPublisher, BindingResult result) {

		if (result.hasErrors()) {
			return "redirect:/editPublisher/" + id;
		  }
		
		Publisher publisher = publisherService.findPublisherById(id);
		publisher.setName(newPublisher.getName());
		publisherService.updatePublisher(publisher);
		
		return "redirect:/publishers";
	}
	
	@GetMapping("/deletePublisher/{id}")
	public String deletePublisher(Model model, @PathVariable Long id) {
		Publisher publisher = publisherService.findPublisherById(id);
		model.addAttribute("publisher",publisher);
		return "delete/confirmDeletePublisher";
	}
	
	@GetMapping("/confirmDeletePublisher/{id}")
	public String confirmDeletePublisher(@PathVariable Long id) {
		publisherService.deletePublisherById(id);
		return "redirect:/publishers";
	}
}
