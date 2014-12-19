package ea.grupo2.Bumaye.ClasesVO;

import com.google.gson.annotations.SerializedName;

public class UsuarioVO {
	@SerializedName("username")
	private String username;
	@SerializedName("pass")
	private String pass;
	@SerializedName("email")
	private String email;
	@SerializedName("idGCM")
	private String idGCM;
	public UsuarioVO (){
	}

public UsuarioVO(String username, String pass, String email, String idGCM) {
	this.username=username;
	this.pass=pass;
	this.email=email;
	this.idGCM=idGCM;
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
