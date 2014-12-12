package ea.grupo2.Bumaye.ClasesVO;
 
import java.util.ArrayList;
import java.util.HashMap;
 
public class BatallaVO {
     
    int idbatalla;
    int turno;
    int NoJugadores;
    ArrayList<PersonajeVO> listajugadores= new ArrayList<PersonajeVO>();
    HashMap<Integer, PersonajeVO> mapPersonajeVO = new HashMap<Integer, PersonajeVO>();
 
    public BatallaVO (){
        }
 
    public BatallaVO(int idbatalla, int turno, int noJugadores,
            ArrayList<PersonajeVO> listajugadores) {
        super();
        this.idbatalla = idbatalla;
        this.turno = turno;
        this.NoJugadores = noJugadores;
        this.listajugadores = listajugadores;
        for (PersonajeVO p : listajugadores) mapPersonajeVO.put(p.getIduser(),p);
 
    }
 
 
 
    public int getIdbatalla() {
        return idbatalla;
    }
 
    public void setIdbatalla(int idbatalla) {
        this.idbatalla = idbatalla;
    }
 
    public int getTurno() {
        return turno;
    }
 
    public void setTurno(int turno) {
        this.turno = turno;
    }
 
    public ArrayList<PersonajeVO> getListajugadores() {
        return listajugadores;
    }
 
    public void setListajugadores(ArrayList<PersonajeVO> listajugadores) {
        this.listajugadores = listajugadores;
    }
 
    public int getNoJugadores() {
        return NoJugadores;
    }
 
    public void setNoJugadores(int noJugadores) {
        NoJugadores = noJugadores;
    }
	public void addPersonajeVO(PersonajeVO personajeVO) {
		listajugadores.add(personajeVO);
		mapPersonajeVO.put(personajeVO.getIduser(), personajeVO);
	}
    public PersonajeVO getPersonajeVO(int idPersonajeVO) {
        System.out.print("entramos en getpersonajeVO \n");
        System.out.print("idPersonajeVO "+idPersonajeVO+"\n");

        PersonajeVO personajeVO = mapPersonajeVO.get(idPersonajeVO);
        System.out.print(""+personajeVO.getNombre());

        return personajeVO;
    }
     
}