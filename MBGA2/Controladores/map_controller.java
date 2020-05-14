package Controladores;

import javax.swing.JTable;
import Main.Aeropuerto;
import Observer.Observer;
import SA.map_SA;

public class map_controller {
	private Aeropuerto aero;
	private map_SA SA;
	public static final long HOUR = 3600 * 1000;
	public static final long MIN = 60 * 1000;

	public map_controller(Aeropuerto aero) {
		this.aero = aero;
		this.SA = new map_SA();

	}

	public Aeropuerto getAero() {
		return aero;
	}

	public void setAero(Aeropuerto aero) {
		this.aero = aero;
	}

	public void addModelObserver(Observer o) {
		aero.addObserver(o);
		for (int i = 0; i < this.aero.getFligths().size(); i++) {
			aero.getFligths().get(i).addObserver(o);
		}
	}
	public void addTable(JTable table) {
		this.SA.setF(table);
	}
}
