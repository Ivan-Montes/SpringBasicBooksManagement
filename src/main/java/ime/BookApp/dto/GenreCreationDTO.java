package ime.BookApp.dto;

import java.io.Serializable;

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

	@Size( min = 1, max = 50, message="{Size.GenreDTO.name")
	private String name;

	@Size( min = 1, max = 100, message="{Size.GenreDTO.description")
	private String description;
	
}
