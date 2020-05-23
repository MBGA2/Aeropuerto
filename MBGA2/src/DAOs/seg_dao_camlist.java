package DAOs;

import Datos.seg.cam;
import Datos.seg.listCam;
import Factory.DaoFactoryImp;
import Transfer.seg.TCam;
import Transfer.seg.TListCam;

public class seg_dao_camlist implements DAO {

	private listCam list = new listCam();

	public seg_dao_camlist(listCam list) {
		super();
		this.list = list;
	}

	public seg_dao_camlist(TListCam t) {
		insertNewList(t);
	}

	public seg_dao_camlist() {

	}

	public TListCam getCamList() {
		TListCam lc = new TListCam();
		for (cam c : list.getListCam()) {
			lc.insert(new TCam(c.getId_cam(), c.getCamStat(), c.isOnRepairs()));
		}
		return lc;
	}

	public void insertNewCam(TCam t) {
		seg_dao_cam camDao = new seg_dao_cam();
		if (searchTCamById(t) == null)
			list.insert(camDao.setNewCam(t));

	}

	public void insertNewList(TListCam tList) {
		for (TCam c : tList.getListCam()) {
			insertNewCam(c);
		}
	}

	public TCam searchTCamById(TCam t) {

		cam c = searchCamById(t);
		if (c != null)
			return new TCam(c.getId_cam(), c.getCamStat(), c.isOnRepairs());
		return null;
	}

	private cam searchCamById(TCam t) {
		return this.list.searchCamById(t.getId_cam());
	}

	public TCam searchCam(TCam t) {
		cam c = this.list.searchCam(new cam(t.getId_cam(), t.getCamStat(), t.isOnRepairs()));
		if (c != null)
			return new TCam(c.getId_cam(), c.getCamStat(), c.isOnRepairs());
		return null;
	}

	public void saveData(listCam l) {
		l = this.list;
	}

	public void saveData(TListCam l) {
		insertNewList(l);
	}

	public void setList(listCam listCam) {
		this.list = listCam;
	}

	public TCam addToMant(TCam tC) {
		cam c = searchCamById(tC);
		if (c != null) {
			seg_dao_cam d = ((seg_dao_cam) (DaoFactoryImp.getInstance().parse(tC)).getDAO(tC));
			d.setCam(c);
			d.setCamOnRepairs(true);
			return new TCam(c.getId_cam(), c.getCamStat(), c.isOnRepairs());
		}
		return null;
	}
}
