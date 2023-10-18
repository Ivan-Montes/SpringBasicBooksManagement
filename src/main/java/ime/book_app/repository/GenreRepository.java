package ime.book_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ime.book_app.dto.GenreDTO;
import ime.book_app.entity.Genre;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long>{

	@Query("SELECT new ime.BookApp.dto.GenreDTO(G.genreId, G.name, G.description) "
			+ "FROM Genre G "
			+ "ORDER BY G.genreId")
	List<GenreDTO>getAllGenreDTO();
}
