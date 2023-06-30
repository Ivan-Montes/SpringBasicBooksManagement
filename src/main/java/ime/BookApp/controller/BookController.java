package ime.BookApp.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ime.BookApp.dto.BookNewDTO;
import ime.BookApp.entity.Book;
import ime.BookApp.service.*;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;
	@Autowired
	private PublisherService publisherService;
	@Autowired
	private GenreService genreService;
	@Autowired
	private AuthorService authorService;
	
	@GetMapping("/books")
	public String getAllBookDTO(Model model) {
		model.addAttribute("books", bookService.getAllBookDTO());
		return "books";
	}	
	
	@GetMapping("/addBook")
	public String addBook(Model model) {
		model.addAttribute("newBook", new BookNewDTO());
		model.addAttribute("publishers",publisherService.getAllPublisherDTO());
		model.addAttribute("genres", genreService.getAllGenreDTO());
		model.addAttribute("authors", authorService.getAllAuthorDTO());
		return "/add/addBook";
	}
	
	@PostMapping("/addBook")
	public String saveBook(@ModelAttribute("newBook")BookNewDTO newBook){
		
		Book book = new Book();		
		book.setIsbn(newBook.getIsbn());
		book.setTitle(newBook.getTitle());
		book.setGenre(genreService.findGenreById(newBook.getGenreId()));
		book.setPublisher(publisherService.findPublisherById(newBook.getPublisherId()));
		book.setAuthors(authorService.findAllById(newBook.getAuthorId()));
		
		bookService.saveBook(book);
		
		return "redirect:/books";
	}
	
	@GetMapping("/editBook/{id}")
	public String editBook(Model model, @PathVariable Long id) {
		model.addAttribute("book", bookService.findBookById(id));
		return "/edit/editBook";
	}
	
	@PostMapping("/updateBook/{id}")
	public String updateBook(@PathVariable Long id, @ModelAttribute("newBook") Book newBook) {
		Book book = bookService.findBookById(id);
		book.setIsbn(newBook.getIsbn());
		book.setTitle(newBook.getTitle());
		bookService.updateBook(book);
		
		return "redirect:/books";
	}
	
	@GetMapping("/deleteBook/{id}")
	public String deleteBook(Model model, @PathVariable Long id) {
		Book book = bookService.findBookById(id);
		model.addAttribute("book",book);
		return "/delete/confirmDeleteBook";
	}

	
	@GetMapping("/confirmDeleteBook/{id}")
	public String confirmDeleteBook(@PathVariable Long id) {
		bookService.deleteBookById(id);
		return "redirect:/books";
	}
	 
	 
	
}
