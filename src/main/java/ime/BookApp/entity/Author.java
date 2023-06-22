package ime.BookApp.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name = "AUTHORS")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name = "author_id")
	private Long authorId;
	 
	@Column(nullable = false, length = 50 )
	@Size(min = 1, max = 50)
	private String name;
	
	@Column(nullable=false, length = 50 )
	@Size(min = 1, max = 50)
	private String surname;
	
	@CreationTimestamp
	@Column( name = "creation_timestamp")
	private LocalDateTime creationTimestamp;
	
	@UpdateTimestamp
	@Column( name = "update_timestamp")
	private LocalDateTime updateTimestamp;
	
	@ManyToMany( mappedBy = "authors")
	private Set<Book>books = new HashSet<>();
	
}
