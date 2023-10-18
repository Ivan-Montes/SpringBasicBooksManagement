package ime.book_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ime.book_app.dto.BookshopDTO;
import ime.book_app.entity.Bookshop;

import java.util.List;

public interface BookshopRepository extends JpaRepository<Bookshop, Long> {
	
	@Query("SELECT new ime.BookApp.dto.BookshopDTO(BS.bookshopId, BS.name) "
			+ "FROM Bookshop BS "
			+ "ORDER BY BS.bookshopId")
	public List<BookshopDTO> getAllBookshopDTO();

}
