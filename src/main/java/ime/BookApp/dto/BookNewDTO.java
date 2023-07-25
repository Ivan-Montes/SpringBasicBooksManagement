package ime.BookApp.dto;

import java.io.Serializable;
import java.util.Set;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookNewDTO implements Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7523292298367025766L;
	
	@NotNull(message="{NotNull.BookNewDTO.bookId}")
	private Long bookId;

	@Size(min=10, max=13, message="{Size.BookNewDTO.isbn}")
	private String isbn;

	@Size(min=1, max=100, message="{Size.BookNewDTO.title}")
	private String title;
	
	@NotNull(message="{NotNull.BookNewDTO.publisherId}")
	private Long publisherId;
	
	@NotNull(message="{NotNull.BookNewDTO.genreId}")
	private Long genreId;
	
	@NotNull(message="{NotNull.BookNewDTO.authorId}")
	private Set<Long> authorId;
	
}
