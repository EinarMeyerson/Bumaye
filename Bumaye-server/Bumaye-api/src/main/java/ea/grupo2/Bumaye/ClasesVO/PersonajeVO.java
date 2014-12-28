package ea.grupo2.Bumaye.ClasesVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ea.grupo2.Bumaye.pojos.ArmasArmaduras;

public class PersonajeVO {
	private int iduser;
	private String idGCM;
	private String nombre;
	private float vida;
	private float defensa;
	private float ataque;
	private List<ArmaArmaduraVO> armasarmaduras;
	private List<AtaqueVO> ataques;
	private List<ObjetoCantidadVO> inventario;
	
	HashMap<Integer, ArmaArmaduraVO> mapArmaArmaduraVO = new HashMap<Integer, ArmaArmaduraVO>();
	HashMap<Integer, AtaqueVO> mapAtaqueVO = new HashMap<Integer, AtaqueVO>();
	HashMap<String, ObjetoCantidadVO> mapObjetoVO = new HashMap<String, ObjetoCantidadVO>();


	public PersonajeVO (){
	}


	public PersonajeVO(int iduser,String idGCM, String nombre, float vida,float defensa, float ataque) {
		this.iduser = iduser;
		this.idGCM = idGCM;
		this.nombre = nombre;
		this.vida = vida;
		this.defensa = defensa;
		this.ataque = ataque;
	}
	public PersonajeVO getPersonajeVO()
	{
		PersonajeVO personaje = new PersonajeVO(this.iduser, this.idGCM, this.nombre, this.vida, this.defensa, this.ataque);
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
		mapArmaArmaduraVO.put(armasarmadura.getIdarmaarmadura(), armasarmadura);
		armasarmaduras.add(armasarmadura);

	}
	public void addAtaques(AtaqueVO ataque) {
		mapAtaqueVO.put(ataque.getIdataque(), ataque);
		ataques.add(ataque);
	}

    public AtaqueVO getAtaqueVO(int idAtaqueVO) {
    	AtaqueVO ataqueVO = mapAtaqueVO.get(idAtaqueVO);
        return ataqueVO;
        
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
		for (ArmaArmaduraVO arm : armasarmaduras) mapArmaArmaduraVO.put(arm.getIdarmaarmadura(),arm);

	}

	public List<AtaqueVO> getAtaques() {
		return ataques;
	}


	public void setAtaques(List<AtaqueVO> ataques) {
		this.ataques = ataques;
		for (AtaqueVO a : ataques) mapAtaqueVO.put(a.getIdataque(),a);
	}

    public ArmaArmaduraVO getArmaArmaduraVO(int idArmaArmaduraVO) {
    	ArmaArmaduraVO armaArmaduraVO = mapArmaArmaduraVO.get(idArmaArmaduraVO);
        return armaArmaduraVO;
        
    }


	public String getIdGCM() {
		return idGCM;
	}


	public void setIdGCM(String idGCM) {
		this.idGCM = idGCM;
	}


	public List<ObjetoCantidadVO> getInventario() {
		return inventario;
	}


	public void setInventario(List<ObjetoCantidadVO> inventario) {
		for (ObjetoCantidadVO objcan : inventario) mapObjetoVO.put(objcan.getNombre(),objcan);
		this.inventario = inventario;
	}
	public void addInventario(ObjetoCantidadVO objeto) {
		mapObjetoVO.put(objeto.getNombre(), objeto);
		inventario.add(objeto);
	}
	
    public ObjetoCantidadVO getObjetoCantidadVO(String nombreObjeto) {
    	ObjetoCantidadVO objetocantidadVO = mapObjetoVO.get(nombreObjeto);
        return objetocantidadVO;
        
    }

}
