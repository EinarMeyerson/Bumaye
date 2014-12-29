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
public class ArmaArmaduraEquipada {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idararmequipada")
	private int idararmequipada;
	@Column(name="equipada")
	private int equipada;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idArmasArmaduras")
	private ArmasArmaduras armaarmadura;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idUser")
	private UsrPersonaje usrPersonaje;
	
	public ArmaArmaduraEquipada (){
    }

	public ArmaArmaduraEquipada(int idararmequipada, int idarmaarmadura, int idUser,
			int equipada) {
		super();
		this.idararmequipada = idararmequipada;
		this.equipada = equipada;
	}

	public int getIdararmequipada() {
		return idararmequipada;
	}

	public void setIdararmequipada(int idararmequipada) {
		this.idararmequipada = idararmequipada;
	}

	public int getEquipada() {
		return equipada;
	}

	public void setEquipada(int equipada) {
		this.equipada = equipada;
	}

	public ArmasArmaduras getArmasArmaduras() {
		return armaarmadura;
	}

	public void setArmasArmaduras(ArmasArmaduras idarmaarmadura) {
		this.armaarmadura = idarmaarmadura;
	}

	public UsrPersonaje getUsrPersonaje() {
		return usrPersonaje;
	}

	public void setUsrPersonaje(UsrPersonaje usrPersonaje) {
		this.usrPersonaje = usrPersonaje;
	}
}
