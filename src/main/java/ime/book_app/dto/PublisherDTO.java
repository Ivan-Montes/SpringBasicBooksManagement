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
public class PublisherDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6923636877841433867L;

	@NotNull(message="{NotNull.PublisherDTO.genreId}")
	private Long publisherId;

	@Size( min = 1, max = 50, message="{Size.PublisherDTO.name}")
	@Pattern(regexp = "[\\w\\s\\-&]+", message="{Pattern.PublisherDTO.name}")
	private String name;	
}
