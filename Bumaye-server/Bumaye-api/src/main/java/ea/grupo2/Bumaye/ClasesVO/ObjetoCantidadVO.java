package ea.grupo2.Bumaye.ClasesVO;


public class ObjetoCantidadVO {
	
	private int idobjeto;
	private String nombre;
	private float rareza;
	private String tipo;
	private String combo1;
	private String combo2;
	private float exito;
	private int cantidad;
	public ObjetoCantidadVO (){
	}
	
	public ObjetoCantidadVO(int idobjeto, String nombre, float rareza, String tipo,
			String combo1, String combo2, float exito, int cantidad) {
		super();
		this.idobjeto = idobjeto;
		this.nombre = nombre;
		this.rareza = rareza;
		this.tipo = tipo;
		this.combo1 = combo1;
		this.combo2 = combo2;
		this.exito = exito;
		this.cantidad = cantidad;
	}




	public int getIdobjeto() {
		return idobjeto;
	}
	public void setIdobjeto(int idobjeto) {
		this.idobjeto = idobjeto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public float getRareza() {
		return rareza;
	}
	public void setRareza(float rareza) {
		this.rareza = rareza;
	}
	public String getCombo1() {
		return combo1;
	}
	public void setCombo1(String combo1) {
		this.combo1 = combo1;
	}
	public String getCombo2() {
		return combo2;
	}
	public void setCombo2(String combo2) {
		this.combo2 = combo2;
	}
	public float getExito() {
		return exito;
	}
	public void setExito(float exito) {
		this.exito = exito;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
