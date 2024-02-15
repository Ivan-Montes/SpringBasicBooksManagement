package ime.book_app.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ime.book_app.dto.BookshopDTO;
import ime.book_app.entity.Bookshop;
import ime.book_app.repository.BookshopRepository;
import ime.book_app.service.BookshopService;

@Service
public class BookshopServiceImpl implements BookshopService{

	private final BookshopRepository bookshopRepository;	
	
	public BookshopServiceImpl(BookshopRepository bookshopRepository) {
		this.bookshopRepository = bookshopRepository;
	}

	@Override
	public List<BookshopDTO> getAllBookshopDTO() {		
		return bookshopRepository.getAllBookshopDTO();
	}

	@Override
	public Bookshop findBookshopById(Long id) {
		return bookshopRepository.findById(id).orElse(new Bookshop());
	}

	@Override
	public Bookshop updateBookshop(Bookshop bookshop) {
		return bookshopRepository.save(bookshop);	
	}

	@Override
	public Bookshop saveBookshop(Bookshop bookshop) {
		return bookshopRepository.save(bookshop);	
	}

	@Override
	public void deleteBookshopById(Long id) {
		bookshopRepository.deleteById(id);
	}

}
