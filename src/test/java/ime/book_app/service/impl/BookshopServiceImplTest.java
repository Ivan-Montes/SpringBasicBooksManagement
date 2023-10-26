package ime.book_app.service.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
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

import ime.book_app.dto.BookshopDTO;
import ime.book_app.entity.Bookshop;
import ime.book_app.repository.BookshopRepository;

@ExtendWith(MockitoExtension.class)
class BookshopServiceImplTest {

	@Mock
	private BookshopRepository boookShopRepository;
	
	@InjectMocks
	private BookshopServiceImpl bookShopService;
	
	@Test
	void BookshopServiceImpl_getAllBookshopDTO_ReturnZeroOrMoreDTO() {
		
		List<BookshopDTO> bookDTOList = List.of(Mockito.mock(BookshopDTO.class));
		when(boookShopRepository.getAllBookshopDTO()).thenReturn(bookDTOList);
		List<BookshopDTO> list = bookShopService.getAllBookshopDTO();
		
		assertAll(
				()->Assertions.assertThat(list).isNotNull(),
				()->Assertions.assertThat(list).hasSize(1)
				);
	}
	
	@Test
	void BookshopServiceImpl_findBookshopById_ReturnBookshop() {
		Long bookshopId = 1L;
		Bookshop bookshop = new Bookshop();
		bookshop.setBookshopId(bookshopId);
		Optional<Bookshop> opt = Optional.of(bookshop);
		doReturn(opt).when(boookShopRepository).findById(bookshopId);
		Bookshop bookshopFound = bookShopService.findBookshopById(bookshopId);
		
		assertAll(
				()->Assertions.assertThat(bookshopFound).isNotNull(),
				()->Assertions.assertThat(bookshopFound.getBookshopId()).isEqualTo(bookshopId)
				);
	}

	@Test
	void BookshopServiceImpl_updateBookshop_ReturnBookshop() {
		Long bookshopId = 1L;
		Bookshop bookshop = new Bookshop();
		bookshop.setBookshopId(bookshopId);
		doReturn(bookshop).when(boookShopRepository).save(bookshop);
		Bookshop bookshopFound = bookShopService.updateBookshop(bookshop);
		
		assertAll(
				()->Assertions.assertThat(bookshopFound).isNotNull(),
				()->Assertions.assertThat(bookshopFound.getBookshopId()).isEqualTo(bookshopId)
				);
	}

	@Test
	void BookshopServiceImpl_saveBookshop_ReturnBookshop() {
		Long bookshopId = 1L;
		Bookshop bookshop = new Bookshop();
		bookshop.setBookshopId(bookshopId);
		doReturn(bookshop).when(boookShopRepository).save(bookshop);
		Bookshop bookshopFound = bookShopService.saveBookshop(bookshop);
		
		assertAll(
				()->Assertions.assertThat(bookshopFound).isNotNull(),
				()->Assertions.assertThat(bookshopFound.getBookshopId()).isEqualTo(bookshopId)
				);
	}

	@Test
	void BookshopServiceImpl_deleteBookshopById_ReturnVoid() {
		Long bookshopId = 1L;
		bookShopService.deleteBookshopById(bookshopId);
	
		verify(boookShopRepository, times(1)).deleteById(bookshopId);
	}
}
