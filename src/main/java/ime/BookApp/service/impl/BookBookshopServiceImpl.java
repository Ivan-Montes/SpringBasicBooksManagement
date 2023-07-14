package ime.BookApp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ime.BookApp.dto.BookBookshopDTO;
import ime.BookApp.entity.BookBookshop;
import ime.BookApp.entity.BookBookshopId;
import ime.BookApp.repository.BookBookshopRepository;
import ime.BookApp.service.BookBookshopService;
import ime.BookApp.service.BookService;
import ime.BookApp.service.BookshopService;

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

}
