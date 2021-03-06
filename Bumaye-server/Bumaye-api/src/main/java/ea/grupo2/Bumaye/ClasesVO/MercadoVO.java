package ea.grupo2.Bumaye.ClasesVO;


public class MercadoVO {

	private int idEntradaMercado;
	private int cantidad;
	private int precioUnidad;
	private PersonajeVO personaje;
	private ObjetoVO objeto;
	
	public MercadoVO(){
		
	}
	
	public MercadoVO(int idEntradaMercado, int cantidad, int precioUnidad,
			PersonajeVO personaje, ObjetoVO objeto) {
		super();
		this.idEntradaMercado = idEntradaMercado;
		this.cantidad = cantidad;
		this.precioUnidad = precioUnidad;
		this.personaje = personaje;
		this.objeto = objeto;
	}

	public int getIdEntradaMercado() {
		return idEntradaMercado;
	}

	public void setIdEntradaMercado(int idEntradaMercado) {
		this.idEntradaMercado = idEntradaMercado;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getPrecioUnidad() {
		return precioUnidad;
	}

	public void setPrecioUnidad(int preciounidad) {
		precioUnidad = preciounidad;
	}

	public PersonajeVO getPersonaje() {
		return personaje;
	}

	public void setPersonaje(PersonajeVO personaje) {
		this.personaje = personaje;
	}

	public ObjetoVO getObjeto() {
		return objeto;
	}

	public void setObjeto(ObjetoVO objeto) {
		this.objeto = objeto;
	}


	
}
