package ime.BookApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ime.BookApp.service.*;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;
	
	@GetMapping("/books")
	public String getAllBookDTO(Model model) {
		model.addAttribute("books", bookService.getAllBookDTO());
		return "books";
	}
	
}
