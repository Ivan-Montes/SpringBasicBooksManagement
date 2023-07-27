package ime.BookApp.dto;

import java.io.Serializable;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AuthorCreationDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	@Size(min = 1, max = 50, message="{Size.AuthorCreationDTO.name}")
	@Pattern( regexp = "[a-zA-Z\\s\\-&]+", message="{Pattern.AuthorCreationDTO.name}")
	private String name;
	
	@Size(min = 1, max = 50, message="{Size.AuthorCreationDTO.surname}")
	@Pattern( regexp = "[a-zA-Z\\s\\-&]+", message="{Pattern.AuthorCreationDTO.surname}")
	private String surname;
}
