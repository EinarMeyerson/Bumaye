package ea.grupo2.Bumaye.ClasesVO;

public class PeticionBatallaVO {
	int idatacante;
	int iddefensor;

	public PeticionBatallaVO (){
	}

	public PeticionBatallaVO(int idatacante, int iddefensor) {
		super();
		this.idatacante = idatacante;
		this.iddefensor = iddefensor;

	}

	public int getIdatacante() {
		return idatacante;
	}
	public void setIdatacante(int idatacante) {
		this.idatacante = idatacante;
	}
	public int getIddefensor() {
		return iddefensor;
	}
	public void setIddefensor(int iddefensor) {
		this.iddefensor = iddefensor;
	}
}
