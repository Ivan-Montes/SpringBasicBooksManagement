package ime.book_app.exception;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;

import jakarta.validation.ConstraintViolationException;

class GlobalExceptionHandlerTest {

	private GlobalExceptionHandler globalExceptionHandler;
	private static final String PATH_ERROR_EXCEPTION = "error/exception";
	private static final String EXCEPTION_TEXT = "33";
	
	@BeforeEach
	private void createObjects() {
		globalExceptionHandler = new GlobalExceptionHandler();
		
	}
	
	@Test
	void GlobalExceptionHandler_hibernateConstraintViolationException_ReturnString() {
		
		ConstraintViolationException ex = Mockito.mock(ConstraintViolationException.class);
		Model model = Mockito.mock(Model.class);		
		doReturn(EXCEPTION_TEXT).when(ex).getMessage();
		doReturn(model).when(model).addAttribute(Mockito.any(), Mockito.any());
		
		String result = globalExceptionHandler.hibernateConstraintViolationException(ex, model);
		
		assertAll(
				()->Assertions.assertThat(result).isNotNull(),
				()->Assertions.assertThat(result).isEqualTo(PATH_ERROR_EXCEPTION)
				);
	}

	@Test
	void GlobalExceptionHandler_numberFormatException_ReturnString() {
		
		NumberFormatException ex = Mockito.mock(NumberFormatException.class);
		Model model = Mockito.mock(Model.class);		
		doReturn(EXCEPTION_TEXT).when(ex).getMessage();
		doReturn(model).when(model).addAttribute(Mockito.any(), Mockito.any());
		
		String result = globalExceptionHandler.numberFormatException(ex, model);
		
		assertAll(
				()->Assertions.assertThat(result).isNotNull(),
				()->Assertions.assertThat(result).isEqualTo(PATH_ERROR_EXCEPTION)
				);
	}
	

	@Test
	void GlobalExceptionHandler_jakartaValidationConstraintViolationException_ReturnString() {
		
		jakarta.validation.ConstraintViolationException ex = Mockito.mock(jakarta.validation.ConstraintViolationException.class);
		Model model = Mockito.mock(Model.class);		
		doReturn(EXCEPTION_TEXT).when(ex).getMessage();
		doReturn(model).when(model).addAttribute(Mockito.any(), Mockito.any());
		
		String result = globalExceptionHandler.jakartaValidationConstraintViolationException(ex, model);
		
		assertAll(
				()->Assertions.assertThat(result).isNotNull(),
				()->Assertions.assertThat(result).isEqualTo(PATH_ERROR_EXCEPTION)
				);
	}
	

	@Test
	void GlobalExceptionHandler_methodArgumentNotValidException_ReturnString() {
		
		MethodArgumentNotValidException ex = Mockito.mock(MethodArgumentNotValidException.class);
		Model model = Mockito.mock(Model.class);		
		doReturn(EXCEPTION_TEXT).when(ex).getMessage();
		doReturn(model).when(model).addAttribute(Mockito.any(), Mockito.any());
		
		String result = globalExceptionHandler.methodArgumentNotValidException(ex, model);
		
		assertAll(
				()->Assertions.assertThat(result).isNotNull(),
				()->Assertions.assertThat(result).isEqualTo(PATH_ERROR_EXCEPTION)
				);
	}
}
