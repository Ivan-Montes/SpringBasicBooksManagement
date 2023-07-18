package ime.BookApp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ime.BookApp.dto.BookBookshopDTO;
import ime.BookApp.entity.BookBookshop;
import ime.BookApp.entity.BookBookshopId;
import ime.BookApp.repository.BookBookshopRepository;
import ime.BookApp.service.BookBookshopService;

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
