package ea.grupo2.Bumaye.ClasesVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class PersonajeVO implements Serializable{
	@SerializedName("iduser")
	private int iduser;
	@SerializedName("idGCM")
	private String idGCM;
	@SerializedName("nombre")
	private String nombre;
	@SerializedName("vida")
	private float vida;
	@SerializedName("defensa")
	private float defensa;
	@SerializedName("ataque")
	private float ataque;
	@SerializedName("armasarmaduras")
	private List<ArmaArmaduraVO> armasarmaduras = new ArrayList<ArmaArmaduraVO>();
//	@SerializedName("ataques")
//	private List<AtaqueVO> ataques = new ArrayList<AtaqueVO>();

   public PersonajeVO (){
	}
	

public PersonajeVO(int iduser, String idGCM, String nombre, float vida,float defensa, float ataque) {
	this.iduser = iduser;
	this.idGCM = idGCM;
	this.nombre = nombre;
	this.vida = vida;
	this.defensa = defensa;
	this.ataque = ataque;
}
public PersonajeVO getPersonajeVO()
{
	PersonajeVO personaje = new PersonajeVO(this.iduser,this.idGCM, this.nombre, this.vida, this.defensa, this.ataque);
	return personaje;
}


//funcion que actualiza los atributos afectados por un ataque
public void getActualizacionAtributo(String atributo, float nuevoatributo)
{
	if (atributo =="vida")
	{
		System.out.print("vida actualizada: "+ nuevoatributo+"\n");

		this.vida= nuevoatributo;
	}

	else if (atributo=="defensa")
	{
		System.out.print("defensa actualizada: "+ nuevoatributo+"\n");

		this.defensa= nuevoatributo;
	}

	else if (atributo=="ataque")
	{
		System.out.print("ataque actualizada: "+ nuevoatributo+"\n");

		this.ataque= nuevoatributo;
	}
	else{
		System.out.print("ERRRORR getActualizacionAtributo");

	}
}

public int getIduser() {
	return iduser;
}
public void setIduser(int iduser) {
	this.iduser = iduser;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}

public void addArmasArmaduras(ArmaArmaduraVO armasarmadura) {
	armasarmaduras.add(armasarmadura);
}
//public void addAtaques(AtaqueVO ataque) {
//	ataques.add(ataque);
//}
public float getVida() {
	return vida;
}

public void setVida(float vida) {
	this.vida = vida;
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


public List<ArmaArmaduraVO> getArmasarmaduras() {
	return armasarmaduras;
}


public void setArmasarmaduras(List<ArmaArmaduraVO> armasarmaduras) {
	this.armasarmaduras = armasarmaduras;
}


public String getIdGCM() {
	return idGCM;
}


public void setIdGCM(String idGCM) {
	this.idGCM = idGCM;
}

//public List<AtaqueVO> getAtaques() {
//	return ataques;
//}
//
//
//public void setAtaques(List<AtaqueVO> ataques) {
//	this.ataques = ataques;
//}



   
}
