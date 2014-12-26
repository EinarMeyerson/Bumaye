package ea.grupo2.Bumaye.ClasesVO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


public class ObjetoCofreCantidadVO {

	private int idobjetocofrecantidad;
	private int cantidad;
	private int idobjeto;
	private int idcofre;

	
	public ObjetoCofreCantidadVO (){
    }


	public ObjetoCofreCantidadVO(int idobjetocofrecantidad, int cantidad,
			int idobjeto, int idcofre) {
		super();
		this.idobjetocofrecantidad = idobjetocofrecantidad;
		this.cantidad = cantidad;
		this.idobjeto = idobjeto;
		this.idcofre = idcofre;
	}

	
	public int getIdobjetocofrecantidad() {
		return idobjetocofrecantidad;
	}


	public void setIdobjetocofrecantidad(int idobjetocofrecantidad) {
		this.idobjetocofrecantidad = idobjetocofrecantidad;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public int getIdobjeto() {
		return idobjeto;
	}


	public void setIdobjeto(int idobjeto) {
		this.idobjeto = idobjeto;
	}


	public int getIdcofre() {
		return idcofre;
	}


	public void setIdcofre(int idcofre) {
		this.idcofre = idcofre;
	}
     
}
