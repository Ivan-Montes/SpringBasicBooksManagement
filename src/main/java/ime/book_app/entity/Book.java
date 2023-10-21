package ime.book_app.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table ( name = "BOOKS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Generated
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
	@JoinColumn( name = "publisher_id")
	private Publisher publisher;
	
	@ManyToOne
	@JoinColumn( name = "genre_id")
	private Genre genre;
	
	@ManyToMany
	@JoinTable(
			name = "BOOKS_AUTHORS",
			joinColumns = @JoinColumn( name = "book_id"),
			inverseJoinColumns = @JoinColumn( name = "author_id")
			)
	private Set<Author>authors = new HashSet<>();
	
	@OneToMany( mappedBy = "book")
	private Set<BookBookshop> bookshops = new HashSet<>();

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", isbn=" + isbn + ", title=" + title + ", creationTimestamp="
				+ creationTimestamp + ", updateTimestamp=" + updateTimestamp + ", publisher=" + publisher + ", genre="
				+ genre + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(bookId, creationTimestamp, genre, isbn, publisher, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(bookId, other.bookId) && Objects.equals(creationTimestamp, other.creationTimestamp)
				&& Objects.equals(genre, other.genre) && Objects.equals(isbn, other.isbn)
				&& Objects.equals(publisher, other.publisher) && Objects.equals(title, other.title);
	}

	
	
}
