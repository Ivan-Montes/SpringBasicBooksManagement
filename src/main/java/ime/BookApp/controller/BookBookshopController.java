package ime.BookApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ime.BookApp.service.BookBookshopService;
import ime.BookApp.service.BookService;
import ime.BookApp.service.BookshopService;
import jakarta.validation.Valid;
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
	
	@ModelAttribute("allBookshopDTO")
	private List<BookshopDTO> prepareAllBookshopDTO(){
		return bookshopService.getAllBookshopDTO();
	}
	
	@ModelAttribute("allBookDTO")
	private List<BookDTO>prepareAllBookDTO(){
		return bookService.getAllBookDTO();
	}
	
	@ModelAttribute("newBbsDTO")
	private BookBookshopDTO prepareBookBookshopDTO() {
		return new BookBookshopDTO();
	}
	
	@GetMapping("/bookBookshops")
	private String getAllBookBookshopDTO(Model model){
		model.addAttribute("bookbookshops", bookBookshopService.getAllBookBookshopDTO());
		return "bookBookshops";
	}
	
	@GetMapping("/addBookBookshop")
	private String addBookBookshop(Model model) {
		return "add/addBookBookshop";
	}
	
	@PostMapping("/addBookBookshop")
	private String saveBookBookshop(@Valid @ModelAttribute("newBbsDTO") BookBookshopDTO newBbsDTO, BindingResult result) {
		
		if (result.hasErrors()) {
		    return "add/addBookBookshop";
		  }
		
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
