package ea.grupo2.Bumaye.ClasesVO;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class ObjetoCantidadVO implements Serializable {
	@SerializedName("idobjeto")
	private int idobjeto;
	@SerializedName("nombre")
	private String nombre;
	@SerializedName("rareza")
	private float rareza;
	@SerializedName("tipo")
	private String tipo;
	@SerializedName("combo1")
	private String combo1;
	@SerializedName("combo2")
	private String combo2;
	@SerializedName("exito")
	private float exito;
	@SerializedName("cantidad")
	private int cantidad;

	public ObjetoCantidadVO() {
	}

	public ObjetoCantidadVO(int idobjeto, String nombre, String tipo,
			float rareza, String combo1, String combo2, float exito,
			int cantidad) {
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}
