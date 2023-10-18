package ime.book_app.entity;



import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBookshopId  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column( name = "book_id")
	private Long bookId;
	
	@Column( name = "bookshop_id")
	private Long bookshopId;
}
