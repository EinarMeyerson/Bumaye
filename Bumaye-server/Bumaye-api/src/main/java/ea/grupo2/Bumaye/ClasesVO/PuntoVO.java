package ea.grupo2.Bumaye.ClasesVO;


public class PuntoVO {

	private double longitud;
	private double latitud;
	
	public PuntoVO (){
    }
	
	public PuntoVO(double latitud, double longitud) {
		super();
		this.longitud = longitud;
		this.latitud = latitud;
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
