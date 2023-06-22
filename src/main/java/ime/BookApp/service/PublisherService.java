package ime.BookApp.service;
import java.util.List;

import org.springframework.stereotype.Service;

import ime.BookApp.dto.PublisherDTO;

@Service
public interface PublisherService {

	public List<PublisherDTO>getAllPublisherDTO();
	
}
