package ea.grupo2.Bumaye.ClasesVO;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class CofreVO {
	@SerializedName("idcofre")
	private int idcofre;
	@SerializedName("longitud")
	private float longitud;
	@SerializedName("latitud")
	private float latitud;
	@SerializedName("lista_objetos")
	private List<ObjetoCantidadVO> lista_objetos;

	public CofreVO() {
	}

	public CofreVO(float longitud, float latitud) {

		this.longitud = longitud;
		this.latitud = latitud;
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

	public List<ObjetoCantidadVO> getLista_objetos() {
		return lista_objetos;
	}

	public void setLista_objetos(List<ObjetoCantidadVO> lista_objetos) {
		this.lista_objetos = lista_objetos;
	}

}
