package ea.grupo2.Bumaye.pojos;

import java.util.HashSet;
import java.util.List;
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
import javax.persistence.OneToMany;

@Entity
public class ArmasArmaduras {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idArmasArmaduras")
	private int idArmasArmaduras;
	@Column(unique = true, name="nombre")
	private String nombre;
	@Column(name="tipo")
	private String tipo;
	@Column(name="defensa")
	private float defensa;
	@Column(name="ataque")
	private float ataque;
	
	@OneToMany(mappedBy="armasarmaduras", fetch=FetchType.LAZY, cascade = {CascadeType.ALL})
	private List<Ataques> ataques;
	
	public ArmasArmaduras (){
	}
	
	public ArmasArmaduras(String nombre, String tipo, float defensa, float ataque) {
		this.setNombre(nombre);
		this.setTipo(tipo);
		this.setDefensa(defensa);
		this.setAtaque(ataque);;
	}

	public int getIdArmasArmaduras() {
		return idArmasArmaduras;
	}

	public void setIdArmasArmaduras(int idArmasArmaduras) {
		this.idArmasArmaduras = idArmasArmaduras;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public List<Ataques> getAtaques() {
		return ataques;
	}

	public void setAtaques(List<Ataques> ataques) {
		this.ataques = ataques;
	}
	
	public void addAtaque(Ataques ataque) {
		ataques.add(ataque);
	}
}
