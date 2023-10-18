package ime.book_app.repository;

import java.util.List;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.Query;

import ime.book_app.dto.AuthorDTO;
import ime.book_app.entity.Author;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long>{

	
	@Query(value="SELECT A.author_id, A.name, A.surname FROM AUTHORS A "
			+ "INNER JOIN BOOKS_AUTHORS BA ON BA.AUTHOR_ID = A.AUTHOR_ID "
			+ "WHERE BA.BOOK_ID = ?1", nativeQuery = true)
	List<Tuple>getAuthorsByBookIdWithTuples(Long id);
	
	
	@Query("Select new ime.BookApp.dto.AuthorDTO(a.authorId, a.name, a.surname) "
			+ "FROM Author a "
			+ "JOIN a.books b "
			+ "WHERE b.bookId = ?1")
	List<AuthorDTO>getAuthorDTOByBookIdWithConstructor(Long id);
	
	
	@Query("SELECT new ime.BookApp.dto.AuthorDTO(a.authorId, a.name, a.surname) "
			+ "FROM Author a ")
	List<AuthorDTO>getAllAuthorsDTO();
}
