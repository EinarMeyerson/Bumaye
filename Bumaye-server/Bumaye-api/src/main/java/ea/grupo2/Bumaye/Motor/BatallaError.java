package ea.grupo2.Bumaye.Motor;

public class BatallaError {
	private int status;
	private String message;
 
	public BatallaError() {
		super();
	}
 
	public BatallaError(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
 
	public int getStatus() {
		return status;
	}
 
	public void setStatus(int status) {
		this.status = status;
	}
 
	public String getMessage() {
		return message;
	}
 
	public void setMessage(String message) {
		this.message = message;
	}
}
