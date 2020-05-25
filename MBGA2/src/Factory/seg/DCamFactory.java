package Factory.seg;

import DAOs.seg_dao_cam;
import Transfers.TCam;
import Transfers.Transfer;
import DAOs.DAO;

public class DCamFactory extends DaoFactory {

	@Override
	public DaoFactory parseCroncrete(Transfer t) {
		if(t instanceof TCam)
			return this;
		return null;
	}

	public DAO getDAO(Transfer t) {
		return new seg_dao_cam((TCam) t);
	}
	
	
	

}
