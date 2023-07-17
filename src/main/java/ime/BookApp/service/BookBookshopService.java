package ime.BookApp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ime.BookApp.dto.BookBookshopDTO;
import ime.BookApp.entity.BookBookshop;
import ime.BookApp.entity.BookBookshopId;

@Service
public interface BookBookshopService {

	public List<BookBookshopDTO> getAllBookBookshopDTO();
	
	public BookBookshop saveBookBookshop(BookBookshop bbs);
	
	public BookBookshop findBookBookshopById(BookBookshopId bbsId);
	
	public void deleteBookBookshop(BookBookshopId bbsId);
}
