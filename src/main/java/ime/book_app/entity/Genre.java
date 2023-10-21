package ime.book_app.entity;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.HashSet;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "GENRES")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Generated
public class Genre {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column( name = "genre_id")
	private Long genreId;
	
	@Column(unique = true, nullable = false, length = 50)
	@Size( min = 1, max = 50)
	private String name;
	
	@Column(nullable = false, length = 100)
	@Size( min = 1, max = 100)
	private String description;
	
	@CreationTimestamp
	@Column( name = "creation_timestamp")
	private LocalDateTime creationTimestamp;	

	@UpdateTimestamp
	@Column( name = "update_timestamp")
	private LocalDateTime updateTimestamp;	
	
	@OneToMany(mappedBy = "genre", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Book>books = new HashSet<>();

}
