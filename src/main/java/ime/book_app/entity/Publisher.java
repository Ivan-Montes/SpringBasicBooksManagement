package ime.book_app.entity;
import java.time.LocalDateTime;
import java.util.Set;

import ime.book_app.tool.RegexPattern;

import java.util.HashSet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;

@Entity
@Table( name = "PUBLISHERS")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Generated
public class Publisher {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column( name = "publisher_id")
	private Long publisherId;
	
	@Column( unique = true, nullable = false, length = 50)
	@Pattern( regexp = RegexPattern.NAME_FULL)
	private String name;	
	
	@Column( name = "creation_timestamp")
	private LocalDateTime creationTimestamp;
	
	@Column( name = "update_timestamp")
	private LocalDateTime updateTimestamp;
	
	@OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL, orphanRemoval = true )
	private Set<Book>books = new HashSet<>();
	
	@PrePersist
    private void prePersistFunction(){
		creationTimestamp = LocalDateTime.now();
	}
	
	@PreUpdate
    public void preUpdateFunction(){
		updateTimestamp = LocalDateTime.now();
	}
	
	
}
