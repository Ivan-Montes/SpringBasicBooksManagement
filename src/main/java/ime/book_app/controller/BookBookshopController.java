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

import ime.book_app.dto.*;
import ime.book_app.entity.BookBookshop;
import ime.book_app.entity.BookBookshopId;
import ime.book_app.mapper.BookBookshopMapper;
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
	
	private final BookBookshopMapper mapper;
	
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
	
	@GetMapping( value = {
			"/bookBookshops/{pageNum}",
			"/bookBookshops"
	})
public String getAllPaged(Model model, @PathVariable Optional<Integer> pageNum, @RequestParam( defaultValue = "bookBookshopId") String sortField, @RequestParam( defaultValue = "asc") String sortDir) {
		
		int initPageNumber = 1;
		
		if (pageNum.isPresent()) {
			initPageNumber = pageNum.get();
		}
		
		Page<BookBookshop> page = bookBookshopService.getAllPaged(initPageNumber, sortField, sortDir);
		List<BookBookshop>list = page.getContent();

		model.addAttribute("currentPage", initPageNumber);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("totalItems", page.getTotalElements());
	    model.addAttribute("bookBookshops", mapper.fromListEntityToListDto(list));
	    model.addAttribute("sortField", sortField);
	    model.addAttribute("sortDir", sortDir);
	    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
	    
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
