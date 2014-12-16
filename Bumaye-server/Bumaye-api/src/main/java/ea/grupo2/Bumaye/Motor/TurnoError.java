package ea.grupo2.Bumaye.Motor;

public class TurnoError {
	private int status;
	private String message;
 
	public TurnoError() {
		super();
	}
 
	public TurnoError(int status, String message) {
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
