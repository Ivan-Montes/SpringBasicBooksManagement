package ime.BookApp.service.impl;

import org.assertj.core.api.Assertions;
import static org.mockito.Mockito.doReturn;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import ime.BookApp.repository.BookBookshopRepository;
import ime.BookApp.dto.BookBookshopDTO;

@ExtendWith(MockitoExtension.class)
class BookBookshopServiceImplTest {

	@Mock
	private BookBookshopRepository repository;
	
	@InjectMocks
	private BookBookshopServiceImpl bookService;
	
	@Test
	void BookBookshopServiceImpl_getAllBookBookshopDTO_ReturnZeroOrMoreDTO() {
		List<BookBookshopDTO>list = List.of(Mockito.mock(BookBookshopDTO.class));
		doReturn(list).when(repository).getAllBookBookshopDTO();
		
		List<BookBookshopDTO>listFound = bookService.getAllBookBookshopDTO();
		
		Assertions.assertThat(listFound).isNotNull();
		Assertions.assertThat(listFound.size()).isEqualTo(1);
	}
	
	

}
