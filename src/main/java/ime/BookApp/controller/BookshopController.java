package ime.BookApp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ime.BookApp.entity.Bookshop;
import ime.BookApp.entity.Publisher;
import ime.BookApp.service.BookshopService;

@Controller
public class BookshopController {

	@Autowired
	private BookshopService bookshopService;
	
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
	public String saveBookshop(@ModelAttribute("newBookshop")Bookshop newBookshop){
		bookshopService.saveBookshop(newBookshop);
		return "redirect:/bookshops";
	}
	
	@GetMapping("/editBookshop/{id}")
	public String editBookshop(Model model, @PathVariable Long id) {
		model.addAttribute("bookshop", bookshopService.findBookshopById(id));
		return "edit/editBookshop";
	}
	
	@PostMapping("/updateBookshop/{id}")
	public String updateBookshop(@PathVariable Long id, @ModelAttribute("newBookshop") Publisher newBookshop) {
		Bookshop bookshop = bookshopService.findBookshopById(id);
		bookshop.setName(newBookshop.getName());
		bookshopService.updateBookshop(bookshop);
		
		return "redirect:/bookshops";
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
		return "redirect:/bookshops";
	}
}
