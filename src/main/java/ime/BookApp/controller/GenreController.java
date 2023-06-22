package ime.BookApp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ime.BookApp.service.GenreService;

@Controller
public class GenreController {
	
	@Autowired
	private GenreService genreService;
	
	@GetMapping("/genres")
	public String getAllGenreDTO(Model model) {
		model.addAttribute("genres", genreService.getAllGenreDTO());
		return "genres";
	}

}
