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
public class PublisherCreationDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6923636877841433867L;

	@Size( min = 1, max = 50, message="{Size.PublisherCreationDTO.name}")
	@Pattern(regexp = "[\\w\\s\\-&]+", message="{Pattern.PublisherCreationDTO.name}")
	private String name;	
}
