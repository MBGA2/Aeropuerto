package DAOs;

import Datos.seg.listCam;
import Transfer.seg.TCam;
import Transfer.seg.TListCam;

public class seg_camlist_dao {
	
	private listCam list;
	
	public seg_camlist_dao(listCam list) {
		super();
		this.list = list;
	}
	
	public listCam getCamList()
	{
		return null;
	}
	
	public void insertNewCam(TCam t)
	{
		seg_cam_dao camDao = new seg_cam_dao();	//single
		list.insert(camDao.setNewCam(t));	//list
		
	}

	public void insertNewList(TListCam tList) {
		for(TCam c : tList.getListCam())
		{
			insertNewCam(c);
		}
	}
}
