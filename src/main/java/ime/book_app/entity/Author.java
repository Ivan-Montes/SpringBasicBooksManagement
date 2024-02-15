package ime.book_app.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.UpdateTimestamp;

import ime.book_app.tool.RegexPattern;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table( name = "AUTHORS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Generated
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name = "author_id")
	private Long authorId;
	 
	@Column(nullable = false, length = 50 )
	@Pattern( regexp = RegexPattern.NAME_FULL)
	private String name;
	
	@Column(nullable=false, length = 50 )
	@Pattern( regexp = RegexPattern.SURNAME_FULL)
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
