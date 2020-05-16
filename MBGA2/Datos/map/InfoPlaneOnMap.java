package Datos.map;

import java.sql.Timestamp;

import Datos.Flight;

public class InfoPlaneOnMap {
	private Timestamp initialD;
	private Timestamp lastD;
	private Flight flight;
	
	public Timestamp getInitialD() {
		return initialD;
	}
	public void setInitialD(Timestamp initialD) {
		this.initialD = initialD;
	}
	public Timestamp getLastD() {
		return lastD;
	}
	public void setLastD(Timestamp lastD) {
		this.lastD = lastD;
	}
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	public InfoPlaneOnMap(Timestamp initial, Timestamp last, Flight f){
		this.initialD = initial;
		this.lastD = last;
		this.flight = f;
	}
	
}
