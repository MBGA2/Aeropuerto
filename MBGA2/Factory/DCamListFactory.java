package Factory;

import DAOs.seg_dao_camlist;
import DAOs.DAO;
import Transfer.seg.TListCam;
import Transfer.seg.Transfer;

public class DCamListFactory extends DaoFactory {

	@Override
	public DCamListFactory parseCroncrete(Transfer t) {
		if(t instanceof TListCam)
			return this;
		return null;
	}

	public DAO getDAO(Transfer t) {
		if(t != null && ((TListCam) t).getSize() > 0)
			return new seg_dao_camlist((TListCam) t);
		return new seg_dao_camlist();
	}

}
