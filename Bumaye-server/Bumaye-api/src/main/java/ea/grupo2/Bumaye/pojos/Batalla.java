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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
 
@Entity
public class Batalla {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="idBatalla")
    private int idBatalla;
    @Column(name="Atacante/Defensor")
    private String AtacoDefens;
 
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="Ganador")
    private UsrPersonaje usrPersonaje;
     
    public Batalla(){
         
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

	public UsrPersonaje getUsrPersonaje() {
		return usrPersonaje;
	}

	public void setUsrPersonaje(UsrPersonaje usrPersonaje) {
		this.usrPersonaje = usrPersonaje;
	}
    
    
 
}