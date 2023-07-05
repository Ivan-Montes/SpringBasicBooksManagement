package ime.BookApp.service.impl;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import ime.BookApp.repository.AuthorRepository;
import ime.BookApp.dto.*;
import ime.BookApp.entity.Author;

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
	
	@Test
	public void AuthorServiceImpl_findAuthorById_ReturnAuthor() {		
		Long authorId = 1L;
		Author author = new Author();
		author.setAuthorId(authorId);
		Optional<Author> opt = Optional.of(author);
		doReturn(opt).when(authorRepository).findById(authorId);
		Author authorFound = authorService.findAuthorById(authorId);
		
		Assertions.assertThat(authorFound).isNotNull();
		Assertions.assertThat(authorFound.getAuthorId()).isEqualTo(authorId);
	}
	
	@Test
	public void AuthorServiceImpl_updateAuthor_ReturnVoid() {
		Long authorId = 1L;
		Author author = new Author();
		author.setAuthorId(authorId);
		doReturn(author).when(authorRepository).save(Mockito.any());
		Author authorUpdated = authorService.updateAuthor(author);
		
		Assertions.assertThat(authorUpdated).isNotNull();
		Assertions.assertThat(authorUpdated.getAuthorId()).isEqualTo(authorId);
	}
	
	
	@Test
	public void AuthorServiceImpl_saveAuthor_ReturnAuthor() {
		Long authorId = 1L;
		Author author = new Author();
		author.setAuthorId(authorId);
		doReturn(author).when(authorRepository).save(Mockito.any());
		Author authorSaved = authorService.updateAuthor(author);
		
		Assertions.assertThat(authorSaved).isNotNull();
		Assertions.assertThat(authorSaved.getAuthorId()).isEqualTo(authorId);
	}
	
	@Test
	public void AuthorServiceImpl_deleteAuthorById_ReturnVoid() {
		Long authorId = 1L;
		authorService.deleteAuthorById(authorId);
	
		verify(authorRepository, times(1)).deleteById(authorId);
	}
	
	@Test
	public void AuthorServiceImpl_findAllById_ReturnSetAuthorsByIds() {
		List<Author>authorSet = List.of(Mockito.mock(Author.class));
		doReturn(authorSet).when(authorRepository).findAllById(Mockito.any());
		Set<Author>authors = authorService.findAllById(Set.of(1L)).stream().collect(Collectors.toSet());
		
		Assertions.assertThat(authors).isNotNull();
		Assertions.assertThat(authors.size()).isEqualTo(1);
	}
	
	@Test
	public void AuthorServiceImpl_getAuthorDTOByBookIdWithConstructor_ReturnAuthorDTO() {
		Long id = 1L;
		List<AuthorDTO> authorDTOList = List.of(Mockito.mock(AuthorDTO.class));
		doReturn(authorDTOList).when(authorRepository).getAuthorDTOByBookIdWithConstructor(id);
		List<AuthorDTO> authorsDTO = authorService.getAuthorDTOByBookIdWithConstructor(id);
		
		Assertions.assertThat(authorsDTO).isNotNull();
		Assertions.assertThat(authorsDTO.size()).isEqualTo(1);
		
	}

}
