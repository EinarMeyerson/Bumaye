package ea.grupo2.Bumaye.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Objeto {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idobjeto")
	private int idobjeto;
	@Column(unique = true, name="nombre")
	private String nombre;
	@Column(name="rareza")
	private float rareza;
	@Column(name="tipo")
	private String tipo;
	@Column(name="combo1")
	private String combo1;
	@Column(name="combo2")
	private String combo2;
	@Column(name="exito")
	private float exito;
	
	public Objeto (){
    }
     
     
    public Objeto(String nombre, String tipo, float rareza, String combo1, String combo2, float exito) {
    	
        this.nombre = nombre;
        this.rareza = rareza;
        this.tipo = tipo;
        this.combo1 = combo1;
        this.combo2 = combo2;
        this.exito = exito;
    }
	
	public int getIdobjeto() {
		return idobjeto;
	}
	public void setIdobjeto(int idobjeto) {
		this.idobjeto = idobjeto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public float getRareza() {
		return rareza;
	}
	public void setRareza(float rareza) {
		this.rareza = rareza;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getCombo1() {
		return combo1;
	}
	public void setCombo1(String combo1) {
		this.combo1 = combo1;
	}
	public String getCombo2() {
		return combo2;
	}
	public void setCombo2(String combo2) {
		this.combo2 = combo2;
	}
	public float getExito() {
		return exito;
	}
	public void setExito(float exito) {
		this.exito = exito;
	}
}
