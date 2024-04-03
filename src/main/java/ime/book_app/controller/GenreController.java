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

import ime.book_app.dto.GenreCreationDTO;
import ime.book_app.dto.GenreDTO;
import ime.book_app.entity.Genre;
import ime.book_app.service.GenreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class GenreController {	
	
	private final GenreService genreService;
	
	private static final String REDIRECT_GENRES = "redirect:/genres";
	
	
	@GetMapping( value= {
			"/genres/paged/{pageNum}",
			"/genres/paged",
			"/genres"
	})	
	public String getAllPaged(Model model, @PathVariable Optional<Integer> pageNum, @RequestParam( defaultValue = "genreId") String sortField, @RequestParam( defaultValue = "asc") String sortDir) {
		
		int initPageNumber = 1;
		
		if (pageNum.isPresent()) {
			initPageNumber = pageNum.get();
		}		
		
		Page<Genre> page = genreService.getAllPaged(initPageNumber, sortField, sortDir);
		List<Genre>list = page.getContent();
		
		model.addAttribute("currentPage", initPageNumber);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("totalItems", page.getTotalElements());
	    model.addAttribute("genres", list);
	    model.addAttribute("sortField", sortField);
	    model.addAttribute("sortDir", sortDir);
	    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
	    
		return "genres";
	}

	@GetMapping("/addGenre")
	public String addGenre(Model model) {
		model.addAttribute("newGenre", new GenreCreationDTO());
		return "add/addGenre";
	}
	
	@PostMapping("/addGenre")
	public String saveGenre(@Valid @ModelAttribute("newGenre")GenreCreationDTO newGenre, BindingResult result){

		if (result.hasErrors()) {
			return "add/addGenre";
		}
		
		Genre genre = new Genre();
		genre.setName(newGenre.getName());
		genre.setDescription(newGenre.getDescription());
		genreService.saveGenre(genre);
		return REDIRECT_GENRES;
	}
	
	@GetMapping("/editGenre/{id}")
	public String editGenre(Model model, @PathVariable Long id) {
		model.addAttribute("genre", genreService.findGenreById(id));
		return "edit/editGenre";
	}
	
	@PostMapping("/updateGenre/{id}")
	public String updateGenre(@PathVariable Long id, @Valid @ModelAttribute("newGenre") GenreDTO newGenre, BindingResult result) {
		
		if (result.hasErrors()) {
			return "redirect:/editGenre/" + id;
		}
		
		Genre genre = genreService.findGenreById(id);
		genre.setName(newGenre.getName());
		genre.setDescription(newGenre.getDescription());
		genreService.updateGenre(genre);
		
		return REDIRECT_GENRES;
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
		return REDIRECT_GENRES;
	}
	
}
