package ime.BookApp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ime.BookApp.dto.GenreCreationDTO;
import ime.BookApp.dto.GenreDTO;
import ime.BookApp.entity.Genre;
import ime.BookApp.service.GenreService;
import jakarta.validation.Valid;

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
		model.addAttribute("newGenre", new GenreCreationDTO());
		return "add/addGenre";
	}
	
	@PostMapping("/addGenre")
	public String saveGenre(@Valid @ModelAttribute("newGenre")GenreCreationDTO newGenre){
		Genre genre = new Genre();
		genre.setName(newGenre.getName());
		genre.setDescription(newGenre.getDescription());
		genreService.saveGenre(genre);
		return "redirect:/genres";
	}
	
	@GetMapping("/editGenre/{id}")
	public String editGenre(Model model, @PathVariable Long id) {
		model.addAttribute("genre", genreService.findGenreById(id));
		return "edit/editGenre";
	}
	
	@PostMapping("/updateGenre/{id}")
	public String updateGenre(@PathVariable Long id, @Valid @ModelAttribute("newGenre") GenreDTO newGenre) {
		Genre genre = genreService.findGenreById(id);
		genre.setName(newGenre.getName());
		genre.setDescription(newGenre.getDescription());
		genreService.updateGenre(genre);
		
		return "redirect:/genres";
	}
	
	@GetMapping("/deleteGenre/{id}")
	public String deleteGenre(Model model, @PathVariable Long id) {
		Genre genre = genreService.findGenreById(id);
		model.addAttribute("genre",genre);
		return "delete/confirmDeleteGenre";
	}
	
	@GetMapping("/confirmDeleteGenre/{id}")
	public String confirmDeleteGenre(@PathVariable Long id) {
		genreService.deleteGenreById(id);
		return "redirect:/genres";
	}
	

}
