package ime.BookApp.dto;

import java.io.Serializable;
import java.util.Set;

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
	private Long bookId;
	private String isbn;
	private String title;		
	private Long publisherId;
	private Long genreId;
	private Long authorId;
	
}
