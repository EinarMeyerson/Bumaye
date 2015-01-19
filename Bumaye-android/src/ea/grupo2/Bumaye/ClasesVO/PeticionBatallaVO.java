package ea.grupo2.Bumaye.ClasesVO;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class PeticionBatallaVO implements Serializable{
	@SerializedName("idatacante")
	int idatacante;
	@SerializedName("iddefensor")
	int iddefensor;
	@SerializedName("aceptada")
	String aceptada;//Aceptada= "Si"  o   Aceptada="No"

	public PeticionBatallaVO (){
	}

	public PeticionBatallaVO(int idatacante, int iddefensor) {
		super();
		this.idatacante = idatacante;
		this.iddefensor = iddefensor;
		this.aceptada = "No";

	}

	public int getIdatacante() {
		return idatacante;
	}
	public void setIdatacante(int idatacante) {
		this.idatacante = idatacante;
	}
	public int getIddefensor() {
		return iddefensor;
	}
	public void setIddefensor(int iddefensor) {
		this.iddefensor = iddefensor;
	}
	public String getAceptada() {
		return aceptada;
	}
	public void setAceptada(String aceptada) {
		this.aceptada = aceptada;
	}
	
}
