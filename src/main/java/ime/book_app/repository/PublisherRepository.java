package ime.book_app.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ime.book_app.dto.PublisherDTO;
import ime.book_app.entity.Publisher;

import java.util.List;

public interface PublisherRepository extends JpaRepository<Publisher,Long>{

	@Query("SELECT new ime.BookApp.dto.PublisherDTO(P.publisherId, P.name) "
			+ "FROM Publisher P "
			+ "ORDER BY P.publisherId")
	List<PublisherDTO>getAllPublisherDTO();
}
