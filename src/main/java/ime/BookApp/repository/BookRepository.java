package ime.BookApp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ime.BookApp.dto.BookDTO;
import ime.BookApp.entity.Book;
import jakarta.persistence.Tuple;

public interface BookRepository extends JpaRepository<Book, Long>{

	
	@Query(value = "SELECT B.BOOK_ID, B.ISBN, B.TITLE, P.NAME, G.NAME "
			+ "FROM BOOKS B "
			+ "INNER JOIN PUBLISHERS P ON P.PUBLISHER_ID = B.PUBLISHER_ID "
			+ "INNER JOIN GENRES G ON G.GENRE_ID = B.GENRE_ID;", nativeQuery = true)
	List<Tuple>givemeListTuple();
		
	//JPQL Funciona, falta lista de authors ***
	// Con authors no matching constructor found,  a no ser que creemos un constructor sin ese campo.
	@Query(value = "SELECT new ime.BookApp.dto.BookDTO(bookId, isbn, title, publisher.name, genre.name) "
			+ "FROM Book "
			+ "ORDER BY bookId")
	List<BookDTO>getAllBookDTO();	
		
			
}
