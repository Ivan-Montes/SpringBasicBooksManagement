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

import ime.BookApp.dto.BookshopDTO;
import ime.BookApp.repository.BookshopRepository;
import ime.BookApp.entity.Bookshop;

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
	
	@Test
	public void BookshopRepository_findBookshopById_ReturnBookshop() {
		Long bookshopId = 1L;
		Bookshop bookshop = new Bookshop();
		bookshop.setBookshopId(bookshopId);
		Optional<Bookshop> opt = Optional.of(bookshop);
		doReturn(opt).when(boookShopRepository).findById(bookshopId);
		Bookshop bookshopFound = bookShopService.findBookshopById(bookshopId);
		
		Assertions.assertThat(bookshopFound).isNotNull();
		Assertions.assertThat(bookshopFound.getBookshopId()).isEqualTo(bookshopId);
	}

	@Test
	public void BookshopRepository_updateBookshop_ReturnBookshop() {
		Long bookshopId = 1L;
		Bookshop bookshop = new Bookshop();
		bookshop.setBookshopId(bookshopId);
		doReturn(bookshop).when(boookShopRepository).save(bookshop);
		Bookshop bookshopFound = bookShopService.updateBookshop(bookshop);
		
		Assertions.assertThat(bookshopFound).isNotNull();
		Assertions.assertThat(bookshopFound.getBookshopId()).isEqualTo(bookshopId);
	}

	@Test
	public void BookshopRepository_saveBookshop_ReturnBookshop() {
		Long bookshopId = 1L;
		Bookshop bookshop = new Bookshop();
		bookshop.setBookshopId(bookshopId);
		doReturn(bookshop).when(boookShopRepository).save(bookshop);
		Bookshop bookshopFound = bookShopService.saveBookshop(bookshop);
		
		Assertions.assertThat(bookshopFound).isNotNull();
		Assertions.assertThat(bookshopFound.getBookshopId()).isEqualTo(bookshopId);
	}

	@Test
	public void BookshopRepository_deleteBookshopById_ReturnVoid() {
		Long bookshopId = 1L;
		bookShopService.deleteBookshopById(bookshopId);
	
		verify(boookShopRepository, times(1)).deleteById(bookshopId);
	}
}
