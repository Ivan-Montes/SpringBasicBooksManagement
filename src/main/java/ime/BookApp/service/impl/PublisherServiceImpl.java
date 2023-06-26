package ime.BookApp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ime.BookApp.dto.PublisherDTO;
import ime.BookApp.entity.Publisher;
import ime.BookApp.repository.PublisherRepository;
import ime.BookApp.service.PublisherService;

@Service
public class PublisherServiceImpl implements PublisherService{


	@Autowired
	private PublisherRepository publisherRepository;

	@Override
	public List<PublisherDTO> getAllPublisherDTO() {		
		return publisherRepository.getAllPublisherDTO();
	}

	@Override
	public Publisher findPublisherById(Long id) {
		return publisherRepository.findById(id).orElse(new Publisher());
	}

	@Override
	public void updatePublisher(Publisher publisher) {
		publisherRepository.save(publisher);		
	}

	@Override
	public Publisher savePublisher(Publisher publisher) {
		return publisherRepository.save(publisher);
	}

	@Override
	public void deletePublisherById(Long id) {
		publisherRepository.deleteById(id);
	}
	
	
}
