package ime.BookApp.dto;

import java.io.Serializable;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
	
	@NotNull(message="{NotNull.BookBookshopDTO.bookId}")
	private Long bookId;
	private String isbn;
	private String title;	
	
	@NotNull(message="{NotNull.BookBookshopDTO.bookshopId}")
	private Long bookshopId;
	private String name;
	
	@NotNull
	@Max(value=999, message = "{Max.BookBookshopDTO.price}")
	@Min(value=0, message = "{Min.BookBookshopDTO.price}")
	private Double price;
	
	@NotNull
	@Max(value=99, message = "{Max.BookBookshopDTO.units}")
	@Min(value=0, message = "{Min.BookBookshopDTO.units}")
	private Integer units;
	

}
