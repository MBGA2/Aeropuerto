package Controladores;

import java.sql.Timestamp;

import javax.swing.table.DefaultTableModel;

import Datos.Flight;
import Datos.InfoCity;
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
	public void fillMap(Flight f) {
		InfoCity destInfo = null, sourceInfo = null, stopInfo = null;
		int j = 0, maxFlightsBox = 20;

		// Busco el valor de las ciudades origen, destino y escala
		for (InfoCity aux : this.aero.getPath().getCities())
			if (aux.getName().equalsIgnoreCase(f.getDestination()))
				destInfo = aux;
			else if (aux.getName().equalsIgnoreCase(f.getSource()))
				sourceInfo = aux;
			else if (aux.getName().equalsIgnoreCase(f.getPath().getStopover()))
				stopInfo = aux;

		int xSource = sourceInfo.getPosX(), ySource = sourceInfo.getPosY(), xDest = destInfo.getPosX(),
				yDest = destInfo.getPosY(), ti = 30 * 60 * 1000;
		Timestamp t = f.getDeparture_time();

		if (stopInfo != null) {
			int xStop = stopInfo.getPosX(), yStop = stopInfo.getPosY();

			while (xSource != xStop && ySource != yStop) {
				if (xSource < xStop && this.aero.getMap().checkFlightsInMap(xSource + 1, ySource,
						new Timestamp(t.getTime() + (ti * j))) < maxFlightsBox) {
					this.aero.getMap().addFlightInMap(f, xSource, ySource, new Timestamp(t.getTime() + (ti * j)),
							new Timestamp(t.getTime() + (ti * (j + 1))));
					xSource++;
					j++;
				} else if (xSource > xStop && this.aero.getMap().checkFlightsInMap(xSource - 1, ySource,
						new Timestamp(t.getTime() + (ti * j))) < maxFlightsBox) {
					this.aero.getMap().addFlightInMap(f, xSource, ySource, new Timestamp(t.getTime() + (ti * j)),
							new Timestamp(t.getTime() + (ti * (j + 1))));
					xSource--;
					j++;
				}
				if (ySource < yStop && this.aero.getMap().checkFlightsInMap(xSource, ySource + 1,
						new Timestamp(t.getTime() + (ti * j))) < maxFlightsBox) {
					this.aero.getMap().addFlightInMap(f, xSource, ySource, new Timestamp(t.getTime() + (ti * j)),
							new Timestamp(t.getTime() + (ti * (j + 1))));
					ySource++;
				} else if (ySource > yStop && this.aero.getMap().checkFlightsInMap(xSource, ySource - 1,
						new Timestamp(t.getTime() + (ti * j))) < maxFlightsBox) {
					this.aero.getMap().addFlightInMap(f, xSource, ySource, new Timestamp(t.getTime() + (ti * j)),
							new Timestamp(t.getTime() + (ti * (j + 1))));
					ySource--;
				}
				j++;
			}
			
			// Recorro de origen a escala y de escala a destino si hay escala

		}
		while (xSource != xDest || ySource != yDest) {
			if (xSource < xDest && this.aero.getMap().checkFlightsInMap(xSource + 1, ySource,
					new Timestamp(t.getTime() + (ti * j))) < maxFlightsBox) {
				this.aero.getMap().addFlightInMap(f, xSource, ySource, new Timestamp(t.getTime() + (ti * j)),
						new Timestamp(t.getTime() + (ti * (j + 1))));
				xSource++;
				j++;
			} else if (xSource > xDest && this.aero.getMap().checkFlightsInMap(xSource - 1, ySource,
					new Timestamp(t.getTime() + (ti * j))) < maxFlightsBox) {
				this.aero.getMap().addFlightInMap(f, xSource, ySource, new Timestamp(t.getTime() + (ti * j)),
						new Timestamp(t.getTime() + (ti * (j + 1))));
				xSource--;
				j++;
			}
			if (ySource < yDest && this.aero.getMap().checkFlightsInMap(xSource, ySource + 1,
					new Timestamp(t.getTime() + (ti * j))) < maxFlightsBox) {
				this.aero.getMap().addFlightInMap(f, xSource, ySource, new Timestamp(t.getTime() + (ti * j)),
						new Timestamp(t.getTime() + (ti * (j + 1))));
				ySource++;
			} else if (ySource > yDest && this.aero.getMap().checkFlightsInMap(xSource, ySource - 1,
					new Timestamp(t.getTime() + (ti * j))) < maxFlightsBox) {
				this.aero.getMap().addFlightInMap(f, xSource, ySource, new Timestamp(t.getTime() + (ti * j)),
						new Timestamp(t.getTime() + (ti * (j + 1))));
				ySource--;
			}
			j++;
		}
		this.aero.getMap().addFlightInMap(f, xSource, ySource, new Timestamp(t.getTime() + (ti * j)),
				new Timestamp(t.getTime() + (ti * (j + 1))));

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