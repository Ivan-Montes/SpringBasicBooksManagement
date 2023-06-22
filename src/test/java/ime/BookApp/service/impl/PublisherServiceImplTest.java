package ime.BookApp.service.impl;

import static org.mockito.Mockito.when;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import ime.BookApp.dto.PublisherDTO;
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
	
}
