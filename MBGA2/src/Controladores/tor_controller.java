package Controladores;

import Main.Aeropuerto;
import SA.tor_SA;

public class tor_controller {
	private Aeropuerto airport;
	private tor_SA sa;

	public tor_controller(Aeropuerto airport) {
		this.airport = airport;
		this.sa = new tor_SA(this.airport);
	}

	public tor_SA getSA() {
		return this.sa;
	}

	public Aeropuerto getAirport() {
		return airport;
	}

	public void setAirport(Aeropuerto airport) {
		this.airport = airport;
	}

}
