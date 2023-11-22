package ime.book_app.repository;

import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ime.book_app.dto.GenreDTO;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class GenreRepositoryTest {

	@Autowired
	private GenreRepository genreRepository;
	
	@Test
	void GenreRepository_getAllGenreDTO_ReturnZeroOrMoreDTO() {
		List<GenreDTO> genreDTOList = genreRepository.getAllGenreDTO();
		assertAll(
				()->Assertions.assertThat(genreDTOList).isNotNull(),
				()->Assertions.assertThat(genreDTOList).hasSizeGreaterThanOrEqualTo(0)
				);
	}
	

}
