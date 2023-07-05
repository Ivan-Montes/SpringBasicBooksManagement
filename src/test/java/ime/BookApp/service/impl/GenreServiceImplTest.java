package ime.BookApp.service.impl;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import ime.BookApp.dto.GenreDTO;
import ime.BookApp.entity.Genre;
import ime.BookApp.repository.GenreRepository;

@ExtendWith(MockitoExtension.class)
class GenreServiceImplTest {

	@Mock
	private GenreRepository genreRepository;
	
	@InjectMocks
	private GenreServiceImpl genreService;
	
	@Test
	public void GenreRepository_getAllGenreDTO_ReturnZeroOrMoreDTO() {
		
		List<GenreDTO> genreDTOList = List.of(Mockito.mock(GenreDTO.class));
		when(genreRepository.getAllGenreDTO()).thenReturn(genreDTOList);
		List<GenreDTO> list = genreService.getAllGenreDTO();
		
		Assertions.assertThat(list).isNotNull();
		Assertions.assertThat(list.size()).isEqualTo(1);
	}
	
	@Test
	public void GenreRepository_findGenreById_ReturnGenre() {
		Long genreId = 1L;
		Genre genre = new Genre();
		genre.setGenreId(genreId);
		Optional<Genre>opt = Optional.of(genre);
		
		doReturn(opt).when(genreRepository).findById(genreId);
		Genre genreFound = genreService.findGenreById(genreId);
		
		Assertions.assertThat(genreFound).isNotNull();
		Assertions.assertThat(genreFound.getGenreId()).isEqualTo(genreId);
	}

	@Test
	public void GenreRepository_updateGenre_ReturnGenre() {
		
	}
	
	@Test
	public void GenreRepository_saveGenre_ReturnGenre() {
		
	}
	
	@Test
	public void GenreRepository_deleteGenreById_ReturnVoid() {
		
	}
}
