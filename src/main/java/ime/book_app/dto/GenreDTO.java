package ime.book_app.dto;

import java.io.Serializable;

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
public class GenreDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message="{NotNull.GenreDTO.genreId}")
	private Long genreId;

	@Size( min = 1, max = 50, message="{Size.GenreDTO.name}")
	@Pattern( regexp = "[\\w\\s\\-&]+", message="{Pattern.GenreDTO.name}")
	private String name;

	@Size( min = 1, max = 100, message="{Size.GenreDTO.description}")
	@Pattern( regexp = "[\\w\\s\\-&\\.\\,\\(\\)]+", message="{Pattern.GenreDTO.description}")
	private String description;
	
}
