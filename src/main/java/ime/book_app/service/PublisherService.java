package ime.book_app.service;
import java.util.List;

import org.springframework.stereotype.Service;

import ime.book_app.dto.PublisherDTO;
import ime.book_app.entity.Publisher;
import org.springframework.data.domain.Page;

@Service
public interface PublisherService {

	List<PublisherDTO>getAllPublisherDTO();

	Publisher findPublisherById(Long id);
	
	Publisher updatePublisher(Publisher publisher);
	
	Publisher savePublisher(Publisher publisher);
	
	void deletePublisherById(Long id);
	
	Page<Publisher>getAllPaged(int page, String sortField, String sortDir);
}
