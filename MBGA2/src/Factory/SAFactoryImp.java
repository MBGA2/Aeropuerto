package Factory;

import SA.atm_SA;
import SA.inf_SA;
import SA.map_SA;

public class SAFactoryImp implements SAFactory{

	private daoSFactory factoria = new daoSFactoryImp();
	@Override
	public inf_SA generaSAinf() {
		inf_SA sa = new inf_SA();
		sa.setDao(factoria.generaDAOinf());
		return sa;
	}

	@Override
	public atm_SA generaSAatm() {
		atm_SA sa = new atm_SA();
		sa.setDao(factoria.generaDAOatm());
		return sa;
	}

	@Override
	public map_SA generaSAmap() {
		map_SA sa = new map_SA();
		sa.setDao(factoria.generaDAOmap());
		return sa;
	}

}
