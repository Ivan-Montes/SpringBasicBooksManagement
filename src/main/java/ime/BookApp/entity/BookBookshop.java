package ime.BookApp.entity;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table( name = "BOOKS_BOOKSHOPS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookBookshop {

	@EmbeddedId
	private BookBookshopId bookBookshopId;
	
	@ManyToOne
	@MapsId("bookId")
	private Book book;
	
	@ManyToOne
	@MapsId("bookshopId")
	private Bookshop bookshop;
	
	@Column( nullable = false)
	@Max(value=1000000, message = "Price should not be greater than One Million")
	@Min(value=0, message = "Price should not be less than 0")
	private Double price;
	
	@Column( nullable = false)
	@Max(value=1000000, message = "units should not be greater than One Million")
	@Min(value=0, message = "units should not be less than 0")
	private Integer units;
}
