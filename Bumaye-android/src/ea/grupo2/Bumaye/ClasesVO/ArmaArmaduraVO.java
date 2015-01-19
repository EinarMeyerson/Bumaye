package ea.grupo2.Bumaye.ClasesVO;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;



public class ArmaArmaduraVO {
	@SerializedName("idarmaarmadura")
	private int idarmaarmadura;
	@SerializedName("nombre")
	private String nombre;
	@SerializedName("tipo")
	private String tipo;
	@SerializedName("defensa")
	private float defensa;
	@SerializedName("equipada")
	private int equipada;
	@SerializedName("ataque")
	private float ataque;
	@SerializedName("ataques")
	private List<AtaqueVO> ataques = new ArrayList<AtaqueVO>();
	
	public ArmaArmaduraVO (){
	}
	
	public ArmaArmaduraVO(int idarmaarmadura, String nombrenombrearm,
			String tipo, float defensa, float ataque, int equipada) {
		this.idarmaarmadura = idarmaarmadura;
		this.nombre = nombrenombrearm;
		this.tipo = tipo;
		this.defensa = defensa;
		this.equipada = equipada;
		this.ataque = ataque;
	}

	public int getIdarmaarmadura() {
		return idarmaarmadura;
	}
	public void setIdarmaarmadura(int idarmaarmadura) {
		this.idarmaarmadura = idarmaarmadura;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public String getNombrearmaarmadura() {
		return nombre;
	}

	public void setNombrearmaarmadura(String nombrearmaarmadura) {
		this.nombre = nombrearmaarmadura;
	}

	public List<AtaqueVO> getAtaques() {
		return ataques;
	}

	public void setAtaques(List<AtaqueVO> ataques) {
		this.ataques = ataques;
	}

	public int getEquipada() {
		return equipada;
	}

	public void setEquipada(int equipada) {
		this.equipada = equipada;
	}
	

}
