package ea.grupo2.Bumaye.ClasesVO;

import com.google.gson.annotations.SerializedName;

public class ObjetoCofreCantidadVO {
	@SerializedName("idobjetocofrecantidad")
	private int idobjetocofrecantidad;
	@SerializedName("cantidad")
	private int cantidad;
	@SerializedName("idobjeto")
	private int idobjeto;
	@SerializedName("idcofre")
	private int idcofre;
	@SerializedName("nombreObjeto")
	private String nombreObjeto;

	public ObjetoCofreCantidadVO() {
	}

	public ObjetoCofreCantidadVO(int idobjetocofrecantidad, int cantidad,
			int idobjeto, int idcofre, String nombreObjeto) {
		super();
		this.idobjetocofrecantidad = idobjetocofrecantidad;
		this.cantidad = cantidad;
		this.idobjeto = idobjeto;
		this.idcofre = idcofre;
	}

	public int getIdobjetocofrecantidad() {
		return idobjetocofrecantidad;
	}

	public void setIdobjetocofrecantidad(int idobjetocofrecantidad) {
		this.idobjetocofrecantidad = idobjetocofrecantidad;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getIdobjeto() {
		return idobjeto;
	}

	public void setIdobjeto(int idobjeto) {
		this.idobjeto = idobjeto;
	}

	public int getIdcofre() {
		return idcofre;
	}

	public void setIdcofre(int idcofre) {
		this.idcofre = idcofre;
	}

	public String getNombreObjeto() {
		return nombreObjeto;
	}

	public void setNombreObjeto(String nombreObjeto) {
		this.nombreObjeto = nombreObjeto;
	}

}
