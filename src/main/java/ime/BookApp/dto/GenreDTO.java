package ime.BookApp.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GenreDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message="{NotNull.GenreDTO.genreId}")
	private Long genreId;

	@Size( min = 1, max = 50, message="{Size.GenreDTO.name")
	private String name;

	@Size( min = 1, max = 100, message="{Size.GenreDTO.description")
	private String description;
	
}
