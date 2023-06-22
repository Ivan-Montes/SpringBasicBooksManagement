package ime.BookApp.repository;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ime.BookApp.dto.GenreDTO;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class GenreRepositoryTest {

	@Autowired
	private GenreRepository genreRepository;
	
	@Test
	public void GenreRepository_getAllGenreDTO_ReturnZeroOrMoreDTO() {
		List<GenreDTO> genreDTOList = genreRepository.getAllGenreDTO();
		Assertions.assertThat(genreDTOList).isNotNull();
		Assertions.assertThat(genreDTOList.size()).isGreaterThanOrEqualTo(0);
	}
	

}
