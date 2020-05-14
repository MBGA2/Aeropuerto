package Utils.atm;

import java.sql.Timestamp;
import java.util.HashMap;

import Datos.Flight;

public class Map {
	private HashMap<String,NumberFlights> infoMap;
	public Map() {
		infoMap = new HashMap<String,NumberFlights>();
		for(int i=0;i<19;i++)
			for(int j=0;j<11;j++)
				infoMap.put("("+i+","+j+")", new NumberFlights());
	}
	public HashMap<String, NumberFlights> getInfoMap() {
		return infoMap;
	}
	public void addFlightInMap(Flight f, int x, int y, Timestamp ini, Timestamp last) {
		this.infoMap.get("("+x+","+y+")").addInfoFlight(ini, last, f);
	}
	public void removeFlightInMap(Flight f, int x, int y) {
		this.infoMap.get("("+x+","+y+")").removeFlight(f);
	}
}
