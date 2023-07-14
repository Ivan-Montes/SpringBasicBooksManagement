package ime.BookApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ime.BookApp.service.BookBookshopService;
import ime.BookApp.service.BookService;
import ime.BookApp.service.BookshopService;
import ime.BookApp.dto.*;
import ime.BookApp.entity.BookBookshop;
import ime.BookApp.entity.BookBookshopId;

@Controller
public class BookBookshopController {

	@Autowired
	private BookBookshopService bookBookshopService;
	
	@Autowired
	private BookService bookService;

	@Autowired
	private BookshopService bookshopService;
	
	
	@GetMapping("/bookBookshops")
	private String getAllBookBookshopDTO(Model model){
		model.addAttribute("bookbookshops", bookBookshopService.getAllBookBookshopDTO());
		return "bookBookshops";
	}
	
	@GetMapping("/addBookBookshop")
	private String addBookBookshop(Model model) {
		model.addAttribute("bookshops", bookshopService.getAllBookshopDTO());
		model.addAttribute("books", bookService.getAllBookDTO());
		model.addAttribute("newBbsDTO", new BookBookshopDTO());
		return "add/addBookBookshop";
	}
	
	@PostMapping("/addBookBookshop")
	private String saveBookBookshop(@ModelAttribute("newBbsDTO") BookBookshopDTO newBbsDTO) {
		
		BookBookshop bbs = new BookBookshop();
		bbs.setBookBookshopId(new BookBookshopId(newBbsDTO.getBookId(), newBbsDTO.getBookshopId()));
		bbs.setBook(bookService.findBookById(newBbsDTO.getBookId()));
		bbs.setBookshop(bookshopService.findBookshopById(newBbsDTO.getBookshopId()));
		bbs.setPrice(newBbsDTO.getPrice());
		bbs.setUnits(newBbsDTO.getUnits());
		
		bookBookshopService.saveBookBookshop(bbs);
		
		return "redirect:/bookBookshops";
	}
	
	
	
}
