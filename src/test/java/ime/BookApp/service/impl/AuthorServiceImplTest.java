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

import ime.BookApp.repository.AuthorRepository;
import ime.BookApp.dto.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {

	@Mock
	private AuthorRepository authorRepository;
	
	@InjectMocks
	private AuthorServiceImpl authorService;
	
	@Test
	public void AuthorServiceImpl_getAllAuthorDTO_ReturnZeroOrMoreDTO() {
		List<AuthorDTO> authorDTOList = List.of(Mockito.mock(AuthorDTO.class));
		when(authorRepository.getAllAuthorsDTO()).thenReturn(authorDTOList);
		List<AuthorDTO> list = authorService.getAllAuthorDTO();
		
		Assertions.assertThat(list).isNotNull();
		Assertions.assertThat(list.size()).isEqualTo(1);
	}

}
