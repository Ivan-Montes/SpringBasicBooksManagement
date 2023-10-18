package ime.book_app.dto;

import java.io.Serializable;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GenreCreationDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Size( min = 1, max = 50, message="{Size.GenreDTO.name}")
	@Pattern( regexp = "[\\w\\s\\-&]+", message="{Pattern.GenreCreationDTO.name}")
	private String name;

	@Size( min = 1, max = 100, message="{Size.GenreDTO.description}")
	@Pattern( regexp = "[\\w\\s\\-&\\.\\,\\(\\)]+", message="{Pattern.GenreCreationDTO.description}")
	private String description;
	
}
