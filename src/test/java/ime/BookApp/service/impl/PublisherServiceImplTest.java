package ime.BookApp.service.impl;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import ime.BookApp.dto.PublisherDTO;
import ime.BookApp.entity.Publisher;
import ime.BookApp.repository.PublisherRepository;

@ExtendWith(MockitoExtension.class)
class PublisherServiceImplTest {

	@Mock
	private PublisherRepository publisherRepository;

	@InjectMocks
	private PublisherServiceImpl publisherService;
	
	@Test
	public void PublisherServiceImpl_getAllPublisherDTO_ReturnZeroOrMoreDTO() {
		
		List<PublisherDTO> publisherDTOList = List.of(Mockito.mock(PublisherDTO.class));
		when(publisherRepository.getAllPublisherDTO()).thenReturn(publisherDTOList);		
		List<PublisherDTO> list = publisherService.getAllPublisherDTO();
		
		Assertions.assertThat(list).isNotNull();
		Assertions.assertThat(list.size()).isEqualTo(1);
	}
	
	@Test
	public void PublisherServiceImpl_findPublisherById_ReturnPublisher() {
		Long publisherId = 1L;
		Publisher publisher = new Publisher();
		publisher.setPublisherId(publisherId);
		Optional<Publisher> opt = Optional.of(publisher);
		
		doReturn(opt).when(publisherRepository).findById(publisherId);
		Publisher publisherFound = publisherService.findPublisherById(publisherId);
		
		Assertions.assertThat(publisherFound).isNotNull();
		Assertions.assertThat(publisherFound.getPublisherId()).isEqualTo(publisherId);
		
	}	
	
	@Test
	public void PublisherServiceImpl_updatePublisher_ReturnPublisher() {
		Long publisherId = 1L;
		Publisher publisher = new Publisher();
		publisher.setPublisherId(publisherId);
		
		doReturn(publisher).when(publisherRepository).save(Mockito.any());
		Publisher publisherUpdated = publisherService.updatePublisher(publisher);
		
		Assertions.assertThat(publisherUpdated).isNotNull();
		Assertions.assertThat(publisherUpdated.getPublisherId()).isEqualTo(publisherId);
	}

	@Test
	public void PublisherServiceImpl_savePublisher_ReturnPublisher() {
		Long publisherId = 1L;
		Publisher publisher = new Publisher();
		publisher.setPublisherId(publisherId);
		
		doReturn(publisher).when(publisherRepository).save(Mockito.any());
		Publisher publisherSaved = publisherService.savePublisher(publisher);
		
		Assertions.assertThat(publisherSaved).isNotNull();
		Assertions.assertThat(publisherSaved.getPublisherId()).isEqualTo(publisherId);
	}
	
	@Test
	public void PublisherServiceImpl_deletePublisherById_ReturnVoid() {
		Long publisherId = 1L;
		publisherService.deletePublisherById(publisherId);
		
		verify(publisherRepository, times(1)).deleteById(publisherId);
	}
	
}
