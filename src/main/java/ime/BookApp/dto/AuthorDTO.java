package ime.BookApp.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AuthorDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull(message="{NotNull.AuthorDTO.authorId}")
	private Long authorId;
	
	@Size(min = 1, max = 50, message="{Size.AuthorDTO.name}")
	private String name;
	
	@Size(min = 1, max = 50, message="{Size.AuthorDTO.surname}")
	private String surname;
}
