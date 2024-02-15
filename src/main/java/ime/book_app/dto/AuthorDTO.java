package ime.book_app.dto;

import java.io.Serializable;

import ime.book_app.tool.RegexPattern;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Generated
public class AuthorDTO implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4463252815200541343L;

	@NotNull(message="{NotNull.AuthorDTO.authorId}")
	private Long authorId;
	
	@Size(min = 1, max = 50, message="{Size.AuthorDTO.name}")
	@Pattern( regexp = RegexPattern.NAME_BASIC, message="{Pattern.AuthorDTO.name}")
	private String name;
	
	@Size(min = 1, max = 50, message="{Size.AuthorDTO.surname}")
	@Pattern( regexp = RegexPattern.SURNAME_BASIC, message="{Pattern.AuthorDTO.surname}")
	private String surname;
}
