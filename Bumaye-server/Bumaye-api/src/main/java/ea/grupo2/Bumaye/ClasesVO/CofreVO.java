package ea.grupo2.Bumaye.ClasesVO;

import java.util.HashMap;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import ea.grupo2.Bumaye.pojos.Objeto;
import ea.grupo2.Bumaye.pojos.ObjetoCofreCantidad;

public class CofreVO {

	private int idcofre;
	private float longitud;
	private float latitud;
	private List<ObjetoVO> lista_objetos;
	private List<ObjetoCofreCantidadVO> objetoscofrecantidads;
	
	HashMap<String, ObjetoVO> mapObjetoVO = new HashMap<String, ObjetoVO>();
	HashMap<Integer, ObjetoCofreCantidadVO> mapObjetoCofreCantidadVO = new HashMap<Integer, ObjetoCofreCantidadVO>();
	
	public CofreVO (){
    }
	
	public CofreVO(int idcofre, float longitud, float latitud) {
		super();
		this.idcofre = idcofre;
		this.longitud = longitud;
		this.latitud = latitud;
	}

	public void addlistaobjetos(ObjetoVO objetoVO) {
		mapObjetoVO.put(objetoVO.getNombre(), objetoVO);
		lista_objetos.add(objetoVO);

	}
	public void addobjetoscofrecantidads(ObjetoCofreCantidadVO objetocofreCantidadVO) {
		mapObjetoCofreCantidadVO.put(objetocofreCantidadVO.getIdobjetocofrecantidad(), objetocofreCantidadVO);
		objetoscofrecantidads.add(objetocofreCantidadVO);
	}


	public int getIdcofre() {
		return idcofre;
	}

	public void setIdcofre(int idcofre) {
		this.idcofre = idcofre;
	}

	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	public float getLatitud() {
		return latitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	public List<ObjetoVO> getLista_objetos() {
		return lista_objetos;
	}

	public void setLista_objetos(List<ObjetoVO> lista_objetos) {
		this.lista_objetos = lista_objetos;
	}

	public List<ObjetoCofreCantidadVO> getObjetoscofrecantidads() {
		return objetoscofrecantidads;
	}

	public void setObjetoscofrecantidads(
			List<ObjetoCofreCantidadVO> objetoscofrecantidads) {
		this.objetoscofrecantidads = objetoscofrecantidads;
	}

	public HashMap<String, ObjetoVO> getMapObjetoVO() {
		return mapObjetoVO;
	}

	public void setMapObjetoVO(HashMap<String, ObjetoVO> mapObjetoVO) {
		for (ObjetoVO obj : lista_objetos) this.mapObjetoVO.put(obj.getNombre(),obj);
		this.mapObjetoVO = mapObjetoVO;
	}

	public HashMap<Integer, ObjetoCofreCantidadVO> getMapObjetoCofreCantidadVO() {
		return mapObjetoCofreCantidadVO;
	}

	public void setMapObjetoCofreCantidadVO(
			HashMap<Integer, ObjetoCofreCantidadVO> mapObjetoCofreCantidadVO) {
		for (ObjetoCofreCantidadVO objcofre : objetoscofrecantidads) this.mapObjetoCofreCantidadVO.put(objcofre.getIdobjetocofrecantidad(),objcofre);
		this.mapObjetoCofreCantidadVO = mapObjetoCofreCantidadVO;
	}
     
}
