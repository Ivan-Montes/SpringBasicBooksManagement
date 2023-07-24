package ime.BookApp.dto;

import java.io.Serializable;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookshopCreationDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7425873714011526621L;
	
	@Size( min = 1, max = 50, message="{Size.BookshopCreationDTO.name}")
	private String name;
	
}
