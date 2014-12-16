package ea.grupo2.Bumaye.ClasesVO;

import com.google.gson.annotations.SerializedName;

public class BatallaVO {
	@SerializedName("idbatalla")
	int idbatalla;
	@SerializedName("ataquante")
	PersonajeVO ataquante = null;
	@SerializedName("defensor")
	PersonajeVO defensor = null;
	@SerializedName("turno")
	int turno;

	   public BatallaVO (){
		}

	public BatallaVO(int idbatalla, PersonajeVO ataquante, PersonajeVO defensor , int turno) {
		this.idbatalla = idbatalla;
		this.ataquante = ataquante;
		this.defensor =defensor;
		this.turno=turno;
	}
	
	public int getIdbatalla() {
		return idbatalla;
	}
	public void setIdbatalla(int idbatalla) {
		this.idbatalla = idbatalla;
	}
	public PersonajeVO getAtaquante() {
		return ataquante;
	}
	public void setAtaquante(PersonajeVO ataquante) {
		this.ataquante = ataquante;
	}
	public PersonajeVO getDefensor() {
		return defensor;
	}
	public void setDefensor(PersonajeVO defensor) {
		this.defensor = defensor;
	}

	public int getTurno() {
		return turno;
	}

	public void setTurno(int turno) {
		this.turno = turno;
	}

	
	
}
