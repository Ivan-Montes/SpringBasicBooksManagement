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
public class GenreCreationDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank()
	@Size( min = 1, max = 50, message="{Size.GenreDTO.name}")
	@Pattern( regexp = RegexPattern.NAME_BASIC, message="{Pattern.GenreCreationDTO.name}")
	private String name;

	@NotBlank()
	@Size( min = 1, max = 100, message="{Size.GenreDTO.description}")
	@Pattern( regexp = RegexPattern.DESCRIPTION_BASIC, message="{Pattern.GenreCreationDTO.description}")
	private String description;
	
}
