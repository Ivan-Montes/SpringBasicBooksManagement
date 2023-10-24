package ime.book_app.service.impl;

import org.assertj.core.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import ime.book_app.dto.BookBookshopDTO;
import ime.book_app.entity.BookBookshop;
import ime.book_app.entity.BookBookshopId;
import ime.book_app.repository.BookBookshopRepository;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class BookBookshopServiceImplTest {

	@Mock
	private BookBookshopRepository repository;
	
	@InjectMocks
	private BookBookshopServiceImpl bookService;
	
	private BookBookshopId bookBookshopIdTest;
	
	@BeforeEach
	private void createObjects() {
	
		bookBookshopIdTest = new BookBookshopId(1L,1L);
		
	}
	
	@Test
	void BookBookshopServiceImpl_getAllBookBookshopDTO_ReturnZeroOrMoreDTO() {
		List<BookBookshopDTO>list = List.of(Mockito.mock(BookBookshopDTO.class));
		doReturn(list).when(repository).getAllBookBookshopDTO();
		
		List<BookBookshopDTO>listFound = bookService.getAllBookBookshopDTO();
		
		assertAll(
				()->Assertions.assertThat(listFound).isNotNull(),
				()->Assertions.assertThat(listFound).hasSize(1)
				);
	}
	
	@Test
	void BookBookshopServiceImpl_saveBookBookshop_ReturnBookBookshop(){
		BookBookshop bbs = Mockito.mock(BookBookshop.class);
		doReturn(bbs).when(repository).save(Mockito.any(BookBookshop.class));
		
		BookBookshop bbsResult = bookService.saveBookBookshop(bbs);
		
		assertAll(
				()->Assertions.assertThat(bbsResult).isNotNull(),
				()->Assertions.assertThat(bbsResult).isEqualTo(bbs)
				);
	}

	@Test
	void BookBookshopServiceImpl_findBookBookshopById_ReturnBookBookshop() {
		BookBookshop bbs = Mockito.mock(BookBookshop.class);
		doReturn(Optional.of(bbs)).when(repository).findById(Mockito.any(BookBookshopId.class));
		
		BookBookshop bbsResult = bookService.findBookBookshopById(bookBookshopIdTest);
		
		assertAll(
				()->Assertions.assertThat(bbsResult).isNotNull(),
				()->Assertions.assertThat(bbsResult).isEqualTo(bbs)
				);		
	}
	
	@Test
	void BookBookshopServiceImpl_findBookBookshopById_ReturnBookBookshopVoid() {
		
		doReturn(Optional.empty()).when(repository).findById(Mockito.any(BookBookshopId.class));
		
		BookBookshop bbsResult = bookService.findBookBookshopById(bookBookshopIdTest);
		
		assertAll(
				()->Assertions.assertThat(bbsResult).isNotNull()
				);		
	}
	
	@Test
	void BookBookshopServiceImpl_deleteBookBookshop_ReturnNothing() {
		
		doNothing().when(repository).deleteById(Mockito.any(BookBookshopId.class));
		
		bookService.deleteBookBookshop(bookBookshopIdTest);
		
		verify(repository, times(1)).deleteById(bookBookshopIdTest);
	}
	
	@Test
	void BookBookshopServiceImpl_getBookBookshopDTOById_ReturnBookBookshopDTO() {
		BookBookshopDTO bookBookshopDTO = Mockito.mock(BookBookshopDTO.class);
		doReturn(bookBookshopDTO).when(repository).getBookBookshopDTOById(Mockito.anyLong(), Mockito.anyLong());
		
		BookBookshopDTO bookBookshopDTOfound = bookService.getBookBookshopDTOById(Mockito.anyLong(), Mockito.anyLong());
		
		assertAll(
				()->Assertions.assertThat(bookBookshopDTOfound).isNotNull(),
				()->Assertions.assertThat(bookBookshopDTOfound).isEqualTo(bookBookshopDTO)
				);
	}
}
