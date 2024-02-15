package ime.book_app.entity;

import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import ime.book_app.tool.RegexPattern;

import java.time.LocalDateTime;
import java.util.HashSet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Entity
@Table( name = "BOOKSHOPS")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Generated
public class Bookshop {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column( name = "bookshop_id")
	private Long bookshopId;
	
	@Column(nullable = false, length = 50)
	@Pattern( regexp = RegexPattern.NAME_FULL)
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
