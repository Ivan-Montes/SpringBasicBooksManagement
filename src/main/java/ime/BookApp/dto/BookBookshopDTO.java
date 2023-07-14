package ime.BookApp.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookBookshopDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8789307225020451644L;
	
	private Long bookId;
	private String isbn;
	private String title;		
	
	private Long bookshopId;
	private String name;
	
	private Double price;
	private Integer units;
	

}
