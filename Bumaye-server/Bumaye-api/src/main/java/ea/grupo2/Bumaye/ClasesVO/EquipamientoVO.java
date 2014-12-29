package ea.grupo2.Bumaye.ClasesVO;

import java.util.List;

public class EquipamientoVO {
	private int iduser;
	private int idarmequipada;
	private int idesequipada;

	public EquipamientoVO() {
	}

	public EquipamientoVO(int idarmequipada, int iduser, int idesequipada) {

		this.idarmequipada = idarmequipada;
		this.iduser=iduser;
		this.idesequipada=idesequipada;
	}

	public int getIdarmequipada() {
		return idarmequipada;
	}

	public void setIdarmequipada(int idarmequipada) {
		this.idarmequipada = idarmequipada;
	}

	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public int getIdesequipada() {
		return idesequipada;
	}

	public void setIdesequipada(int idesequipada) {
		this.idesequipada = idesequipada;
	}
	
}
