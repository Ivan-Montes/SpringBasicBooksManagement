package ime.book_app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ime.book_app.dto.BookBookshopDTO;
import ime.book_app.entity.BookBookshop;
import ime.book_app.entity.BookBookshopId;

@Service
public interface BookBookshopService {

	List<BookBookshopDTO> getAllBookBookshopDTO();
	
	BookBookshop saveBookBookshop(BookBookshop bbs);
	
	BookBookshop findBookBookshopById(BookBookshopId bbsId);
	
	void deleteBookBookshop(BookBookshopId bbsId);
	
	BookBookshopDTO getBookBookshopDTOById(Long bookId, Long bookshopId);
}
