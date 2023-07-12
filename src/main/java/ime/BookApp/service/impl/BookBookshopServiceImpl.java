package ime.BookApp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ime.BookApp.dto.BookBookshopDTO;
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

}
