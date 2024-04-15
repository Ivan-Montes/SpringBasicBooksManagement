package ime.book_app.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import ime.book_app.dto.BookDTO;
import ime.book_app.entity.Book;

@Component
public class BookMapper {

	private final AuthorMapper authorMapper;
	
	public BookMapper(AuthorMapper authorMapper) {
		super();
		this.authorMapper = authorMapper;
	}

	public BookDTO fromEntityToDto(Book book) {
		
		BookDTO dto = new BookDTO();
		dto.setBookId(book.getBookId());
		dto.setIsbn(book.getIsbn());
		dto.setTitle(book.getTitle());
		dto.setPublisher(book.getPublisher().getName());
		dto.setGenre(book.getGenre().getName());
		dto.setAuthors(authorMapper.fromSetEntityToListDto(book.getAuthors()));
		return dto;
	}
	
	public List<BookDTO> fromListEntityToListDto(List<Book>list){
		
		return list.stream()
				.map(this::fromEntityToDto)
				.toList();
	}
	
}
