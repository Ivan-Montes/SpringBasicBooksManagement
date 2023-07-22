package ime.BookApp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ime.BookApp.dto.BookBookshopDTO;
import ime.BookApp.entity.BookBookshop;
import ime.BookApp.entity.BookBookshopId;

@Service
public interface BookBookshopService {

	List<BookBookshopDTO> getAllBookBookshopDTO();
	
	BookBookshop saveBookBookshop(BookBookshop bbs);
	
	BookBookshop findBookBookshopById(BookBookshopId bbsId);
	
	void deleteBookBookshop(BookBookshopId bbsId);
	
	BookBookshopDTO getBookBookshopDTOById(Long bookId, Long bookshopId);
}
