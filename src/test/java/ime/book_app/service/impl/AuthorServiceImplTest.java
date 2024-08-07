package ime.book_app.service.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import ime.book_app.dto.*;
import ime.book_app.entity.Author;
import ime.book_app.repository.AuthorRepository;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {

	@Mock
	private AuthorRepository authorRepository;
	
	@InjectMocks
	private AuthorServiceImpl authorService;
	
	@Test
	void AuthorServiceImpl_getAllAuthorDTO_ReturnZeroOrMoreDTO() {
		List<AuthorDTO> authorDTOList = List.of(Mockito.mock(AuthorDTO.class));
		when(authorRepository.getAllAuthorsDTO()).thenReturn(authorDTOList);
		List<AuthorDTO> list = authorService.getAllAuthorDTO();
		
		assertAll(
				()->Assertions.assertThat(list).isNotNull(),
				()->Assertions.assertThat(list).hasSize(1)
				);
	}
	
	@Test
	void AuthorServiceImpl_findAuthorById_ReturnAuthor() {		
		Long authorId = 1L;
		Author author = new Author();
		author.setAuthorId(authorId);
		Optional<Author> opt = Optional.of(author);
		doReturn(opt).when(authorRepository).findById(authorId);
		Author authorFound = authorService.findAuthorById(authorId);
		
		assertAll(
				()->Assertions.assertThat(authorFound).isNotNull(),
				()->Assertions.assertThat(authorFound.getAuthorId()).isEqualTo(authorId)
				);		
	}	
	
	
	@Test
	void AuthorServiceImpl_saveAuthor_ReturnAuthor() {
		Long authorId = 1L;
		Author author = new Author();
		author.setAuthorId(authorId);
		doReturn(author).when(authorRepository).save(Mockito.any());
		Author authorSaved = authorService.saveAuthor(author);
		
		assertAll(
				()->Assertions.assertThat(authorSaved).isNotNull(),
				()->Assertions.assertThat(authorSaved.getAuthorId()).isEqualTo(authorId)
				);
	}
	
	@Test
	void AuthorServiceImpl_deleteAuthorById_ReturnVoid() {
		Long authorId = 1L;
		authorService.deleteAuthorById(authorId);
	
		verify(authorRepository, times(1)).deleteById(authorId);
	}
	
	@Test
	void AuthorServiceImpl_findAllById_ReturnSetAuthorsByIds() {
		List<Author>authorSet = List.of(Mockito.mock(Author.class));
		doReturn(authorSet).when(authorRepository).findAllById(Mockito.any());
		Set<Author>authors = authorService.findAllById(Set.of(1L)).stream().collect(Collectors.toSet());
		
		assertAll(
				()->Assertions.assertThat(authors).isNotNull(),
				()->Assertions.assertThat(authors).hasSize(1)
				);
	}
	
	@Test
	void AuthorServiceImpl_getAuthorDTOByBookIdWithConstructor_ReturnAuthorDTO() {
		Long id = 1L;
		List<AuthorDTO> authorDTOList = List.of(Mockito.mock(AuthorDTO.class));
		doReturn(authorDTOList).when(authorRepository).getAuthorDTOByBookIdWithConstructor(id);
		List<AuthorDTO> authorsDTO = authorService.getAuthorDTOByBookIdWithConstructor(id);
		
		assertAll(
				()->Assertions.assertThat(authorsDTO).isNotNull(),
				()->Assertions.assertThat(authorsDTO).hasSize(1)
				);
	}

	@Test
	void AuthorServiceImpl_getAllPaged_ReturnPageAsc() {
		
		Page<Author>page = Page.empty();
		when(authorRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(page);
		
		Page<Author>pageGenreResult = authorService.getAllPaged(1, "authorId", "asc");
		
		assertAll(
				()->Assertions.assertThat(pageGenreResult).isNotNull(),
				()->Assertions.assertThat(pageGenreResult.isEmpty())
				);		
	}

	@Test
	void AuthorServiceImpl_getAllPaged_ReturnPageDesc() {
		
		Page<Author>page = Page.empty();
		when(authorRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(page);
		
		Page<Author>pageGenreResult = authorService.getAllPaged(1, "authorId", "d");
		
		assertAll(
				()->Assertions.assertThat(pageGenreResult).isNotNull(),
				()->Assertions.assertThat(pageGenreResult.isEmpty())
				);		
	}

}
