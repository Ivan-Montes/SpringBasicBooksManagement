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

import ime.BookApp.dto.BookshopDTO;
import ime.BookApp.repository.BookshopRepository;

@ExtendWith(MockitoExtension.class)
class BookshopServiceImplTest {

	@Mock
	private BookshopRepository boookShopRepository;
	
	@InjectMocks
	private BookshopServiceImpl bookShopService;
	
	@Test
	public void BookshopRepository_getAllBookshopDTO_ReturnZeroOrMoreDTO() {
		
		List<BookshopDTO> bookDTOList = List.of(Mockito.mock(BookshopDTO.class));
		when(boookShopRepository.getAllBookshopDTO()).thenReturn(bookDTOList);
		List<BookshopDTO> list = bookShopService.getAllBookshopDTO();
		
		Assertions.assertThat(list).isNotNull();
		Assertions.assertThat(list.size()).isEqualTo(1);
	}
	
}
