package Factory;

import DAOs.seg_dao_cam;
import DAOs.DAO;
import Transfer.seg.TCam;
import Transfer.seg.Transfer;

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
