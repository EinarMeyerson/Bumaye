package ea.grupo2.Bumaye.ClasesVO;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class UsuarioVO implements Serializable {
	@SerializedName("username")
	private String username;
	@SerializedName("pass")
	private String pass;
	@SerializedName("email")
	private String email;
	@SerializedName("idGCM")
	private String idGCM;
	@SerializedName("longitud")
	private double longitud;
	@SerializedName("latitud")
	private double latitud;

	public UsuarioVO() {
	}

	public UsuarioVO(String idGCM, String username, String pass, String email,
			double latitud, double longitud) {
		super();
		this.idGCM = idGCM;
		this.username = username;
		this.pass = pass;
		this.email = email;
		this.longitud = longitud;
		this.latitud = latitud;
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

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
}
