package ime.book_app.repository;

import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ime.book_app.dto.PublisherDTO;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class PublisherRepositoryTest {

	@Autowired
	private PublisherRepository publisherRepository;	
	
	@Test
	void PublisherRepository_getAllPublisherDTO_ReturnZeroOrMoreDTO() {
		List<PublisherDTO> publisherDTOList = publisherRepository.getAllPublisherDTO();
		assertAll(
				()->Assertions.assertThat(publisherDTOList).isNotNull(),
				()->Assertions.assertThat(publisherDTOList).hasSizeGreaterThanOrEqualTo(0)
				);	
	}

}
