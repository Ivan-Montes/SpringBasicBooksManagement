package ime.BookApp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ime.BookApp.dto.BookshopDTO;
import ime.BookApp.repository.BookshopRepository;
import ime.BookApp.service.BookshopService;

@Service
public class BookshopServiceImpl implements BookshopService{

	@Autowired
	private BookshopRepository bookshopRepository;
	
	@Override
	public List<BookshopDTO> getAllBookshopDTO() {		
		return bookshopRepository.getAllBookshopDTO();
	}

}
