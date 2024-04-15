package ime.book_app.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ime.book_app.dto.AuthorDTO;
import ime.book_app.entity.Author;

@Component
public class AuthorMapper {

	public AuthorMapper() {
		super();
	}

	public AuthorDTO fromEntityToDto(Author author) {
		
		AuthorDTO dto = new AuthorDTO();
		dto.setAuthorId(author.getAuthorId());
		dto.setName(author.getName());
		dto.setSurname(author.getSurname());
		
		return dto;
	}
	
	public Set<AuthorDTO> fromSetEntityToListDto(Set<Author>list){
		
		return list.stream()
				.map(this::fromEntityToDto)
				.collect(Collectors.toSet());		
	}
	
}
