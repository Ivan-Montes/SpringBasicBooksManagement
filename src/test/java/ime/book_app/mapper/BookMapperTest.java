package ime.book_app.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import ime.book_app.dto.BookDTO;
import ime.book_app.entity.Book;
import ime.book_app.entity.Genre;
import ime.book_app.entity.Publisher;

@ExtendWith(MockitoExtension.class)
class BookMapperTest {

	@Mock
	private AuthorMapper authorMapper;
	
	@InjectMocks
	private BookMapper bookMapper;
	
	private List<Book>listBook;
	private Book book;
	private Publisher publisher;
	private Genre genre;
	private final Long bookId = 6L;
	private final Long publisherId = 9L;
	private final Long genreId = 31L;
	private final String publisherName = "Terrorific Pictures";
	private final String genreName = "Terror";

	@BeforeEach
	private void createObjects() {
		
		listBook = new ArrayList<>();
		publisher = new Publisher();
		publisher.setPublisherId(publisherId);
		publisher.setName(publisherName);
		genre = new Genre();
		genre.setGenreId(genreId);
		genre.setName(genreName);
		book = new Book();
		book.setBookId(bookId);
		book.setPublisher(publisher);
		book.setGenre(genre);
	}
	
	@Test
	void BookMapper_fromListEntityToListDto_ReturnListDto() {
		
		listBook.add(book);
		Mockito.when(authorMapper.fromSetEntityToListDto(Mockito.anySet())).thenReturn(Collections.emptySet());
		List<BookDTO> list = bookMapper.fromListEntityToListDto(listBook);

		assertAll(
				()->Assertions.assertThat(list).isNotNull(),
				()->Assertions.assertThat(list).hasSize(1),
				()->Assertions.assertThat(list.parallelStream().anyMatch( a -> a.getBookId().equals(bookId)))
				);
	}

}
