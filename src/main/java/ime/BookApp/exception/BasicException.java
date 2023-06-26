package ime.BookApp.exception;

import java.io.Serializable;

import lombok.Value;

@Value
public class BasicException implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8509786998435141897L;
	private String name;
	private String message;
	private String description;

}