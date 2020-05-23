package Datos.map;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import Datos.Flight;
import Datos.Tuple;

public class Mapm {
	private HashMap<String, NumberFlights> infoMap;
	private HashMap<Flight, ArrayList<Tuple<Integer, Integer, Timestamp>>> flightList;

	public Mapm() {
		this.infoMap = new HashMap<String, NumberFlights>();
		for (int i = 0; i < 20; i++)
			for (int j = 0; j < 14; j++)
				this.infoMap.put(i + "," + j, new NumberFlights());
		this.flightList = new HashMap<Flight, ArrayList<Tuple<Integer, Integer, Timestamp>>>();
	}

	public Tuple<Integer, Integer, Timestamp> currentPos(Flight f, Timestamp t) {
		int x = 0, y = 0;
		if (this.flightList.containsKey(f)) {
			x = this.flightList.get(f).get(this.flightList.get(f).size() - 1).x;
			y = this.flightList.get(f).get(this.flightList.get(f).size() - 1).y;
			return new Tuple<Integer, Integer, Timestamp>(x, y, null);
		}
		return null;
	}

	public void addFlightInMap(Flight f, int x, int y, Timestamp ini, Timestamp last) {
		NumberFlights aux = this.infoMap.get(x + "," + y);
		aux.addInfoFlight(ini, last, f);
		this.infoMap.put(x + "," + y, aux);
		if (this.flightList.containsKey(f))
			this.flightList.get(f).add(new Tuple<Integer, Integer, Timestamp>(x, y, ini));
		else {
			this.flightList.put(f, new ArrayList<Tuple<Integer, Integer, Timestamp>>());
			this.flightList.get(f).add(new Tuple<Integer, Integer, Timestamp>(x, y, ini));
		}
	}

	private void removeFlightInMap(Flight f, int x, int y) {
		this.infoMap.get(x + "," + y).removeFlight(f);
	}

	

	public int checkFlightsInMap(int x, int y, Timestamp ini) {
		return this.infoMap.get(x + "," + y).flightsOnTime(ini);
	}

	public void freezeFlight(Flight f, Timestamp t) {
		int x = -1, y = -1;
		if (this.flightList.containsKey(f)) {
			for (Iterator<Tuple<Integer, Integer, Timestamp>> iterator = this.flightList.get(f).iterator(); iterator
					.hasNext();) {
				Tuple<Integer, Integer, Timestamp> tupla = iterator.next();
				if (tupla.z.before(t)) {
					x = tupla.x;
					y = tupla.y;
				} else {
					this.removeFlightInMap(f, tupla.x, tupla.y);
					iterator.remove();
				}
			}
			this.infoMap.get(x + "," + y).delayFlight(f, 20160, true); // Se queda en el sitio 2 semanas o hasta llegada
																		// de SAR
		}
	}

	public void delayFlight(Flight f, Timestamp t, int d) {
		int x = -1, y = -1;
		if (this.flightList.containsKey(f)) {
			for (Iterator<Tuple<Integer, Integer, Timestamp>> iterator = this.flightList.get(f).iterator(); iterator
					.hasNext();) {
				Tuple<Integer, Integer, Timestamp> tupla = iterator.next();
				if (tupla.z.before(t)) {
					x = tupla.x;
					y = tupla.y;
				} else {
					tupla.z = new Timestamp(tupla.z.getTime() + d * 60 * 1000);
					this.infoMap.get(tupla.x + "," + tupla.y).delayFlight(f, d, false);
				}
			}
			this.infoMap.get(x + "," + y).delayFlight(f, d, true);
		}
	}

	public HashMap<String, NumberFlights> getInfoMap() {
		return infoMap;
	}
	public HashMap<Flight, ArrayList<Tuple<Integer, Integer, Timestamp>>> getflightPathList() {
		return this.flightList;
	}
}
