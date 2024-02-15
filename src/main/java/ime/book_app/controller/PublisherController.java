package ime.book_app.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ime.book_app.dto.PublisherCreationDTO;
import ime.book_app.dto.PublisherDTO;
import ime.book_app.entity.Publisher;
import ime.book_app.service.PublisherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PublisherController {

	private final PublisherService publisherService;

	private static final String REDIRECT_PUBLISHERS = "redirect:/publishers";
	
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
		return REDIRECT_PUBLISHERS;
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
		
		return REDIRECT_PUBLISHERS;
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
		return REDIRECT_PUBLISHERS;
	}
}
