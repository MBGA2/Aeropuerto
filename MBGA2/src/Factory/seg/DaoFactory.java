package Factory.seg;

import DAOs.DAO;
import Transfers.Transfer;

public abstract class DaoFactory {
	
	
	public abstract DaoFactory parseCroncrete(Transfer t);
	public abstract DAO getDAO(Transfer t);

}
