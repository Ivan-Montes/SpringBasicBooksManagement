package ime.book_app.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import ime.book_app.dto.AuthorDTO;
import ime.book_app.entity.Author;

@ExtendWith(MockitoExtension.class)
class AuthorMapperTest {

	@InjectMocks
	private AuthorMapper authorMapper;
	
	private Set<Author> setAuthor;
	private Author author;
	private final Long authorId = 9L;
	
	@BeforeEach
	private void createObjects() {
		
		setAuthor = new HashSet<>();
		author = new Author();
		author.setAuthorId(authorId);
		
	}
	
	@Test
	void AuthorMapper_fromListEntityToListDto_ReturnListDto() {
		
		setAuthor.add(author);
		Set<AuthorDTO> setAuthorDTO = authorMapper.fromSetEntityToListDto(setAuthor);
		
		assertAll(
				()->Assertions.assertThat(setAuthorDTO).isNotNull(),
				()->Assertions.assertThat(setAuthorDTO).hasSize(1),
				()->Assertions.assertThat(setAuthorDTO.parallelStream().anyMatch( a -> a.getAuthorId().equals(authorId)))
				);
	}

}
