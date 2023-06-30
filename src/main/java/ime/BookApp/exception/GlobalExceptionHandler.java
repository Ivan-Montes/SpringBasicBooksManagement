package ime.BookApp.exception;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	

	@ExceptionHandler(org.hibernate.exception.ConstraintViolationException.class)
	public String hibernateConstraintViolationException(Exception ex, Model model) {
		model.addAttribute("exception", new BasicException("HibernateConstraintViolationException", ex.getMessage(), "Violación de una restricción de Integridad Referencial Hibernate"));
		return "/error/exception";
	}
	
	@ExceptionHandler(java.lang.NumberFormatException.class)
	public String numberFormatException(Exception ex, Model model) {
		model.addAttribute("exception", new BasicException("NumberFormatException", ex.getMessage(), "Failed to convert value"));
		return "/error/exception";
	}	

	@ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
	public String jakartaValidationConstraintViolationException(Exception ex, Model model) {
		model.addAttribute("exception", new BasicException("jakartaValidationConstraintViolationException", ex.getMessage(), "Failed checking some validation limit"));
		return "/error/exception";
	}
}
