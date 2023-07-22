package ime.BookApp.service;
import java.util.List;

import org.springframework.stereotype.Service;

import ime.BookApp.dto.PublisherDTO;
import ime.BookApp.entity.Publisher;

@Service
public interface PublisherService {

	List<PublisherDTO>getAllPublisherDTO();

	Publisher findPublisherById(Long id);
	
	Publisher updatePublisher(Publisher publisher);
	
	Publisher savePublisher(Publisher publisher);
	
	void deletePublisherById(Long id);
}
