package Controladores;

import DAOs.tor_dao;
import Main.Aeropuerto;
import SA.atm_SA;
import SA.tor_SA;

public class tor_controller {
	private Aeropuerto airport;
	private tor_SA sa;
	
	public tor_controller(Aeropuerto airport) {
		
		this.sa = new tor_SA(this.airport = airport);
	}

	public tor_SA getSA() {
		return this.sa;
	}

}


