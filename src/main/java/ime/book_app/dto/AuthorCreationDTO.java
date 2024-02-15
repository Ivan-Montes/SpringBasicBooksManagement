package ime.book_app.dto;

import java.io.Serializable;

import ime.book_app.tool.RegexPattern;
import jakarta.validation.constraints.NotBlank;
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
public class AuthorCreationDTO implements Serializable{

		
	/**
	 * 
	 */
	private static final long serialVersionUID = 727075358399459951L;

	@NotBlank()
	@Size(min = 1, max = 50, message="{Size.AuthorCreationDTO.name}")
	@Pattern( regexp = RegexPattern.NAME_BASIC, message="{Pattern.AuthorCreationDTO.name}")
	private String name;

	@NotBlank()
	@Size(min = 1, max = 50, message="{Size.AuthorCreationDTO.surname}")
	@Pattern( regexp = RegexPattern.SURNAME_BASIC, message="{Pattern.AuthorCreationDTO.surname}")
	private String surname;
}
