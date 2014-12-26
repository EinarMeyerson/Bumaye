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
public class ObjetoCofreCantidad {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idobjetocofrecantidad")
	private int idobjetocofrecantidad;
	@Column(name="cantidad")
	private int cantidad;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idobjeto")
	private Objeto objeto;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idCofre")
	private Cofre cofre;

	
	public ObjetoCofreCantidad (){
    }

	public ObjetoCofreCantidad(int idobjetocofrecantidad, int cantidad) {
		super();
		this.idobjetocofrecantidad = idobjetocofrecantidad;
		this.cantidad = cantidad;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getIdobjetocofrecantidad() {
		return idobjetocofrecantidad;
	}

	public Objeto getObjeto() {
		return objeto;
	}

	public void setObjeto(Objeto objeto) {
		this.objeto = objeto;
	}

	public Cofre getCofre() {
		return cofre;
	}

	public void setCofre(Cofre cofre) {
		this.cofre = cofre;
	}

     
     
}
