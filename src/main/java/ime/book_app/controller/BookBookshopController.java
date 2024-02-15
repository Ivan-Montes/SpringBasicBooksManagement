package ime.book_app.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ime.book_app.dto.*;
import ime.book_app.entity.BookBookshop;
import ime.book_app.entity.BookBookshopId;
import ime.book_app.service.BookBookshopService;
import ime.book_app.service.BookService;
import ime.book_app.service.BookshopService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BookBookshopController {

	private final BookBookshopService bookBookshopService;
	
	private final BookService bookService;

	private final BookshopService bookshopService;
	
	@ModelAttribute("allBookshopDTO")
	public List<BookshopDTO> prepareAllBookshopDTO(){
		return bookshopService.getAllBookshopDTO();
	}
	
	@ModelAttribute("allBookDTO")
	public List<BookDTO>prepareAllBookDTO(){
		return bookService.getAllBookDTO();
	}
	
	@ModelAttribute("newBbsDTO")
	public BookBookshopDTO prepareBookBookshopDTO() {
		return new BookBookshopDTO();
	}
	
	private static final String REDIRECT_BOOKBOOKSHOPS = "redirect:/bookBookshops";
	
	@GetMapping("/bookBookshops")
	public String getAllBookBookshopDTO(Model model){
		model.addAttribute("bookBookshops", bookBookshopService.getAllBookBookshopDTO());
		return "bookBookshops";
	}
	
	@GetMapping("/addBookBookshop")
	public String addBookBookshop(Model model) {
		return "add/addBookBookshop";
	}
	
	@PostMapping("/addBookBookshop")
	public String saveBookBookshop(@Valid @ModelAttribute("newBbsDTO") BookBookshopDTO newBbsDTO, BindingResult result) {
		
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
		
		return REDIRECT_BOOKBOOKSHOPS;
	}
	
	@GetMapping("/deleteBookBookshop")
	public String deleteBookBookshop(@RequestParam("bookId")Long bookId, @RequestParam("bookshopId")Long bookshopId, Model model) {		
		
		model.addAttribute("thebbs", bookBookshopService.findBookBookshopById(new BookBookshopId(bookId,bookshopId)));
		
		return "delete/confirmDeleteBookBookshop";
	}
	
	@GetMapping("/confirmDeleteBookBookshop")
	public String confirmDeleteBookBookshop(@RequestParam("bookId")Long bookId, @RequestParam("bookshopId")Long bookshopId) {
		
		bookBookshopService.deleteBookBookshop(new BookBookshopId(bookId,bookshopId));
		
		return REDIRECT_BOOKBOOKSHOPS;
		
	}
	
	@GetMapping("/editBookBookshop")
	public String editBookBookshop(@RequestParam("bookId")Long bookId, @RequestParam("bookshopId")Long bookshopId, Model model) {
		model.addAttribute("thebbs", bookBookshopService.getBookBookshopDTOById(bookId, bookshopId));
		return "edit/editBookBookshop";
	}
	
	@PostMapping("/updateBookBookshop")
	public String updateBookshop(@Valid @ModelAttribute("newBbsDTO") BookBookshopDTO newBbsDTO, BindingResult result) {
		
		if (result.hasErrors()) {
			return new StringBuffer("/editBookBookshop?")
					.append("bookshopId=").append(newBbsDTO.getBookshopId().toString())
					.append("&")
					.append("bookId=").append(newBbsDTO.getBookId())
					.toString();
		}
		
		BookBookshop bbs = bookBookshopService.findBookBookshopById(new BookBookshopId(newBbsDTO.getBookId(),newBbsDTO.getBookshopId()));
		bbs.setPrice(newBbsDTO.getPrice());
		bbs.setUnits(newBbsDTO.getUnits());
		
		bookBookshopService.saveBookBookshop(bbs);
		return REDIRECT_BOOKBOOKSHOPS;
	}
}
