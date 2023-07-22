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
public class BookNewSimpleDTO implements Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7523292298367025766L;
	

	@Size(min=10, max=13, message="{Size.BookNewSimpleDTO.isbn")
	private String isbn;

	@Size(min=1, max=100, message="{Size.BookNewSimpleDTO.title")
	private String title;
	
	@NotNull(message="{NotNull.BookNewSimpleDTO.publisherId}")
	private Long publisherId;
	
	@NotNull(message="{NotNull.BookNewSimpleDTO.genreId}")
	private Long genreId;
	
	@NotNull(message="{NotNull.BookNewSimpleDTO.authorId}")
	private Set<Long> authorId;
	
}
