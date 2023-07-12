package ime.BookApp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ime.BookApp.dto.BookBookshopDTO;

@Service
public interface BookBookshopService {

	public List<BookBookshopDTO> getAllBookBookshopDTO();
}
