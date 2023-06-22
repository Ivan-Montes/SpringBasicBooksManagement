package ime.BookApp.dto;

import java.io.Serializable;

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
	
	private Long publisherId;
	private String name;	
}
