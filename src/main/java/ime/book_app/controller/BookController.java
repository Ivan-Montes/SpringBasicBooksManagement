package ime.book_app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ime.book_app.dto.BookCreationDTO;
import ime.book_app.dto.BookNewDTO;
import ime.book_app.entity.Book;
import ime.book_app.mapper.BookMapper;
import ime.book_app.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BookController {

	private final BookService bookService;
	
	private final PublisherService publisherService;
	
	private final GenreService genreService;
	
	private final AuthorService authorService;
	
	private final BookMapper mapper;
	
	private static final String REDIRECT_BOOKS = "redirect:/books";

	@GetMapping( value = {
			"/books/{pageNum}",
			"/books"
	})
	public String getAllPaged(Model model, @PathVariable Optional<Integer> pageNum, @RequestParam( defaultValue = "bookId") String sortField, @RequestParam( defaultValue = "asc") String sortDir) {
		
		int initPageNumber = 1;
		
		if (pageNum.isPresent()) {
			initPageNumber = pageNum.get();
		}
		
		Page<Book> page = bookService.getAllPaged(initPageNumber, sortField, sortDir);
		List<Book>list = page.getContent();

		model.addAttribute("currentPage", initPageNumber);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("totalItems", page.getTotalElements());
	    model.addAttribute("books", mapper.fromListEntityToListDto(list));
	    model.addAttribute("sortField", sortField);
	    model.addAttribute("sortDir", sortDir);
	    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
	    
		return "books";
	}
	
	@GetMapping("/addBook")
	public String addBook(Model model) {
		model.addAttribute("newBook", new BookCreationDTO());
		model.addAttribute("publishers",publisherService.getAllPublisherDTO());
		model.addAttribute("genres", genreService.getAllGenreDTO());
		model.addAttribute("authors", authorService.getAllAuthorDTO());
		return "add/addBook";
	}
	
	@PostMapping("/addBook")
	public String saveBook(@Valid @ModelAttribute("newBook")BookCreationDTO newBook, BindingResult result){

		if (result.hasErrors()) {
			return "redirect:/addBook";
		}
		
		Book book = new Book();		
		book.setIsbn(newBook.getIsbn());
		book.setTitle(newBook.getTitle());
		book.setGenre(genreService.findGenreById(newBook.getGenreId()));
		book.setPublisher(publisherService.findPublisherById(newBook.getPublisherId()));
		book.setAuthors(authorService.findAllById(newBook.getAuthorId()));
		
		bookService.saveBook(book);
		
		return REDIRECT_BOOKS;
	}
	
	@GetMapping("/editBook/{id}")
	public String editBook(Model model, @PathVariable Long id) {
		Book bookBasic = bookService.findBookById(id);
		BookNewDTO book = new BookNewDTO();
		
		book.setBookId(bookBasic.getBookId());
		book.setIsbn(bookBasic.getIsbn());
		book.setTitle(bookBasic.getTitle());
		book.setGenreId(bookBasic.getGenre().getGenreId());
		book.setPublisherId(bookBasic.getPublisher().getPublisherId());
		book.setAuthorId(authorService.getAuthorDTOByBookIdWithConstructor(bookBasic.getBookId())
																								.stream()
																								.map(b->b.getAuthorId())
																								.collect(Collectors.toSet()));
		
		model.addAttribute("book", book);
		model.addAttribute("publishers",publisherService.getAllPublisherDTO());
		model.addAttribute("genres", genreService.getAllGenreDTO());
		model.addAttribute("authors", authorService.getAllAuthorDTO());
		
		return "edit/editBook";
	}
	
	@PostMapping("/updateBook/{id}")
	public String updateBook(@PathVariable Long id, @Valid @ModelAttribute("book") BookNewDTO newBook, BindingResult result) {

		if (result.hasErrors()) {
			return "redirect:/editBook/" + id;
		}
		
		Book book = bookService.findBookById(id);
		book.setIsbn(newBook.getIsbn());
		book.setTitle(newBook.getTitle());
		book.setGenre(genreService.findGenreById(newBook.getGenreId()));
		book.setPublisher(publisherService.findPublisherById(newBook.getPublisherId()));
		book.setAuthors(authorService.findAllById(newBook.getAuthorId()));
		
		bookService.updateBook(book);
		
		return REDIRECT_BOOKS;
	}
	
	@GetMapping("/deleteBook/{id}")
	public String deleteBook(Model model, @PathVariable Long id) {
		Book book = bookService.findBookById(id);
		model.addAttribute("book",book);
		return "delete/confirmDeleteBook";
	}

	
	@GetMapping("/confirmDeleteBook/{id}")
	public String confirmDeleteBook(@PathVariable Long id) {
		bookService.deleteBookById(id);
		return REDIRECT_BOOKS;
	}
	 
	 
	
}
