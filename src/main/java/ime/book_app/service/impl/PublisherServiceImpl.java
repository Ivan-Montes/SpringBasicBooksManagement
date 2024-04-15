package ime.book_app.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ime.book_app.dto.PublisherDTO;
import ime.book_app.entity.Publisher;
import ime.book_app.repository.PublisherRepository;
import ime.book_app.service.PublisherService;

@Service
public class PublisherServiceImpl implements PublisherService{

	private final PublisherRepository publisherRepository;

	public PublisherServiceImpl(PublisherRepository publisherRepository) {
		this.publisherRepository = publisherRepository;
	}

	@Override
	public List<PublisherDTO> getAllPublisherDTO() {		
		return publisherRepository.getAllPublisherDTO();
	}

	@Override
	public Publisher findPublisherById(Long id) {
		return publisherRepository.findById(id).orElse(new Publisher());
	}

	@Override
	public Publisher updatePublisher(Publisher publisher) {
		return  publisherRepository.save(publisher);		
	}

	@Override
	public Publisher savePublisher(Publisher publisher) {
		return publisherRepository.save(publisher);
	}

	@Override
	public void deletePublisherById(Long id) {
		publisherRepository.deleteById(id);
	}

	@Override
	public Page<Publisher> getAllPaged(int page, String sortField, String sortDir) {
		
		return publisherRepository.findAll(PageRequest.of(page - 1, 5,sortDir.equals("asc") ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending() ) );
	}	
	
}
