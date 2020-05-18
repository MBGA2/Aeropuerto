package Factory;

import DAOs.DAO;
import Transfer.seg.Transfer;

public abstract class DaoFactory {
	
	
	public abstract DaoFactory parseCroncrete(Transfer t);
	public abstract DAO getDAO(Transfer t);

}
