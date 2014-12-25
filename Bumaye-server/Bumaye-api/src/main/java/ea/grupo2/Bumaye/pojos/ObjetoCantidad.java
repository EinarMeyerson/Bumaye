package ea.grupo2.Bumaye.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ObjetoCantidad {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idobjetocantidad")
	private int idobjetocantidad;
	@Column(name="cantidad")
	private int cantidad;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idobjeto")
	private Objeto objeto;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idUser")
	private UsrPersonaje usrPersonaje;

	
	public ObjetoCantidad (){
    }

	public ObjetoCantidad(int idobjetocantidad, int idobjeto, int idUser,
			int cantidad) {
		super();
		this.idobjetocantidad = idobjetocantidad;
		this.cantidad = cantidad;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getIdobjetocantidad() {
		return idobjetocantidad;
	}

	public void setIdobjetocantidad(int idobjetocantidad) {
		this.idobjetocantidad = idobjetocantidad;
	}

	public Objeto getObjeto() {
		return objeto;
	}

	public void setObjeto(Objeto objeto) {
		this.objeto = objeto;
	}

	public UsrPersonaje getUsrPersonaje() {
		return usrPersonaje;
	}

	public void setUsrPersonaje(UsrPersonaje usrPersonaje) {
		this.usrPersonaje = usrPersonaje;
	}
     
     
}
