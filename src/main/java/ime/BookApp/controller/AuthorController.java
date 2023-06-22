package ime.BookApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ime.BookApp.service.*;

@Controller
public class AuthorController {
	
	private AuthorService authorService;
	
	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;
	}
	
	@GetMapping("/authors")
	public String getAllAuthorDTO(Model model) {		
		model.addAttribute("authors", authorService.getAllAuthorDTO());
		return "authors";
	}
}
