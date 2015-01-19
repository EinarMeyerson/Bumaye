package ea.grupo2.Bumaye.ClasesVO;

import java.util.ArrayList;
import java.util.HashMap;

public class ListaPeticionesVO {
	ArrayList<PeticionBatallaVO> listapeticiones = new ArrayList<PeticionBatallaVO>();
	HashMap<Integer, PeticionBatallaVO> mapPeticionAtacanteVO = new HashMap<Integer, PeticionBatallaVO>();
	HashMap<Integer, PeticionBatallaVO> mapPeticionDefensorVO = new HashMap<Integer, PeticionBatallaVO>();
	private static ListaPeticionesVO instance = null;

	protected ListaPeticionesVO(){

	}

	public static ListaPeticionesVO getInstance() {
		if(instance == null) {
			instance = new ListaPeticionesVO();
		}
		return instance;
	}

	public ArrayList<PeticionBatallaVO> getListapeticiones() {
		return listapeticiones;
	}

	public void setListapeticiones(ArrayList<PeticionBatallaVO> listapeticiones) {
		this.listapeticiones = listapeticiones;
	}
	
	public PeticionBatallaVO getPeticionAtacanteVO(int idatacante) {
		PeticionBatallaVO peticionVO = mapPeticionAtacanteVO.get(idatacante);
        return peticionVO;
    }
	
	public PeticionBatallaVO getPeticionDefensorVO(int iddefensor) {
		PeticionBatallaVO peticionVO = mapPeticionDefensorVO.get(iddefensor);
        return peticionVO;
    }

	public void addPeticionVO(PeticionBatallaVO peticion) {
		listapeticiones.add(peticion);
		mapPeticionAtacanteVO.put(peticion.getIdatacante(), peticion);
		mapPeticionDefensorVO.put(peticion.getIddefensor(), peticion);
	}

	public int getcount(){
		int i = listapeticiones.size();
		return i;
	}
	
	public void remove_peticion (int idatacante)
	{
		int i=0;
		for(PeticionBatallaVO p: listapeticiones)
	    {
			p = getPeticionAtacanteVO(i);
			System.out.print("&& Eliminar peticicion IDatacante "+p.getIdatacante());

	        if(p.getIdatacante()==idatacante)
	        {
	        	listapeticiones.remove(p);
	            break;
	        }
	        i++;
	}
	}
}
