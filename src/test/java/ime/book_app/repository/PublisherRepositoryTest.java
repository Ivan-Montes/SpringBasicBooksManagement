package ime.book_app.repository;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ime.book_app.dto.PublisherDTO;
import ime.book_app.repository.PublisherRepository;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class PublisherRepositoryTest {

	@Autowired
	private PublisherRepository publisherRepository;	
	
	@Test
	public void PublisherRepository_getAllPublisherDTO_ReturnZeroOrMoreDTO() {
		List<PublisherDTO> publisherDTOList = publisherRepository.getAllPublisherDTO();
		Assertions.assertThat(publisherDTOList).isNotNull();
		Assertions.assertThat(publisherDTOList.size()).isGreaterThanOrEqualTo(0);		
	}

}
