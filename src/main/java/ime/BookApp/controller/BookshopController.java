package ime.BookApp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
