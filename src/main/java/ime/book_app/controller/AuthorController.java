package ime.book_app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ime.book_app.dto.AuthorCreationDTO;
import ime.book_app.dto.AuthorDTO;
import ime.book_app.entity.Author;
import ime.book_app.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthorController {
	
	private final AuthorService authorService;
	
	private static final String REDIRECT_AUTHORS = "redirect:/authors";	
	
	@GetMapping( value = {
			"/authors",
			"/authors/{pageNum}"
	})
	public String getAllPaged(Model model, @PathVariable Optional<Integer> pageNum, @RequestParam( defaultValue = "authorId") String sortField, @RequestParam( defaultValue = "asc") String sortDir) {

		int initPageNumber = 1;
		
		if (pageNum.isPresent()) {
			initPageNumber = pageNum.get();
		}
		
		Page<Author> page = authorService.getAllPaged(initPageNumber, sortField, sortDir);
		List<Author>list = page.getContent();

		model.addAttribute("currentPage", initPageNumber);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("totalItems", page.getTotalElements());
	    model.addAttribute("authors", list);
	    model.addAttribute("sortField", sortField);
	    model.addAttribute("sortDir", sortDir);
	    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
	    
		return "authors";
	}
	
	@GetMapping("/addAuthor")
	public String addAuthor(Model model) {
		model.addAttribute("newAuthor", new AuthorCreationDTO());
		return "add/addAuthor";
	}
	
	@PostMapping("/addAuthor")
	public String saveAuthor(@Valid @ModelAttribute("newAuthor")AuthorCreationDTO newAuthor, BindingResult result){
		
		if (result.hasErrors()) {
			return "add/addAuthor";
		}
		
		Author author = new Author();
		author.setName(newAuthor.getName());
		author.setSurname(newAuthor.getSurname());
		authorService.saveAuthor(author);
		return REDIRECT_AUTHORS;
	}
	
	@GetMapping("/editAuthor/{id}")
	public String editAuthor(Model model, @PathVariable Long id) {
		model.addAttribute("author", authorService.findAuthorById(id));
		return "edit/editAuthor";
	}
	
	@PostMapping("/updateAuthor/{id}")
	public String updateAuthor(@PathVariable Long id,@Valid @ModelAttribute("newAuthor") AuthorDTO newAuthor, BindingResult result) {
		
		if (result.hasErrors()) {
			return "redirect:/editAuthor/" + id;
		}
		
		Author author = authorService.findAuthorById(id);
		author.setName(newAuthor.getName());
		author.setSurname(newAuthor.getSurname());
		authorService.saveAuthor(author);
		
		return REDIRECT_AUTHORS;
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
		return REDIRECT_AUTHORS;
	}
}
