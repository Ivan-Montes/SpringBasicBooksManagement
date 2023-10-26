package ime.book_app.dto;

import java.io.Serializable;
import java.util.Set;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class BookCreationDTO implements Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7523292298367025767L;
	

	@Size(min=10, max=13, message="{Size.BookCreationDTO.isbn}")
	@Pattern( regexp = "[\\d]{10,13}", message="{Pattern.BookCreationDTO.isbn}")
	private String isbn;

	@Size(min=1, max=100, message="{Size.BookCreationDTO.title}")
	@Pattern(regexp = "[\\w\\s\\-&]+", message="{Pattern.BookCreationDTO.title}")
	private String title;
	
	@NotNull(message="{NotNull.BookCreationDTO.publisherId}")
	private Long publisherId;
	
	@NotNull(message="{NotNull.BookCreationDTO.genreId}")
	private Long genreId;
	
	@NotNull(message="{NotNull.BookCreationDTO.authorId}")
	private Set<Long> authorId;
	
}
