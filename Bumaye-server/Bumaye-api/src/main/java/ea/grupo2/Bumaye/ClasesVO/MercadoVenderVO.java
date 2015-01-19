package ea.grupo2.Bumaye.ClasesVO;


public class MercadoVenderVO {

	private int idEntradaMercado;
	private int cantidad;
	private int precioUnidad;
	private int idpersonaje;
	private int idobjeto;
	
	public MercadoVenderVO(){
		
	}
	
	public MercadoVenderVO(int idEntradaMercado, int cantidad, int precioUnidad,
			int idpersonaje, int idobjeto) {
		super();
		this.idEntradaMercado = idEntradaMercado;
		this.cantidad = cantidad;
		this.precioUnidad = precioUnidad;
		this.idpersonaje = idpersonaje;
		this.idobjeto = idobjeto;
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

	public int getIdpersonaje() {
		return idpersonaje;
	}

	public void setIdpersonaje(int idpersonaje) {
		this.idpersonaje = idpersonaje;
	}

	public int getIdobjeto() {
		return idobjeto;
	}

	public void setIdobjeto(int idobjeto) {
		this.idobjeto = idobjeto;
	}


}
