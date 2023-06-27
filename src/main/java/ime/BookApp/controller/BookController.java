package ime.BookApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ime.BookApp.entity.Book;
import ime.BookApp.entity.Genre;
import ime.BookApp.service.*;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;
	
	@GetMapping("/books")
	public String getAllBookDTO(Model model) {
		model.addAttribute("books", bookService.getAllBookDTO());
		return "books";
	}
	
	/*
	@GetMapping("/addGenre")
	public String addGenre(Model model) {
		model.addAttribute("newGenre", new Genre());
		return "/add/addGenre";
	}
	@PostMapping("/addGenre")
	public String saveGenre(@ModelAttribute("newGenre")Genre newGenre){
		genreService.saveGenre(newGenre);
		return "redirect:/genres";
	}
	
	@GetMapping("/editGenre/{id}")
	public String editGenre(Model model, @PathVariable Long id) {
		model.addAttribute("genre", genreService.findGenreById(id));
		return "/edit/editGenre";
	}
	
	@PostMapping("/updateGenre/{id}")
	public String updateGenre(@PathVariable Long id, @ModelAttribute("newGenre") Genre newGenre) {
		Genre genre = genreService.findGenreById(id);
		genre.setName(newGenre.getName());
		genre.setDescription(newGenre.getDescription());
		genreService.updateGenre(genre);
		
		return "redirect:/genres";
	} */
	
	@GetMapping("/deleteBook/{id}")
	public String deleteBook(Model model, @PathVariable Long id) {
		Book book = bookService.findBookById(id);
		model.addAttribute("book",book);
		return "/delete/confirmDeleteBook";
	}

	
	@GetMapping("/confirmDeleteBook/{id}")
	public String confirmDeleteBook(@PathVariable Long id) {
		bookService.deleteBookById(id);
		return "redirect:/books";
	}
	 
	 
	
}
