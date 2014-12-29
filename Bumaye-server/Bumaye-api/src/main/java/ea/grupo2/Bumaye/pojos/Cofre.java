package ea.grupo2.Bumaye.pojos;

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

@Entity
public class Cofre {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idcofre")
	private int idcofre;
	@Column(unique = true, name="longitud")
	private double longitud;
	@Column(unique = true, name="latitud")
	private double latitud;

	@ManyToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "Cofre_Objeto", joinColumns = { 
			@JoinColumn(name = "idcofre")}, inverseJoinColumns = { @JoinColumn(name = "idobjeto")})
	private List<Objeto> lista_objetos;
	
	 @OneToMany(mappedBy="cofre", fetch=FetchType.LAZY, cascade = {CascadeType.ALL})
		private List<ObjetoCofreCantidad> objetoscofrecantidads;
	
	public Cofre (){
    }
     
     
    public Cofre(double latitud, double longitud) {
    	
        this.longitud = longitud;
        this.latitud = latitud;
    }

	public int getIdcofre() {
		return idcofre;
	}

	public void setIdcofre(int idcofre) {
		this.idcofre = idcofre;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public List<Objeto> getLista_objetos() {
		return lista_objetos;
	}

	public void setLista_objetos(List<Objeto> lista_objetos) {
		this.lista_objetos = lista_objetos;
	}


	public List<ObjetoCofreCantidad> getObjetoscofrecantidads() {
		return objetoscofrecantidads;
	}


	public void setObjetoscofrecantidads(
			List<ObjetoCofreCantidad> objetoscofrecantidads) {
		this.objetoscofrecantidads = objetoscofrecantidads;
	}
	
	public void addObjetocofrecantidad(ObjetoCofreCantidad objetocofrecantidad) {
		objetoscofrecantidads.add(objetocofrecantidad);
    }
	
	public void addlistaobjetos(Objeto objeto) {
		lista_objetos.add(objeto);
    }
	public void removelistaobjetos(Objeto objeto) {
		lista_objetos.remove(objeto);
    }
}
