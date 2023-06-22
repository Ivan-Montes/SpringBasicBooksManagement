package ime.BookApp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

import ime.BookApp.dto.PublisherDTO;

import ime.BookApp.entity.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher,Long>{

	@Query("SELECT new ime.BookApp.dto.PublisherDTO(P.publisherId, P.name) "
			+ "FROM Publisher P "
			+ "ORDER BY P.publisherId")
	List<PublisherDTO>getAllPublisherDTO();
}
