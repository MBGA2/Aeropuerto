package Utils.atm;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Datos.Flight;

public class NumberFlights {
	private List<InfoPlaneOnMap> infoFlights;
	public NumberFlights() {
		this.infoFlights =new ArrayList<InfoPlaneOnMap>();
	}
	public List<InfoPlaneOnMap> getInfoFlights() {
		return this.infoFlights;
	}
	public void addInfoFlight(Timestamp ini, Timestamp last, Flight f) {
		this.infoFlights.add(new InfoPlaneOnMap(ini,last,f));
	}
	public void removeFlight(Flight f) {
		for (InfoPlaneOnMap aux : infoFlights) 
			if (aux.getFlight().getID().equalsIgnoreCase(f.getID()))
					this.infoFlights.remove(aux);
	}
	public void delayFlight(Flight f, int d) {
		for(InfoPlaneOnMap aux : infoFlights)
			if(aux.getFlight().getID().equalsIgnoreCase(f.getID())) {
				aux.setLastD(new Timestamp(aux.getLastD().getTime() + d * 60 * 1000));
				// for recorriendo el resto de casillas y aumentarlas 5 minutos
			}
	}
	public int flightsOnTime(Timestamp ini) {
		int n = 0;
		for (InfoPlaneOnMap f : this.infoFlights) 
			if (f.getInitialD().before(ini) && (new Timestamp(ini.getTime() + 30 * 60 * 1000)).after(ini))
				n++;
		return n;
	}
}
