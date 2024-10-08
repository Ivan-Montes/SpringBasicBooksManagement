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

import ime.book_app.dto.BookshopCreationDTO;
import ime.book_app.dto.BookshopDTO;
import ime.book_app.entity.Bookshop;
import ime.book_app.service.BookshopService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BookshopController {
	
	private final BookshopService bookshopService;
	
	private static final String REDIRECT_BOOKSHOPS = "redirect:/bookshops";
	
	@GetMapping( value = {
			"/bookshops/{pageNum}",
			"/bookshops"
	})
	public String getAllPaged(Model model, @PathVariable Optional<Integer> pageNum, @RequestParam( defaultValue = "bookshopId") String sortField, @RequestParam( defaultValue = "asc") String sortDir) {
		
		int initPageNumber = 1;
		
		if (pageNum.isPresent()) {
			initPageNumber = pageNum.get();
		}
		
		Page<Bookshop> page = bookshopService.getAllPaged(initPageNumber, sortField, sortDir);
		List<Bookshop>list = page.getContent();

		model.addAttribute("currentPage", initPageNumber);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("totalItems", page.getTotalElements());
	    model.addAttribute("bookshops", list);
	    model.addAttribute("sortField", sortField);
	    model.addAttribute("sortDir", sortDir);
	    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
	    
		return "bookshops";
	}
	
	@GetMapping("/addBookshop")
	public String addBookshopr(Model model) {
		model.addAttribute("newBookshop", new Bookshop() );
		return "add/addBookshop";
	}	
	
	@PostMapping("/addBookshop")
	public String saveBookshop(@Valid @ModelAttribute("newBookshop")BookshopCreationDTO newBookshop, BindingResult result){
		
		if ( result.hasErrors()) {
			return "add/addBookshop";
		}
		Bookshop bookshop = new Bookshop();
		bookshop.setName(newBookshop.getName());
		bookshopService.saveBookshop(bookshop);
		return REDIRECT_BOOKSHOPS;
	}
	
	@GetMapping("/editBookshop/{id}")
	public String editBookshop(Model model, @PathVariable Long id) {
		model.addAttribute("bookshop", bookshopService.findBookshopById(id));
		return "edit/editBookshop";
	}
	
	@PostMapping("/updateBookshop/{id}")
	public String updateBookshop(@PathVariable Long id, @ModelAttribute("newBookshop") BookshopDTO newBookshop, BindingResult result) {

		if ( result.hasErrors()) {
			return "redirect:/editBookshop/" + id;
		}
		
		Bookshop bookshop = bookshopService.findBookshopById(id);
		bookshop.setName(newBookshop.getName());
		bookshopService.updateBookshop(bookshop);
		
		return REDIRECT_BOOKSHOPS;
	}
	
	@GetMapping("/deleteBookshop/{id}")
	public String deleteBookshopr(Model model, @PathVariable Long id) {
		Bookshop bookshop = bookshopService.findBookshopById(id);
		model.addAttribute("bookshop",bookshop);
		return "delete/confirmDeleteBookshop";
	}
	
	@GetMapping("/confirmDeleteBookshop/{id}")
	public String confirmDeleteBookshop(@PathVariable Long id) {
		bookshopService.deleteBookshopById(id);
		return REDIRECT_BOOKSHOPS;
	}
}
