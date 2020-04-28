package DAOs;

import Datos.seg.cam;
import Datos.seg.camState;
import Transfer.seg.TCam;

public class seg_cam_dao {
	
	private cam c;
	
	public seg_cam_dao(cam c)
	{
		this.c = c;
	}
	
	public seg_cam_dao() {
	}

	public cam setNewCam(TCam c)
	{
		this.c = new cam();
		this.c.setId_cam(c.getId_cam());
		this.c.setState(c.getCamStat());
		this.c.setOnRepairs(c.isOnRepairs());
		return this.c;
	}
	
	public void setCam(String id, camState s)
	{
		c.setId_cam(id);
		c.setState(s);
	}

}
