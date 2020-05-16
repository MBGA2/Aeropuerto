package Controladores;

import javax.swing.table.DefaultTableModel;

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
	public void refresh() {
		this.SA.fill(this.aero.getMap(),this.aero.getTime());
	}
	public void addTable(DefaultTableModel tableModel) {
		this.SA.setF(tableModel);
	}

	public void infoCell(int row, int col) {
		this.SA.infoC(row,col,this.aero.getMap(),this.aero.getTime());
		
	}
}
