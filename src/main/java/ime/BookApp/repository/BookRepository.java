package ime.BookApp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ime.BookApp.dto.BookDTO;
import ime.BookApp.entity.Book;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.EntityGraph;

public interface BookRepository extends JpaRepository<Book, Long>{

	//SQL solo funciona contra bd (falta authors), aqui no  => No converter found capable of converting from type
	@Query(value = "SELECT  B.BOOK_ID as bookId, B.ISBN as isbn, B.TITLE as title, P.NAME as publisher, G.NAME as genre "
			+ "FROM BOOKS B "
			+ "INNER JOIN BOOKS_AUTHORS BA ON BA.BOOK_ID = B.BOOK_ID "
			+ "INNER JOIN AUTHORS A ON A.AUTHOR_ID = BA.AUTHOR_ID "
			+ "INNER JOIN PUBLISHERS P ON P.PUBLISHER_ID = B.PUBLISHER_ID "
			+ "INNER JOIN GENRES G ON G.GENRE_ID = B.GENRE_ID;", nativeQuery = true)
	List<BookDTO>givemeListBookDTO1();
	
	//SQL DUPLICA RESULTADOS AL ENCONTRAR LOS DATOS DE TABLA N-M. Si 2 autores escriben conjuntamente un libro, este sale 2 veces. 	
	@Query(value = "SELECT B.BOOK_ID, B.ISBN, B.TITLE, P.NAME, G.NAME, A.* "
			+ "FROM BOOKS B "
			+ "INNER JOIN BOOKS_AUTHORS BA ON BA.BOOK_ID = B.BOOK_ID "
			+ "INNER JOIN AUTHORS A ON A.AUTHOR_ID = BA.AUTHOR_ID "
			+ "INNER JOIN PUBLISHERS P ON P.PUBLISHER_ID = B.PUBLISHER_ID "
			+ "INNER JOIN GENRES G ON G.GENRE_ID = B.GENRE_ID;", nativeQuery = true)
	List<Tuple>givemeListTuple1();
	
	//SQL OK ***
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
	
	// Con authors no matching constructor found
	@Query(value = "SELECT new ime.BookApp.dto.BookDTO(B.bookId, B.isbn, B.title, B.publisher.name, B.genre.name, B.authors) "
			+ "FROM Book B LEFT JOIN B.authors A")
	List<BookDTO>givemeListBookDTO3();
	
	//JPQL Funciona, lista de authors vacia, igual que metodo interno findAll
		@Query("SELECT B FROM Book B")
		List<Book>givemeListBook();
		
	//JPQL Funciona, lista de authors vacia, igual que metodo interno findAll
		@Query("SELECT B FROM Book B")
		@EntityGraph(attributePaths = {"authors"})
		List<Book>givemeListBookUsingEntityGraph();
	
	@EntityGraph(attributePaths = {"authors"})                
		   List<Book> findAll();
			
	//JPQL Funciona, lista de authors vacia, igual que metodo interno findAll
		@Query("SELECT B FROM Book B LEFT JOIN FETCH B.authors A")
		List<Book>givemeObjectsBookJoinFetch();
		
		
	//Using HQL Query
		@Query("from Book b join fetch b.authors")
		List<Book>givemeBookHQLFetch();
		
/*	//JPQL Crash app
			@Query("SELECT A FROM Book B JOIN FETCH B.authors A")
			List<Author>givemeSetAuthores(Long id);*/
		
		//List<Author>findListBy(Long id);	
		
			
}
