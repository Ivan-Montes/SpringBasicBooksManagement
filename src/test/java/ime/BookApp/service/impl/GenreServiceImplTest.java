package ime.BookApp.service.impl;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
	public void GenreServiceImpl_getAllGenreDTO_ReturnZeroOrMoreDTO() {
		
		List<GenreDTO> genreDTOList = List.of(Mockito.mock(GenreDTO.class));
		when(genreRepository.getAllGenreDTO()).thenReturn(genreDTOList);
		List<GenreDTO> list = genreService.getAllGenreDTO();
		
		Assertions.assertThat(list).isNotNull();
		Assertions.assertThat(list.size()).isEqualTo(1);
	}
	
	@Test
	public void GenreServiceImpl_findGenreById_ReturnGenre() {
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
	public void GenreServiceImpl_updateGenre_ReturnGenre() {
		Long genreId = 1L;
		Genre genre = new Genre();
		genre.setGenreId(genreId);
		
		doReturn(genre).when(genreRepository).save(genre);
		Genre genreFound = genreService.updateGenre(genre);
		
		Assertions.assertThat(genreFound).isNotNull();
		Assertions.assertThat(genreFound.getGenreId()).isEqualTo(genreId);
	}
	
	@Test
	public void GenreServiceImpl_saveGenre_ReturnGenre() {
		Long genreId = 1L;
		Genre genre = new Genre();
		genre.setGenreId(genreId);
		
		doReturn(genre).when(genreRepository).save(genre);
		Genre genreFound = genreService.saveGenre(genre);
		
		Assertions.assertThat(genreFound).isNotNull();
		Assertions.assertThat(genreFound.getGenreId()).isEqualTo(genreId);
	}
	
	@Test
	public void GenreServiceImpl_deleteGenreById_ReturnVoid() {
		Long genreId = 1L;
		
		genreService.deleteGenreById(genreId);
		
		verify(genreRepository,times(1)).deleteById(genreId);
	}
}
