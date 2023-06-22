package ime.BookApp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ime.BookApp.dto.PublisherDTO;
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
	
	
}
