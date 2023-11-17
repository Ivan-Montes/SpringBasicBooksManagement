package ime.book_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ime.book_app.dto.BookBookshopDTO;
import ime.book_app.entity.BookBookshop;
import ime.book_app.entity.BookBookshopId;

public interface BookBookshopRepository  extends JpaRepository<BookBookshop, BookBookshopId>{

	@Query("SELECT new ime.book_app.dto.BookBookshopDTO(B.book.bookId, B.book.isbn, B.book.title, B.bookshop.bookshopId, B.bookshop.name, B.price, B.units) "
			+ "FROM BookBookshop B ")
	List<BookBookshopDTO>getAllBookBookshopDTO();
	
	@Query("SELECT new ime.book_app.dto.BookBookshopDTO(B.book.bookId, B.book.isbn, B.book.title, B.bookshop.bookshopId, B.bookshop.name, B.price, B.units) "
			+ "FROM BookBookshop B "
			+ "WHERE B.book.bookId = ?1 AND B.bookshop.bookshopId = ?2")
	BookBookshopDTO getBookBookshopDTOById(Long bookId, Long bookshopId);
}