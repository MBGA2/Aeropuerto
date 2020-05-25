package Factory;

import DAOs.*;

public interface daoSFactory {
	public inf_dao generaDAOinf(); 
	public atm_dao generaDAOatm();
	public map_dao generaDAOmap(); 
	
}
