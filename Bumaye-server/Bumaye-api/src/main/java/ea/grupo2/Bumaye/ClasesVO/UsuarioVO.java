package ea.grupo2.Bumaye.ClasesVO;

public class UsuarioVO {
	
	private String idGCM;
	private String username;
	private String pass;
	private String email;
	
	public UsuarioVO (){
	}

public UsuarioVO(String username,String idGCM, String pass, String email) {
	this.idGCM = idGCM;
	this.username=username;
	this.pass=pass;
	this.email=email;
}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdGCM() {
		return idGCM;
	}

	public void setIdGCM(String idGCM) {
		this.idGCM = idGCM;
	}
}
