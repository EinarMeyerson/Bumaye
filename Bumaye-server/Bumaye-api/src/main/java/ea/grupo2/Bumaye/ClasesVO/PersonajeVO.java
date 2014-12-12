package ea.grupo2.Bumaye.ClasesVO;

import java.util.ArrayList;
import java.util.List;

import ea.grupo2.Bumaye.pojos.ArmasArmaduras;

public class PersonajeVO {
   private int iduser;
   private String nombre;
   private float vida;
   private float defensa;
   private float ataque;
   private List<ArmaArmaduraVO> armasarmaduras;
   private List<AtaqueVO> ataques;

   
   public PersonajeVO (){
	}
	

public PersonajeVO(int iduser, String nombre, float vida,float defensa, float ataque) {
	this.iduser = iduser;
	this.nombre = nombre;
	this.vida = vida;
	this.defensa = defensa;
	this.ataque = ataque;
}
public PersonajeVO getPersonajeVO()
{
	PersonajeVO personaje = new PersonajeVO(this.iduser, this.nombre, this.vida, this.defensa, this.ataque);
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
public void addAtaques(AtaqueVO ataque) {
	ataques.add(ataque);
}
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

public List<AtaqueVO> getAtaques() {
	return ataques;
}


public void setAtaques(List<AtaqueVO> ataques) {
	this.ataques = ataques;
}



   
}
