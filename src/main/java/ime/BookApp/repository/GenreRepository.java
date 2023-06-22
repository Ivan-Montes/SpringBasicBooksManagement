package ime.BookApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import ime.BookApp.entity.Genre;
import ime.BookApp.dto.GenreDTO;

public interface GenreRepository extends JpaRepository<Genre, Long>{

	@Query("SELECT new ime.BookApp.dto.GenreDTO(G.genreId, G.name, G.description) "
			+ "FROM Genre G "
			+ "ORDER BY G.genreId")
	List<GenreDTO>getAllGenreDTO();
}
