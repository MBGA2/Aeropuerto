package Datos.map;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Datos.Flight;

public class NumberFlights {
	private List<InfoPlaneOnMap> infoFlights;

	public NumberFlights() {
		this.infoFlights = new ArrayList<InfoPlaneOnMap>();
	}

	protected void addInfoFlight(Timestamp ini, Timestamp last, Flight f) {
		this.infoFlights.add(new InfoPlaneOnMap(ini, last, f));
	}

	public void removeFlight(Flight f) {
		InfoPlaneOnMap temp = null;
		for (InfoPlaneOnMap aux : this.infoFlights)
			if (aux.getFlight().getID().equalsIgnoreCase(f.getID())) {
				temp = aux;
				break;
			}

		if (temp != null)
			this.infoFlights.remove(temp);
	}

	protected void delayFlight(Flight f, int d, boolean first) {
		InfoPlaneOnMap temp = null;
		for (InfoPlaneOnMap aux : infoFlights)
			if (aux.getFlight().getID().equalsIgnoreCase(f.getID()))
				temp = aux;
		if (temp != null) {
			temp.setLastD(new Timestamp(temp.getLastD().getTime() + d * 60 * 1000));
			if (!first)
				temp.setInitialD(new Timestamp(temp.getInitialD().getTime() + d * 60 * 1000));
		}
	}

	public int flightsOnTime(Timestamp ini) {
		int n = 0;
		for (InfoPlaneOnMap f : this.infoFlights)
			if (f.getInitialD().before(ini) && f.getLastD().after(ini))
				n++;
		return n;
	}

	public List<Flight> fl(Timestamp ini) {
		List<Flight> aux = new ArrayList<Flight>();
		for (InfoPlaneOnMap f : this.infoFlights)
			if (f.getInitialD().before(ini) && f.getLastD().after(ini))
				aux.add(f.getFlight());
		return aux;
	}

	public List<InfoPlaneOnMap> getInfoFlights() {
		return this.infoFlights;
	}
}
