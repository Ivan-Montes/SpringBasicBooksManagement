package ime.book_app.repository;

import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ime.book_app.dto.BookshopDTO;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class BookshopRepositoryTest {

	@Autowired
	private BookshopRepository bookshopRepository;
	
	@Test
	void BookshopRepository_getAllBookshopDTO_ReturnZeroOrMoreDTO() {
		List<BookshopDTO>bookshopDTOList = bookshopRepository.getAllBookshopDTO();
		assertAll(
				()->Assertions.assertThat(bookshopDTOList).isNotNull(),
				()->Assertions.assertThat(bookshopDTOList).hasSizeGreaterThanOrEqualTo(0)
				);
	}

}
