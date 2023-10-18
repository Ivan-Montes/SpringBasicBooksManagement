package ime.book_app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ime.book_app.dto.BookBookshopDTO;
import ime.book_app.entity.BookBookshop;
import ime.book_app.entity.BookBookshopId;
import ime.book_app.repository.BookBookshopRepository;
import ime.book_app.service.BookBookshopService;

@Service
public class BookBookshopServiceImpl implements BookBookshopService{

	@Autowired
	private BookBookshopRepository bookBookshopRepository;
	
	
	@Override
	public List<BookBookshopDTO> getAllBookBookshopDTO() {
		return bookBookshopRepository.getAllBookBookshopDTO();
	}

	@Override
	public BookBookshop saveBookBookshop(BookBookshop bbs) {		
		return bookBookshopRepository.save(bbs);
	}

	@Override
	public BookBookshop findBookBookshopById(BookBookshopId bbsId) {
		return bookBookshopRepository.findById(bbsId).orElse(new BookBookshop());
	}

	@Override
	public void deleteBookBookshop(BookBookshopId bbsId) {
		bookBookshopRepository.deleteById(bbsId);		
	}

	@Override
	public BookBookshopDTO getBookBookshopDTOById(Long bookId, Long bookshopId) {		
		return bookBookshopRepository.getBookBookshopDTOById(bookId, bookshopId);
	}
	

}
