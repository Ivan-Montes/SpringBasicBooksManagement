package ime.BookApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ime.BookApp.service.BookBookshopService;
import ime.BookApp.dto.*;
import ime.BookApp.entity.BookBookshop;

@Controller
public class BookBookshopController {

	@Autowired
	private BookBookshopService bookBookshopService;
	
	
	@GetMapping("/bookbookshops")
	private String getAllBookBookshopDTO(Model model){
		model.addAttribute("bookbookshops", bookBookshopService.getAllBookBookshopDTO());
		return "bookbookshops";
	}
	
	@GetMapping("/addBookBookshop")
	private String addBookBookshop(Model model) {
		model.addAttribute("bookbookshops", bookBookshopService.getAllBookBookshopDTO());
		model.addAttribute("bbs", new BookBookshop());
		model.addAttribute("bbsDTO", new BookBookshopDTO());
		return "add/addBookBookshop";
	}
	
	
}
