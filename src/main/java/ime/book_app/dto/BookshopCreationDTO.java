package ime.book_app.dto;

import java.io.Serializable;

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
public class BookshopCreationDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7425873714011526621L;
	
	@Size( min = 1, max = 50, message="{Size.BookshopCreationDTO.name}")
	@Pattern( regexp = "[\\w\\s\\-&]+", message="{Pattern.BookshopCreationDTO.name}")
	private String name;
	
}
