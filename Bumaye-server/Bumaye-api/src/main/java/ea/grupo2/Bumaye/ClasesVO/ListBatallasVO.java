package ea.grupo2.Bumaye.ClasesVO;

import java.util.ArrayList;
import java.util.HashMap;

import ea.grupo2.Bumaye.pojos.Batalla;

public class ListBatallasVO {
	ArrayList<BatallaVO> listaBatallas=new ArrayList<BatallaVO>();
	HashMap<Integer, BatallaVO> mapBatallaVO = new HashMap<Integer, BatallaVO>();
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
	
	public void addBatallaVO(BatallaVO batallaVO) {
		listaBatallas.add(batallaVO);
		mapBatallaVO.put(batallaVO.getIdbatalla(), batallaVO);
	}
	
	public int getcount(){
		int i = listaBatallas.size();
		return i;
	}
}
