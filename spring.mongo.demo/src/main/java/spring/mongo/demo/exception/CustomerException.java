package spring.mongo.demo.exception;

public class CustomerException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	String message;

	public CustomerException() {}
	
	public CustomerException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	

}
