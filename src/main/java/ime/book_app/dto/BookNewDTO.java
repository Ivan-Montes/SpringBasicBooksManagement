package ime.book_app.dto;

import java.io.Serializable;
import java.util.Set;

import ime.book_app.tool.RegexPattern;
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
public class BookNewDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -839728657870309886L;

	@NotNull(message="{NotNull.BookNewDTO.bookId}")
	private Long bookId;

	@Size(min=10, max=13, message="{Size.BookNewDTO.isbn}")
	@Pattern( regexp = "[\\d]{10,13}", message="{Pattern.BookNewDTO.isbn}")
	private String isbn;

	@Size(min=1, max=100, message="{Size.BookNewDTO.title}")
	@Pattern(regexp = RegexPattern.TITLE_BASIC, message="{Pattern.BookNewDTO.title}")
	private String title;
	
	@NotNull(message="{NotNull.BookNewDTO.publisherId}")
	private Long publisherId;
	
	@NotNull(message="{NotNull.BookNewDTO.genreId}")
	private Long genreId;
	
	@NotNull(message="{NotNull.BookNewDTO.authorId}")
	private Set<Long> authorId;
	
}
