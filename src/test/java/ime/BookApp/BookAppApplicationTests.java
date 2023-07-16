package ime.BookApp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = BookAppApplication.class)
class BookAppApplicationTests {

	@Test
	void BookAppApplication_main_ReturnVoid() {
		Assertions.assertTrue(true);
	}

}
