package ime.book_app.exception;

import java.io.Serializable;

import lombok.Generated;
import lombok.Value;

@Value
@Generated
public class BasicInformation implements Serializable{
	
	private static final long serialVersionUID = 8509786998435141897L;
	private String name;
	private String message;
	private String description;

}
