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
public class Mercado {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="idEntradaMercado")
    private int idEntradaMercado;
    @Column(name="cantidad")
    private int cantidad;
    @Column(name="PrecioUnidad")
    private int precioUnidad;
    
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="iduserVendedor")
	private UsrPersonaje personaje;
    
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idobjetoEnVenta")
	private Objeto objeto;

	
	public Mercado (){
    }
	
	public Mercado(int cantidad, int precioUnidad,
			UsrPersonaje personaje, Objeto objeto) {
		super();
		this.cantidad = cantidad;
		this.precioUnidad = precioUnidad;
		this.personaje = personaje;
		this.objeto = objeto;
	}

	public int getIdEntradaMercado() {
		return idEntradaMercado;
	}

	public void setIdEntradaMercado(int idEntradaMercado) {
		this.idEntradaMercado = idEntradaMercado;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getPrecioUnidad() {
		return precioUnidad;
	}

	public void setPrecioUnidad(int preciounidad) {
		precioUnidad = preciounidad;
	}

	public UsrPersonaje getPersonaje() {
		return personaje;
	}

	public void setPersonaje(UsrPersonaje personaje) {
		this.personaje = personaje;
	}

	public Objeto getObjeto() {
		return objeto;
	}

	public void setObjeto(Objeto objeto) {
		this.objeto = objeto;
	}
	    
	

}
