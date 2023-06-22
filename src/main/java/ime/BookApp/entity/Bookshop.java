package ime.BookApp.entity;

import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;

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
import lombok.NoArgsConstructor;

@Entity
@Table( name = "BOOKSHOPS")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Bookshop {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column( name = "bookshop_id")
	private Long bookshopId;
	
	@Column(nullable = false, length = 50)
	@Size( min = 1, max = 50)
	private String name;
	
	@OneToMany( mappedBy = "bookshop")
	private Set<BookBookshop> books = new HashSet<>();
	

	@CreationTimestamp
	@Column( name = "creation_timestamp")
	private LocalDateTime creationTimestamp;	

	@UpdateTimestamp
	@Column( name = "update_timestamp")
	private LocalDateTime updateTimestamp;	
	
	
}
