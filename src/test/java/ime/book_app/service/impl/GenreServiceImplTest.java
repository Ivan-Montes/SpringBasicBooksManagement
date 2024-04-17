package ime.book_app.service.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import ime.book_app.dto.GenreDTO;
import ime.book_app.entity.Genre;
import ime.book_app.repository.GenreRepository;

@ExtendWith(MockitoExtension.class)
class GenreServiceImplTest {

	@Mock
	private GenreRepository genreRepository;
	
	@InjectMocks
	private GenreServiceImpl genreService;
	
	private Genre genre;
	private final Long genreId = 1L;
	
	@BeforeEach
	private void createObjects() {
		
		genre = new Genre();
		genre.setGenreId(genreId);

	}
	
	@Test
	void GenreServiceImpl_getAllGenreDTO_ReturnZeroOrMoreDTO() {
		
		List<GenreDTO> genreDTOList = List.of(Mockito.mock(GenreDTO.class));
		when(genreRepository.getAllGenreDTO()).thenReturn(genreDTOList);
		List<GenreDTO> list = genreService.getAllGenreDTO();
		
		assertAll(
				()->Assertions.assertThat(list).isNotNull(),
				()->Assertions.assertThat(list).hasSize(1)
				);
	}
	
	@Test
	void GenreServiceImpl_findGenreById_ReturnGenre() {		
		
		Optional<Genre>opt = Optional.of(genre);
		
		doReturn(opt).when(genreRepository).findById(genreId);
		Genre genreFound = genreService.findGenreById(genreId);
		
		assertAll(
				()->Assertions.assertThat(genreFound).isNotNull(),
				()->Assertions.assertThat(genreFound.getGenreId()).isEqualTo(genreId)
				);
	}

	@Test
	void GenreServiceImpl_updateGenre_ReturnGenre() {
		
		doReturn(genre).when(genreRepository).save(genre);
		Genre genreFound = genreService.updateGenre(genre);
		
		assertAll(
				()->Assertions.assertThat(genreFound).isNotNull(),
				()->Assertions.assertThat(genreFound.getGenreId()).isEqualTo(genreId)
				);
	}
	
	@Test
	void GenreServiceImpl_saveGenre_ReturnGenre() {
				
		doReturn(genre).when(genreRepository).save(genre);
		Genre genreFound = genreService.saveGenre(genre);
		
		assertAll(
				()->Assertions.assertThat(genreFound).isNotNull(),
				()->Assertions.assertThat(genreFound.getGenreId()).isEqualTo(genreId)
				);
	}
	
	@Test
	void GenreServiceImpl_deleteGenreById_ReturnVoid() {
		
		genreService.deleteGenreById(genreId);
		
		verify(genreRepository,times(1)).deleteById(genreId);
	}
	
	@Test
	void GenreServiceImpl_getAllPaged_ReturnPageAsc() {
		
		Page<Genre>page = Page.empty();
		when(genreRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(page);
		
		Page<Genre>pageGenreResult = genreService.getAllPaged(1, "genreId", "asc");
		
		assertAll(
				()->Assertions.assertThat(pageGenreResult).isNotNull(),
				()->Assertions.assertThat(pageGenreResult.isEmpty())
				);		
	}

	@Test
	void GenreServiceImpl_getAllPaged_ReturnPageDesc() {
		
		Page<Genre>page = Page.empty();
		when(genreRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(page);
		
		Page<Genre>pageGenreResult = genreService.getAllPaged(1, "genreId", "d");
		
		assertAll(
				()->Assertions.assertThat(pageGenreResult).isNotNull(),
				()->Assertions.assertThat(pageGenreResult.isEmpty())
				);		
	}
	
}
