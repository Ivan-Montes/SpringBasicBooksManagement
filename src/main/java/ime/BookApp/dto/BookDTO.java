package ime.BookApp.dto;

import java.io.Serializable;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class BookDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1880278942915598416L;
	
	private Long bookId;
	private String isbn;
	private String title;		
	private String publisher;
	private String genre;
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
