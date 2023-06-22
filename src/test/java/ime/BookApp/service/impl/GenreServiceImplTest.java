package ime.BookApp.service.impl;

import static org.mockito.Mockito.when;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import ime.BookApp.dto.GenreDTO;
import ime.BookApp.repository.GenreRepository;

@ExtendWith(MockitoExtension.class)
class GenreServiceImplTest {

	@Mock
	private GenreRepository genreRepository;
	
	@InjectMocks
	private GenreServiceImpl genreServiceImpl;
	
	@Test
	public void GenreRepository_getAllGenreDTO_ReturnZeroOrMoreDTO() {
		
		List<GenreDTO> genreDTOList = List.of(Mockito.mock(GenreDTO.class));
		when(genreRepository.getAllGenreDTO()).thenReturn(genreDTOList);
		List<GenreDTO> list = genreServiceImpl.getAllGenreDTO();
		
		Assertions.assertThat(list).isNotNull();
		Assertions.assertThat(list.size()).isEqualTo(1);
	}

}
