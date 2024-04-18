package ime.book_app.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import ime.book_app.dto.BookBookshopDTO;
import ime.book_app.entity.Book;
import ime.book_app.entity.BookBookshop;
import ime.book_app.entity.Bookshop;

@ExtendWith(MockitoExtension.class)
class BookBookshopMapperTest {

	@InjectMocks
	private BookBookshopMapper bookBookshopMapper;
	
	private List<BookBookshop> listBbs;
	private BookBookshop bbs;
	private Book book;
	private Bookshop bookshop;
	private final Long bookId = 6L;
	private final Long bookshopId = 63L;
	private final String name = "Small corner shop";
	private final String title = "Grimorium Child edition";
	
	@BeforeEach
	private void createObjects() {
		
		listBbs = new ArrayList<>();
		bookshop = new Bookshop();
		bookshop.setBookshopId(bookshopId);
		bookshop.setName(name);
		book = new Book();
		book.setBookId(bookId);
		book.setIsbn(null);
		book.setTitle(title);
		bbs = new BookBookshop();
		bbs.setBook(book);
		bbs.setBookshop(bookshop);
		bbs.setPrice(3D);
		bbs.setUnits(11);
	}
	
	@Test
	void BookBookshopMapper_fromListEntityToListDto_ReturnListDto() {
		
		listBbs.add(bbs);
		List<BookBookshopDTO> list = bookBookshopMapper.fromListEntityToListDto(listBbs);

		assertAll(
				()->Assertions.assertThat(list).isNotNull(),
				()->Assertions.assertThat(list).hasSize(1),
				()->Assertions.assertThat(list.parallelStream().anyMatch( a -> a.getBookId().equals(bookId)))
				);
	}

}
