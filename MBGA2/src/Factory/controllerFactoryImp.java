package Factory;

import Controladores.atm_controller;
import Controladores.inf_controller;
import Controladores.map_controller;
import Main.Aeropuerto;

public class controllerFactoryImp implements controllerFactory{

	
	private SAFactory sa = new SAFactoryImp();
	@Override
	public inf_controller generaControllerInf(Aeropuerto aero) {
		inf_controller ctrl = new inf_controller(aero);
		ctrl.setSA(sa.generaSAinf());
		return ctrl;
	}

	@Override
	public atm_controller generaControllerATM(Aeropuerto aero) {
		atm_controller ctrl = new atm_controller(aero);
		ctrl.setSA(sa.generaSAatm());
		return ctrl;
	}

	@Override
	public map_controller generaControllerMAP(Aeropuerto aero) {
		map_controller ctrl = new map_controller(aero);
		ctrl.setSA(sa.generaSAmap());
		return ctrl;
	}

}
