package ea.grupo2.Bumaye.ClasesVO;

import java.util.ArrayList;
import java.util.HashMap;

import ea.grupo2.Bumaye.pojos.Batalla;

public class ListBatallasVO {
	ArrayList<BatallaVO> listaBatallas=new ArrayList<BatallaVO>();
	HashMap<Integer, BatallaVO> mapBatallaVO = new HashMap<Integer, BatallaVO>();
	HashMap<Integer, BatallaVO> mapBatallaVO_ATACANTE = new HashMap<Integer, BatallaVO>();
	private static ListBatallasVO instance = null;

	protected ListBatallasVO(){
		
	}
	
	public static ListBatallasVO getInstance() {
	      if(instance == null) {
	         instance = new ListBatallasVO();
	      }
	      return instance;
	   }
	
	public ListBatallasVO(ArrayList<BatallaVO> listaBatallas) {
		super();
		this.listaBatallas = listaBatallas;
		for (BatallaVO b : listaBatallas) mapBatallaVO.put(b.getIdbatalla(),b);
		for (BatallaVO b : listaBatallas)
		{
			for (PersonajeVO p: b.getListajugadores()) mapBatallaVO_ATACANTE.put(p.getIduser(),b);
			
		}
	}
	
	public ArrayList<BatallaVO> getListaBatallas() {
		return listaBatallas;
	}

	public void setListaBatallas(ArrayList<BatallaVO> listaBatallas) {
		this.listaBatallas = listaBatallas;
	}

	
	
	public BatallaVO getBatallaVO(int idBatllaVO) {
        BatallaVO batallaVO = mapBatallaVO.get(idBatllaVO);
//        return items.get(position).getId();
        return batallaVO;
    }
	
	public BatallaVO getBatallaVO_byIdAtacante(int idatacante) {
        BatallaVO batallaVO = mapBatallaVO_ATACANTE.get(idatacante);
//        return items.get(position).getId();
        return batallaVO;
    }
	
	public void addBatallaVO(BatallaVO batallaVO) {
		listaBatallas.add(batallaVO);
		mapBatallaVO.put(batallaVO.getIdbatalla(), batallaVO);
		for (PersonajeVO p: batallaVO.getListajugadores()) mapBatallaVO_ATACANTE.put(p.getIduser(),batallaVO);
	}
	
	public int getcount(){
		int i = listaBatallas.size();
		return i;
	}
}
