package Controladores;

import javax.swing.table.DefaultTableModel;

import Main.Aeropuerto;
import Observer.Observer;
import SA.inf_SA;

public class inf_controller {
	private Aeropuerto aero;
	private inf_SA SA;
	public static final long HOUR = 3600 * 1000;
	public static final long MIN = 60 * 1000;

	public inf_controller(Aeropuerto aero) {
		this.aero = aero;
	}

	public void addModelObserver(Observer o) {
		aero.addObserver(o);
		for (int i = 0; i < this.aero.getFligths().size(); i++) {
			aero.getFligths().get(i).addObserver(o);
		}
	}

	public void addAll() {
		this.SA.addAllFlights(this.aero.getFligths());
	}

	public void search(String search) {
		this.SA.searchInArrivals(search, this.aero.getFligths());
		this.SA.searchInDepartures(search, this.aero.getFligths());
	}

	public void resetFlag() {
		this.SA.setFlag(true);
	}

	public void addTables(DefaultTableModel model, DefaultTableModel model2) {
		this.SA.setModelArrivals(model2);
		this.SA.setModelDepartures(model);
	}

	public void setSA(inf_SA generaSAinf) {
		this.SA = generaSAinf;
	}
}
