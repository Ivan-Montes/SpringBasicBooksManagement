package ime.book_app.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ime.book_app.dto.BookshopCreationDTO;
import ime.book_app.dto.BookshopDTO;
import ime.book_app.entity.Bookshop;
import ime.book_app.service.BookshopService;
import jakarta.validation.Valid;

@Controller
public class BookshopController {

	@Autowired
	private BookshopService bookshopService;
	
	private static final String REDIRECT_BOOKSHOPS = "redirect:/bookshops";
	
	@GetMapping("/bookshops")
	public String getAllBookshopDTO(Model model) {
		model.addAttribute("bookshops", bookshopService.getAllBookshopDTO());
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
