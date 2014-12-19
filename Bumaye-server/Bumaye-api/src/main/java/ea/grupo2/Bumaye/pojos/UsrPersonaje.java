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
public class UsrPersonaje {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="iduser")
    private int iduser;
    @Column(unique = true, name="idGCM")
    private String idGCM;
    @Column(unique = true, name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(unique = true, name="nombre")
    private String nombre;
    @Column(name="vida")
    private float vida;
    @Column(name="defensa")
    private float defensa;
    @Column(name="ataque")
    private float ataque;   
     
    @ManyToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "UsrPersonaje_Batallas", joinColumns = { 
            @JoinColumn(name = "iduser")}, inverseJoinColumns = { @JoinColumn(name = "idBatalla")})
            private List<Batalla> batallas;
 
     
    @ManyToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "UsrPersonaje_ArmasArmaduras", joinColumns = { 
            @JoinColumn(name = "iduser")}, inverseJoinColumns = { @JoinColumn(name = "idArmasArmaduras")})
    private List<ArmasArmaduras> armasarmaduras;
     
    public UsrPersonaje (){
    }
     
     
    public UsrPersonaje(String username,String idGCM, String password, String nombre, float vida, float defensa, float ataque) {
    	this.idGCM = idGCM;
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.vida = vida;
        this.defensa = defensa;
        this.ataque = ataque;
    }
 
 
    public int getIduser() {
        return iduser;
    }
    public void setIduser(int iduser) {
        this.iduser = iduser;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
 
    public float getVida() {
        return vida;
    }
 
 
    public void setVida(float vida) {
        this.vida = vida;
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
 
    public void addArmasArmaduras(ArmasArmaduras armaarmadura) {
        armasarmaduras.add(armaarmadura);
    }
 
 
    public List<ArmasArmaduras> getArmasarmaduras() {
        return armasarmaduras;
    }
 
 
    public void setArmasarmaduras(List<ArmasArmaduras> armasarmaduras) {
        this.armasarmaduras = armasarmaduras;
    }
 
    public void addBatalla(Batalla batalla) {
        batallas.add(batalla);
    }
     
    public List<Batalla> getBatallas() {
        return batallas;
    }
 
 
    public void setBatallas(List<Batalla> batallas) {
        this.batallas = batallas;
    }


	public String getIdGCM() {
		return idGCM;
	}


	public void setIdGCM(String idGCM) {
		this.idGCM = idGCM;
	}
 
     
}
