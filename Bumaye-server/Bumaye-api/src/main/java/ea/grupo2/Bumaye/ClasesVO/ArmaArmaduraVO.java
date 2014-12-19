package ea.grupo2.Bumaye.ClasesVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArmaArmaduraVO {
	private int idarmaarmadura;
	private String nombrearmaarmadura;
	private String tipo;
	private float defensa;
	private float ataque;
	private List<AtaqueVO> ataques = new ArrayList<AtaqueVO>();
	
	public ArmaArmaduraVO (){
	}
	
	public ArmaArmaduraVO(int idarmaarmadura, String nombrenombrearm,
			String tipo, float defensa, float ataque) {
		this.idarmaarmadura = idarmaarmadura;
		this.nombrearmaarmadura = nombrenombrearm;
		this.tipo = tipo;
		this.defensa = defensa;
		this.ataque = ataque;
	}

	public int getIdarmaarmadura() {
		return idarmaarmadura;
	}
	public void setIdarmaarmadura(int idarmaarmadura) {
		this.idarmaarmadura = idarmaarmadura;
	}
	public String getNombre() {
		return nombrearmaarmadura;
	}
	public void setNombre(String nombre) {
		this.nombrearmaarmadura = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public float getDefensa() {
		return defensa;
	}
	public void setDefensa(float defensa) {
		this.defensa = defensa;
	}
	public float getAtaque() {
		return ataque;
	}
	public void setAtaque(float ataque) {
		this.ataque = ataque;
	}


	public List<AtaqueVO> getAtaques() {
		return ataques;
	}

	public void setAtaques(List<AtaqueVO> ataques) {
		this.ataques = ataques;
	}
	public void addAtaques(AtaqueVO ataque) {
		ataques.add(ataque);
	}

}
