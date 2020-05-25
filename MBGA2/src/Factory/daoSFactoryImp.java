package Factory;

import DAOs.atm_dao;
import DAOs.inf_dao;
import DAOs.map_dao;

public class daoSFactoryImp implements daoSFactory {

	@Override
	public inf_dao generaDAOinf() {
		return new inf_dao();
	}

	@Override
	public atm_dao generaDAOatm() {
		return new atm_dao();
	}

	@Override
	public map_dao generaDAOmap() {
		return new map_dao();
	}  
}
