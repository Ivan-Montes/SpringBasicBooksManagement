package ime.book_app.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ime.book_app.dto.BookBookshopDTO;
import ime.book_app.entity.BookBookshop;
import ime.book_app.entity.BookBookshopId;
import ime.book_app.repository.BookBookshopRepository;
import ime.book_app.service.BookBookshopService;

@Service
public class BookBookshopServiceImpl implements BookBookshopService{
	
	private final BookBookshopRepository bookBookshopRepository;	
	
	public BookBookshopServiceImpl(BookBookshopRepository bookBookshopRepository) {
		this.bookBookshopRepository = bookBookshopRepository;
	}

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

	@Override
	public Page<BookBookshop> getAllPaged(int page, String sortField, String sortDir) {
		
		return bookBookshopRepository.findAll(PageRequest.of(page - 1, 5,sortDir.equals("asc") ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending() ) );
	}
	

}
