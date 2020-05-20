package Controladores;

import DAOs.tor_dao;
import Main.Aeropuerto;

public class tor_controller {
	private Aeropuerto aero;
	private tor_dao dao;
	public tor_controller(Aeropuerto aero) {
		this.aero = aero;
		this.setDao(new tor_dao(this.aero));
	}
	public tor_dao getDao() {
		return dao;
	}
	public void setDao(tor_dao dao) {
		this.dao = dao;
	}

}


