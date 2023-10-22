package ime.book_app.repository;


import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ime.book_app.dto.BookBookshopDTO;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class BookBookshopRepositoryTest {

	@Autowired
	private BookBookshopRepository repository;
	
	@Test
	void BookBookshopRepository_getAllBookBookshopDTO_ReturnZeroOrMoreDTO() {
		
		List<BookBookshopDTO>list = repository.getAllBookBookshopDTO();
		
		assertAll(
				()->Assertions.assertThat(list).isNotNull(),
				()->Assertions.assertThat(list).hasSizeGreaterThanOrEqualTo(0)
				);		
	}

}
