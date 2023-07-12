package ime.BookApp.entity;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
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
	@Size( min = 0, max = 1000000000)
	private Double price;
	
	@Column( nullable = false)
	@Size( min = 0, max = 32767)
	private Short units;
}
