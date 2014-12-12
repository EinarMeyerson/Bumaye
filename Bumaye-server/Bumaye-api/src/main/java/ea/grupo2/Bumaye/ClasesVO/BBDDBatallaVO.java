package ea.grupo2.Bumaye.ClasesVO;

public class BBDDBatallaVO {
	
	int idBatalla;
	String AtacoDefens;
	PersonajeVO ganador;
	
	
	public BBDDBatallaVO(){
		
	}
	
	public BBDDBatallaVO(int idBatalla, String atacoDefens, PersonajeVO ganador) {
		super();
		this.idBatalla = idBatalla;
		AtacoDefens = atacoDefens;
		this.ganador = ganador;
	}
	public int getIdBatalla() {
		return idBatalla;
	}
	public void setIdBatalla(int idBatalla) {
		this.idBatalla = idBatalla;
	}
	public String getAtacoDefens() {
		return AtacoDefens;
	}
	public void setAtacoDefens(String atacoDefens) {
		AtacoDefens = atacoDefens;
	}
	public PersonajeVO getGanador() {
		return ganador;
	}
	public void setGanador(PersonajeVO ganador) {
		this.ganador = ganador;
	}
	

}
