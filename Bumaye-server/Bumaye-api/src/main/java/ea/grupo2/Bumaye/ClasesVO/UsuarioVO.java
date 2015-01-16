package ea.grupo2.Bumaye.ClasesVO;

public class UsuarioVO {
	
	private String idGCM;
	private String username;
	private String pass;
	private String email;
	private double longitud;
	private double latitud;
	private int laPasta;
	
	public UsuarioVO (){
	}

	
	public UsuarioVO(String idGCM, String username, String pass, String email,
			double latitud, double longitud, int laPasta) {
		super();
		this.idGCM = idGCM;
		this.username = username;
		this.pass = pass;
		this.email = email;
		this.longitud = longitud;
		this.latitud = latitud;
		this.laPasta = laPasta;
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


	public int getLaPasta() {
		return laPasta;
	}


	public void setLaPasta(int laPasta) {
		this.laPasta = laPasta;
	}
}
