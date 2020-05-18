package DAOs;

import Datos.seg.cam;
import Datos.seg.camState;
import Transfer.seg.TCam;

public class seg_dao_cam implements DAO{
	
	private cam c;
	
	public seg_dao_cam(cam c)
	{
		this.c = c;
	}
	
	public seg_dao_cam() {
	}
	
	public seg_dao_cam(TCam c)
	{
		setNewCam(c);
	}

	public cam setNewCam(TCam c)
	{
		this.c = new cam();
		this.c.setId_cam(c.getId_cam());
		this.c.setState(c.getCamStat());
		this.c.setOnRepairs(c.isOnRepairs());
		return this.c;
	}
	
	public void setCam(cam c)
	{
		this.c = c;
	}
	
	public void setCam(String id, camState s)
	{
		c.setId_cam(id);
		c.setState(s);
	}
	
	public void setCamOnRepairs(boolean b)
	{
		c.setOnRepairs(b);
	}

}
