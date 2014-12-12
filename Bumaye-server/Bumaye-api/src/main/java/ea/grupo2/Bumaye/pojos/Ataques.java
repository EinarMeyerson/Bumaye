package ea.grupo2.Bumaye.pojos;

import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.ManyToOne;

@Entity
public class Ataques {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idAtaque")
	private int idAtaque;
	@Column(unique = true, name="nombre")
	private String nombre;
	@Column(name="AtributoAfectado")
	private String AtributoAfectado;
	@Column(name="JugadorAfectado")
	private int JugadorAfectado;
	@Column(name="FactorDaño")
	private float FactorDaño;
	@Column(name="PorcentajeAcierto")
	private float PorcentajeAcierto;
	@Column(name="VecesUso")
	private float VecesUso;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idarmasarmaduras")
	private ArmasArmaduras armasarmaduras;
	
	public Ataques (){
	}
	
	public Ataques(String nombre, String AtributoAfectado, int JugadorAfectado, float FactorDaño, float PorcentajeAcierto, int VecesUso) {
		this.setNombre(nombre);
		this.setAtributoAfectado(AtributoAfectado);
		this.setJugadorAfectado(JugadorAfectado);
		this.setFactorDaño(FactorDaño);
		this.setPorcentajeAcierto(PorcentajeAcierto);
		this.setVecesUso(VecesUso);
	}
	
	
	
	public int getIdAtaque() {
		return idAtaque;
	}
	public void setIdAtaque(int idAtaque) {
		this.idAtaque = idAtaque;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getAtributoAfectado() {
		return AtributoAfectado;
	}
	public void setAtributoAfectado(String atributoAfectado) {
		AtributoAfectado = atributoAfectado;
	}
	public int getJugadorAfectado() {
		return JugadorAfectado;
	}
	public void setJugadorAfectado(int jugadorAfectado) {
		JugadorAfectado = jugadorAfectado;
	}
	public float getFactorDaño() {
		return FactorDaño;
	}
	public void setFactorDaño(float factorDaño) {
		FactorDaño = factorDaño;
	}
	public float getPorcentajeAcierto() {
		return PorcentajeAcierto;
	}
	public void setPorcentajeAcierto(float porcentajeAcierto) {
		PorcentajeAcierto = porcentajeAcierto;
	}

	public ArmasArmaduras getArmasarmaduras() {
		return armasarmaduras;
	}

	public void setArmasarmaduras(ArmasArmaduras armasarmaduras) {
		this.armasarmaduras = armasarmaduras;
	}

	public float getVecesUso() {
		return VecesUso;
	}

	public void setVecesUso(float vecesUso) {
		VecesUso = vecesUso;
	}

}
