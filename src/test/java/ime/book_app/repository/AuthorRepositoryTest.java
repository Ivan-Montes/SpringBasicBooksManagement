package ime.book_app.repository;

import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ime.book_app.dto.AuthorDTO;
import jakarta.persistence.Tuple;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestMethodOrder(OrderAnnotation.class)
class AuthorRepositoryTest {

	@Autowired
	private AuthorRepository authorRepository;
	
	@Test
	@Order(1)
	void AuthorRepository_getAllAuthorsDTO_ReturnZeroOrMoreDTO() {		
		List<AuthorDTO> authorDTOList = authorRepository.getAllAuthorsDTO();
		assertAll(
				()->Assertions.assertThat(authorDTOList).isNotNull(),
				()->Assertions.assertThat(authorDTOList).hasSizeGreaterThanOrEqualTo(0)
				);		
	}
	
	@Test
	@Order(2)
	void AuthorRepository_getAuthorDTOByBookIdWithConstructor_ReturnZeroOrMoreDTO() {		
		List<AuthorDTO> authorDTOList = authorRepository.getAuthorDTOByBookIdWithConstructor(1L);
		assertAll(
				()->Assertions.assertThat(authorDTOList).isNotNull(),
				()->Assertions.assertThat(authorDTOList).hasSizeGreaterThanOrEqualTo(0)
				);
	}
	
	@Test
	@Order(3)
	void AuthorRepository_getAuthorsByBookIdWithTuples_ReturnZeroOrMoreDTO() {		
		List<Tuple> authorDTOList = authorRepository.getAuthorsByBookIdWithTuples(1L);
		assertAll(
				()->Assertions.assertThat(authorDTOList).isNotNull(),
				()->Assertions.assertThat(authorDTOList).hasSizeGreaterThanOrEqualTo(0)
				);
	}

}
