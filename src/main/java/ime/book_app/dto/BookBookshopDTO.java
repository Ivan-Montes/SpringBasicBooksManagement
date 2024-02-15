package ime.book_app.dto;

import java.io.Serializable;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Generated
public class BookBookshopDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8789307225020451644L;
	
	@NotNull(message="{NotNull.BookBookshopDTO.bookId}")
	private Long bookId;

	@Size(min=10, max=13, message="{Size.BookBookshopDTO.isbn}")
	private String isbn;

	@NotBlank()
	@Size(min=1, max=100, message="{Size.BookBookshopDTO.title}")
	private String title;	
	
	@NotNull(message="{NotNull.BookBookshopDTO.bookshopId}")
	private Long bookshopId;

	@NotBlank()
	@Size( min = 1, max = 50,  message="{Size.BookBookshopDTO.name}")
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
