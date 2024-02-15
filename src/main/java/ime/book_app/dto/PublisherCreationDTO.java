package ime.book_app.dto;

import java.io.Serializable;

import ime.book_app.tool.RegexPattern;
import jakarta.validation.constraints.NotBlank;
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

	@NotBlank()
	@Size( min = 1, max = 50, message="{Size.PublisherCreationDTO.name}")
	@Pattern(regexp = RegexPattern.NAME_BASIC, message="{Pattern.PublisherCreationDTO.name}")
	private String name;	
}
