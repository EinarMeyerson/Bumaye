package ea.grupo2.Bumaye.ClasesVO;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class AtaqueVO implements Serializable{
	@SerializedName("idataque")
	private int idataque;
	@SerializedName("nombreataque")
	private String nombreataque;
	@SerializedName("atributoafectado")
	private String atributoafectado;
	@SerializedName("jugadorafectado")
	private int jugadorafectado;
	@SerializedName("factor")
	private float factor;
	@SerializedName("acierto")
	private float acierto;
	@SerializedName("vecesuso")
	private float vecesuso;

	public AtaqueVO() {
	}

	public AtaqueVO(int idataque, String nombreataque, String atributoafectado,
			float factor, float acierto, int jugadorafectado, float vecesuso) {
		this.setIdataque(idataque);
		this.setNombreataque(nombreataque);
		this.setAtributoafectado(atributoafectado);
		this.setFactor(factor);
		this.setAcierto(acierto);
		this.setJugadorafectado(jugadorafectado);
		this.setVecesuso(vecesuso);
	}

	public int getIdataque() {
		return idataque;
	}

	public void setIdataque(int idataque) {
		this.idataque = idataque;
	}

	public String getNombreataque() {
		return nombreataque;
	}

	public void setNombreataque(String nombreataque) {
		this.nombreataque = nombreataque;
	}

	public String getAtributoafectado() {
		return atributoafectado;
	}

	public void setAtributoafectado(String atributoafectado) {
		this.atributoafectado = atributoafectado;
	}

	public float getFactor() {
		return factor;
	}

	public void setFactor(float factor) {
		this.factor = factor;
	}

	public float getAcierto() {
		return acierto;
	}

	public void setAcierto(float acierto) {
		this.acierto = acierto;
	}

	public int getJugadorafectado() {
		return jugadorafectado;
	}

	public void setJugadorafectado(int jugadorafectado) {
		this.jugadorafectado = jugadorafectado;
	}

	public float getVecesuso() {
		return vecesuso;
	}

	public void setVecesuso(float vecesuso) {
		this.vecesuso = vecesuso;
	}

}
