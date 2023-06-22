package ime.BookApp.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookshopDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7425873714011526621L;
	
	private Long bookshopId;
	private String name;
	
}
