package ime.BookApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ime.BookApp.dto.BookBookshopDTO;
import ime.BookApp.entity.BookBookshop;
import ime.BookApp.entity.BookBookshopId;

public interface BookBookshopRepository  extends JpaRepository<BookBookshop, BookBookshopId>{

	@Query("SELECT new ime.BookApp.dto.BookBookshopDTO(B.book.bookId, B.book.isbn, B.book.title, B.bookshop.bookshopId, B.bookshop.name, B.price, B.units) "
			+ "FROM BookBookshop B ")
	List<BookBookshopDTO>getAllBookBookshopDTO();
}