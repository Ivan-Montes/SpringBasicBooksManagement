package ime.book_app.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/index")
	public String mainIndex() {
		return "index";
	}
	
}
