package ea.grupo2.Bumaye.ClasesVO;
 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import android.util.Log;

import com.google.gson.annotations.SerializedName;
 
public class BatallaVO implements Serializable {
	@SerializedName("idbatalla")
    int idbatalla;
	@SerializedName("turno")
    int turno;
	@SerializedName("NoJugadores")
    int NoJugadores;
	@SerializedName("listajugadores")
    ArrayList<PersonajeVO> listajugadores= new ArrayList<PersonajeVO>();
    HashMap<String, PersonajeVO> mapPersonajeVO = new HashMap<String, PersonajeVO>();
 
    public BatallaVO (){
        }
 
    public BatallaVO(int idbatalla, int turno, int noJugadores,
            ArrayList<PersonajeVO> listajugadores) {
        super();
        this.idbatalla = idbatalla;
        this.turno = turno;
        this.NoJugadores = noJugadores;
        this.listajugadores = listajugadores;
        for (PersonajeVO p : listajugadores) mapPersonajeVO.put(p.getNombre(),p);
 
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
		mapPersonajeVO.put(personajeVO.getNombre(), personajeVO);
	}
    public PersonajeVO getPersonajeVO(String nombrePersonajeVO) {
    	
        PersonajeVO personajeVO = mapPersonajeVO.get(nombrePersonajeVO);
        
        return personajeVO;
        
    }
    public int getPosicionPersonajeVO(int idPersonajeVO) {

        PersonajeVO personajeVO = mapPersonajeVO.get(idPersonajeVO);
        int posicion = listajugadores.indexOf(personajeVO);
        System.out.print(""+posicion);
        return posicion;
    }
    
    public PersonajeVO getEnemigo (String nombre)
    {
    	PersonajeVO enemigo= new PersonajeVO();
    	for (PersonajeVO person: listajugadores)
    	{
    		if (person.getNombre().equals(nombre))
    		{
    			Log.i("Jugador",person.getNombre());
    		}
    		else
    		{
    			Log.i("Enemigo",person.getNombre());
    			enemigo=person;
    		}
    	}
    	return enemigo;
    }
     
}