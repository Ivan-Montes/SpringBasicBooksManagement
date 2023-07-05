package ime.BookApp.service;
import java.util.List;

import org.springframework.stereotype.Service;

import ime.BookApp.dto.PublisherDTO;
import ime.BookApp.entity.Publisher;

@Service
public interface PublisherService {

	public List<PublisherDTO>getAllPublisherDTO();

	public Publisher findPublisherById(Long id);
	
	public Publisher updatePublisher(Publisher publisher);
	
	public Publisher savePublisher(Publisher publisher);
	
	public void deletePublisherById(Long id);
}
