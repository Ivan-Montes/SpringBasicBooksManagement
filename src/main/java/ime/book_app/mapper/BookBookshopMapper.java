package ime.book_app.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import ime.book_app.dto.BookBookshopDTO;
import ime.book_app.entity.BookBookshop;

@Component
public class BookBookshopMapper {

	public BookBookshopMapper() {
		super();
	}

	public BookBookshopDTO fromEntityToDto(BookBookshop bbs) {
		
		BookBookshopDTO dto = new BookBookshopDTO();
		dto.setBookId(bbs.getBook().getBookId());
		dto.setIsbn(bbs.getBook().getIsbn());
		dto.setTitle(bbs.getBook().getTitle());
		dto.setBookshopId(bbs.getBookshop().getBookshopId());
		dto.setName(bbs.getBookshop().getName());
		dto.setPrice(bbs.getPrice());
		dto.setUnits(bbs.getUnits());
		
		return dto;
	}
	
	public List<BookBookshopDTO> fromListEntityToListDto(List<BookBookshop> list){
		
		return list.stream()
				.map(this::fromEntityToDto)
				.toList();
	}
	
}
