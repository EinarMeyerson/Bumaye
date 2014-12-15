package ea.grupo2.Bumaye.ClasesVO;

public class PersonajeLogeadoVO {
	
	   private int iduser;
	   private String nombre;
	   private float vida;
	   private float defensa;
	   private float ataque;
	   
	   public PersonajeLogeadoVO (){
		}
		

	public PersonajeLogeadoVO(int iduser, String nombre, float vida,float defensa, float ataque) {
		this.iduser = iduser;
		this.nombre = nombre;
		this.vida = vida;
		this.defensa = defensa;
		this.ataque = ataque;
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

}
