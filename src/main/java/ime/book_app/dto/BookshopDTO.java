package ime.book_app.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookshopDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7425873714011526621L;

	@NotNull(message="{NotNull.BookshopDTO.bookshopId}")
	private Long bookshopId;
	
	@Size( min = 1, max = 50, message="{Size.BookshopDTO.name}")
	@Pattern( regexp = "[\\w\\s\\-&]+", message="{Pattern.BookshopDTO.name}")
	private String name;
	
}
