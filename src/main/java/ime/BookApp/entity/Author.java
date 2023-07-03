package ime.BookApp.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table( name = "AUTHORS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

	@Override
	public String toString() {
		return "Author [authorId=" + authorId + ", name=" + name + ", surname=" + surname + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(authorId, creationTimestamp, name, surname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		return Objects.equals(authorId, other.authorId) && Objects.equals(creationTimestamp, other.creationTimestamp)
				&& Objects.equals(name, other.name) && Objects.equals(surname, other.surname);
	}
	
}
