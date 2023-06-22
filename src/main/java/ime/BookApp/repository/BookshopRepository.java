package ime.BookApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ime.BookApp.entity.Bookshop;
import ime.BookApp.dto.BookshopDTO;
import java.util.List;

public interface BookshopRepository extends JpaRepository<Bookshop, Long> {
	
	@Query("SELECT new ime.BookApp.dto.BookshopDTO(BS.bookshopId, BS.name) "
			+ "FROM Bookshop BS "
			+ "ORDER BY BS.bookshopId")
	public List<BookshopDTO> getAllBookshopDTO();

}
