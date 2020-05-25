package Factory;

import Controladores.*;
import Main.Aeropuerto;

public interface controllerFactory {
	public inf_controller generaControllerInf(Aeropuerto aero);
	public atm_controller generaControllerATM(Aeropuerto aero);
	public map_controller generaControllerMAP(Aeropuerto aero);
}
