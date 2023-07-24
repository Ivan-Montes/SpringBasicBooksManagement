package ime.BookApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ime.BookApp.dto.AuthorCreationDTO;
import ime.BookApp.dto.AuthorDTO;
import ime.BookApp.entity.Author;
import ime.BookApp.service.*;
import jakarta.validation.Valid;

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
	
	@GetMapping("/addAuthor")
	public String addAuthor(Model model) {
		model.addAttribute("newAuthor", new Author());
		return "add/addAuthor";
	}
	
	@PostMapping("/addAuthor")
	public String saveAuthor(@Valid @ModelAttribute("newAuthor")AuthorCreationDTO newAuthor){
		Author author = new Author();
		author.setName(newAuthor.getName());
		authorService.saveAuthor(author);
		return "redirect:/authors";
	}
	
	@GetMapping("/editAuthor/{id}")
	public String editAuthor(Model model, @PathVariable Long id) {
		model.addAttribute("author", authorService.findAuthorById(id));
		return "edit/editAuthor";
	}
	
	@PostMapping("/updateAuthor/{id}")
	public String updateAuthor(@PathVariable Long id,@Valid @ModelAttribute("newAuthor") AuthorDTO newAuthor) {
		Author author = authorService.findAuthorById(id);
		author.setName(newAuthor.getName());
		author.setSurname(newAuthor.getSurname());
		authorService.updateAuthor(author);
		
		return "redirect:/authors";
	}
	
	@GetMapping("/deleteAuthor/{id}")
	public String deleteAuthor(Model model, @PathVariable Long id) {
		Author author = authorService.findAuthorById(id);
		model.addAttribute("author",author);
		return "delete/confirmDeleteAuthor";
	}
	
	@GetMapping("/confirmDeleteAuthor/{id}")
	public String confirmDeleteAuthor(@PathVariable Long id) {
		authorService.deleteAuthorById(id);
		return "redirect:/authors";
	}
}
