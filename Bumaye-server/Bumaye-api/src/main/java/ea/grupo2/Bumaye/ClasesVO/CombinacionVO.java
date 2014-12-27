package ea.grupo2.Bumaye.ClasesVO;

public class CombinacionVO {
	
	private String combo1;
	private String combo2;
	int iduser;

	public CombinacionVO (){
	}
	
	public CombinacionVO(int iduser, String combo1, String combo2) {
		super();
		this.iduser = iduser;
		this.combo1 = combo1;
		this.combo2 = combo2;
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

	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}
	
}
