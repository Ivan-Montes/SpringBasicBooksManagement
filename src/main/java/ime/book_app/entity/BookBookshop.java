package ime.book_app.entity;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table( name = "BOOKS_BOOKSHOPS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Generated
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
	@NotNull
	@Max(value=1000000, message = "{Max.BookBookshop.price}")
	@Min(value=0, message = "{Min.BookBookshop.price}")
	private Double price;
	
	@Column( nullable = false)
	@NotNull
	@Max(value=99, message = "{Max.BookBookshop.units}")
	@Min(value=0, message = "{Min.BookBookshop.units}")
	private Integer units;
}
