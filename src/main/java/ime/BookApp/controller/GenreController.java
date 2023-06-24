package ime.BookApp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ime.BookApp.entity.Genre;
import ime.BookApp.service.GenreService;
import jakarta.websocket.server.PathParam;

@Controller
public class GenreController {
	
	@Autowired
	private GenreService genreService;
	
	@GetMapping("/genres")
	public String getAllGenreDTO(Model model) {
		model.addAttribute("genres", genreService.getAllGenreDTO());
		return "genres";
	}
	
	@GetMapping("/addGenre")
	public String addGenre(Model model) {
		model.addAttribute("genre", new Genre());
		return "/add/addGenre";
	}
	
	@GetMapping("/editGenre/{id}")
	public String editGenre(Model model, @PathVariable Long id) {
		model.addAttribute("genre", genreService.findGenreById(id));
		return "/edit/editGenre";
	}
	
	@PostMapping("/updateGenre/{id}")
	public String updateGenre(Model model, @PathVariable Long id) {
		
		
		return "";
	}

}
