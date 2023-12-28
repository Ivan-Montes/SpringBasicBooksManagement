package ime.book_app.exception;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final String EXCEPTION_TITLE = "exception";
	private static final String PATH_ERROR_EXCEPTION = "error/exception";

	@ExceptionHandler(org.hibernate.exception.ConstraintViolationException.class)
	public String hibernateConstraintViolationException(Exception ex, Model model) {
		model.addAttribute(EXCEPTION_TITLE, new BasicInformation("HibernateConstraintViolationException", ex.getMessage(), "Violación de una restricción de Integridad Referencial Hibernate"));
		return PATH_ERROR_EXCEPTION;
	}
	
	@ExceptionHandler(java.lang.NumberFormatException.class)
	public String numberFormatException(Exception ex, Model model) {
		model.addAttribute(EXCEPTION_TITLE, new BasicInformation("NumberFormatException", ex.getMessage(), "Failed to convert value"));
		return PATH_ERROR_EXCEPTION;
	}	

	@ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
	public String jakartaValidationConstraintViolationException(Exception ex, Model model) {
		model.addAttribute(EXCEPTION_TITLE, new BasicInformation("jakartaValidationConstraintViolationException", ex.getMessage(), "Failed checking some validation limit"));
		return PATH_ERROR_EXCEPTION;
	}

	@ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
	public String methodArgumentNotValidException(Exception ex, Model model) {
		model.addAttribute(EXCEPTION_TITLE, new BasicInformation("methodArgumentNotValidException", ex.getMessage(), "Fail Bean validation argument"));
		return PATH_ERROR_EXCEPTION;
	}
	
}
