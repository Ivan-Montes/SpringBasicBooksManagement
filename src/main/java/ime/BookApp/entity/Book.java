package ime.BookApp.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table ( name = "BOOKS")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column( name = "book_id")
	private Long bookId;
	
	@Column(unique = true, nullable = false, length = 13)
	@Size(min=10, max=13)
	private String isbn;
	
	@Column(nullable = false, length = 100)
	@Size(min=1, max=100)
	private String title;		

	@CreationTimestamp
	@Column( name = "creation_timestamp")
	private LocalDateTime creationTimestamp;
	
	@UpdateTimestamp
	@Column( name = "update_timestamp")
	private LocalDateTime updateTimestamp;
	
	@ManyToOne
	@JoinColumn( name = "publisher_id", nullable = false)
	private Publisher publisher;
	
	@ManyToOne
	@JoinColumn( name = "genre_id", nullable = false)
	private Genre genre;
	
	//@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
			name = "BOOKS_AUTHORS",
			joinColumns = @JoinColumn( name = "book_id"),
			inverseJoinColumns = @JoinColumn( name = "author_id")
			)
	//@Fetch(FetchMode.JOIN)
	private Set<Author>authors = new HashSet<>();
	
	@OneToMany( mappedBy = "book")
	private Set<BookBookshop> bookshops = new HashSet<>();

	
	
}
