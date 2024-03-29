package ime.book_app.dto;

import java.io.Serializable;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Generated
public class BookDTO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1214675481560816948L;

	@NotNull(message="{NotNull.BookDTO.bookId}")
	private Long bookId;

	@Size(min=10, max=13, message="{Size.BookDTO.isbn}")
	private String isbn;

	@NotBlank()
	@Size(min=1, max=100, message="{Size.BookDTO.title}")
	private String title;
	
	@Size( min = 1, max = 50, message="{Size.BookDTO.publisher}")
	private String publisher;
	
	@Size( min = 1, max = 50, message="{Size.BookDTO.genre}")
	private String genre;
	
	@NotNull(message="{NotNull.BookDTO.authors}")
	private Set<AuthorDTO> authors;
	
	
	public BookDTO(Long bookId, String isbn, String title, String publisher, String genre) {
		super();
		this.bookId = bookId;
		this.isbn = isbn;
		this.title = title;
		this.publisher = publisher;
		this.genre = genre;
	}
	
	
	
}
