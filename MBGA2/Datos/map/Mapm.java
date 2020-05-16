package Datos.map;

import java.sql.Timestamp;
import java.util.HashMap;

import Datos.Flight;

public class Mapm {
	private HashMap<String,NumberFlights> infoMap;
	public Mapm() {
		infoMap = new HashMap<String,NumberFlights>();
		for(int i=0;i<20;i++)
			for(int j=0;j<14;j++)
				infoMap.put(i+","+j, new NumberFlights());
	}
	public HashMap<String, NumberFlights> getInfoMap() {
		return infoMap;
	}
	public void addFlightInMap(Flight f, int x, int y, Timestamp ini, Timestamp last) {
		NumberFlights aux = this.infoMap.get(x+","+y);
		aux.addInfoFlight(ini, last, f);
		this.infoMap.put(x+","+y, aux);
	}
	public void removeFlightInMap(Flight f, int x, int y) {
		this.infoMap.get(x+","+y).removeFlight(f);
	}
	public int checkFlightsInMap(int x,int y, Timestamp ini) {
		return this.infoMap.get(x+","+y).flightsOnTime(ini);
	}
}
