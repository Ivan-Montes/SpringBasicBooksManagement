package ime.book_app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import ime.book_app.BookAppApplication;

@SpringBootTest(classes = BookAppApplication.class)
class BookAppApplicationTests {

	@Test
	void BookAppApplication_main_ReturnVoid() {
		Assertions.assertTrue(true);
	}

}
