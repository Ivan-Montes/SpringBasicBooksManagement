package ime.book_app.repository;

import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ime.book_app.dto.BookDTO;
import jakarta.persistence.Tuple;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestMethodOrder(OrderAnnotation.class)
class BookRepositoryTest {

	@Autowired
	private BookRepository bookRepository;
	
	@Test
	@Order(1)
	void BookRepository_givemeListTuple_ReturnZeroOrMoreDTO() {
		List<Tuple> bookDTOList = bookRepository.givemeListTuple();
		assertAll(
				()->Assertions.assertThat(bookDTOList).isNotNull(),
				()->Assertions.assertThat(bookDTOList).hasSizeGreaterThanOrEqualTo(0)
				);
	}
	
	@Test
	@Order(2)
	void BookRepository_getAllBookDTO_ReturnZeroOrMoreDTO() {
		List<BookDTO> bookDTOList = bookRepository.getAllBookDTO();
		assertAll(
				()->Assertions.assertThat(bookDTOList).isNotNull(),
				()->Assertions.assertThat(bookDTOList).hasSizeGreaterThanOrEqualTo(0)
				);
	}
	
	
}
