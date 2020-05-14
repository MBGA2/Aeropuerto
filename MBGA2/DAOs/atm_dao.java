package DAOs;


import Main.Aeropuerto;
import Utils.NTYPE;
import Utils.NotifyData;
import Utils.atm.GeneratePath;
import Utils.atm.InfoCity;
import Utils.atm.Path;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import Datos.Flight;

public class atm_dao {

	private Aeropuerto airport;
	private Date current;
	private GeneratePath path;

	public atm_dao() {
		this.path = new GeneratePath();
	}
	
	public void planeCrash(Flight f) {
		if (!f.getPlane_state().equalsIgnoreCase("Crashed")){
			f.setPlane_state("Crashed");
			this.airport.notifyAllO(new NotifyData(NTYPE.TOR_CRASH, f));
			//Enviar Sar
//			warnSAR();
			
		}
		
	}
	public void planeDelay(int d, Flight f) {
		if(!f.getPlane_state().equalsIgnoreCase("Crashed")) {
			f.setPlane_state("Delayed");
			f.setRetarded_value(d);
//			f.setBoarding_time(new Timestamp(d));
			this.airport.notifyAllO(new NotifyData(NTYPE.TOR_DELAY, f,d));
		}
	}
	
	public boolean flightFinished(Flight flight) {
		/*
		 * if (current.getDate() > flight.getArrival_time().getDate()) { return true;
		 * }else if (current.getDate() == flight.getArrival_time().getDate() &&
		 * current.getHours() > flight.getArrival_time().getHours()) { return true;
		 * }else if (current.getDate() == flight.getArrival_time().getDate() &&
		 * current.getHours() == flight.getArrival_time().getHours() &&
		 * current.getMinutes() > flight.getArrival_time().getMinutes()) { return true;
		 * }
		 */
		if (current.after(flight.getArrival_time()) || current.equals(flight.getArrival_time()))
			return true;
		return false;
	}
	/*
	 * public void flightSpace(Flight flight) { String[] aux =
	 * flight.getPath().getStopover().split(",");
	 * 
	 * if (!aux[0].equalsIgnoreCase("Direct")) { int intAux = aux.length; if (intAux
	 * == 1) { if (current .after(new Date(flight.getDeparture_time().getTime() +
	 * (flight.getPath().getDuration() / 2))))
	 * flight.setCurrentSpace(flight.getDestination()); } else { if (current
	 * .after(new Date(flight.getDeparture_time().getTime() +
	 * (flight.getPath().getDuration() / 3)))) flight.setCurrentSpace(aux[1]); else
	 * if (current.after( new Date(flight.getDeparture_time().getTime() +
	 * ((flight.getPath().getDuration() / 3) * 2))))
	 * flight.setCurrentSpace(flight.getDestination()); } } /* if(current.after(new
	 * Date(flight.getDeparture_time().getTime()+(flight.getPath().getDuration()/aux
	 * .length)))) flight.setCurrentSpace(aux[1]);
	 * 
	 * }
	 */

//	public void refreshFlights(DefaultTableModel model) {
//		// int i = 0;
//		// aux.add(Calendar.MINUTE, 15);
//		for (Flight flight : this.airport.getFligths()) {
//			if (flight.getPlane_state().equalsIgnoreCase("On_Going")) {
//				// flightSpace(flight);
//				if (flightFinished(flight)) {
//					flight.setPlane_state("Waiting");
//				}
//			}
//		}
//	}

	public void tableFill(DefaultTableModel table, List<Flight> fligths) {
		for (int i = 0; i < fligths.size(); i++) {
			if (fligths.get(i).getFlight_state().equalsIgnoreCase("On_Going")) {
				//Flight f = this.airport.getFligths().get(i);
				/*if ((f.getDeparture_time().before(this.airport.getTime())
						|| f.getDeparture_time().equals(this.airport.getTime()))
						&& f.getPlane_state().equalsIgnoreCase("Waiting"))
					f.setPlane_state("On_Going");*/
				Object[] fila = constructRow(fligths.get(i));
				table.addRow(fila);
			}
		}
	}

	@SuppressWarnings("deprecation")
	public Object[] constructRow(Flight f) {
		Object[] fila = new Object[9];
		fila[0] = f.getID();
		fila[1] = f.getDestination();
		fila[2] = f.getDeparture_time().toGMTString();
		fila[3] = f.getArrival_time().toGMTString();

		fila[4] = f.getSource();
		fila[5] = f.getCompany();
		fila[6] = f.getPlane_state();
		if (f.getPlane_state().equalsIgnoreCase("Crashed")) {
			fila[7] = "Estrellado";
			fila[8] = "Pa Siempre";
		} else if (f.getPlane_state().equalsIgnoreCase("Damaged")
				|| (f.getPlane_state().equalsIgnoreCase("Delayed"))) {
			fila[7] = "Retrasado";
			fila[8] = "5 Minutos por ahora";
		} else {
			fila[7] = "";
			fila[8] = "";
		}
		return fila;
	}

	public Aeropuerto getAirport() {
		return this.airport;
	}
	
	/*
	public void changeFlight(Flight flight) {
		if ((current.after(flight.getDeparture_time()) || current.equals(flight.getDeparture_time()))
				&& flight.getPlane_state().equalsIgnoreCase("Waiting")) {
			flight.getPlane().setState(PlaneState.On_Going);
			this.airport.notifyAllO(new NotifyData(NTYPE.ATM_REFRESH, flight));
		}
	}*/
	
	//Reescribir calcular path para la llamada inicial teniendo en cuenta las demas
	
	
	/*Estas 2 funciones son para añadir donde se haga el path
	 * Mover tambien el GeneratePath del Constructor*/
	public void calculatePath(Flight flight) {
		InfoCity a = null,b = null,c = null, d = null;
		String stopovers = "";
		for(InfoCity aux : this.path.getCities()) 
			if (flight.getDestination().equalsIgnoreCase(aux.getName()))
				a = aux;
			else if (flight.getSource().equalsIgnoreCase(aux.getName()))
				b = aux;
		
		if (this.path.getDirect().get(flight.getSource()).contains(flight.getDestination())) { //Vuelo Directo
			flight.setPath(new Path(flight.getDestination(), "Direct", 
					this.calculateTimeFlight(
							Math.abs(a.getPosX()-b.getPosX()+a.getPosY()-b.getPosY())), b.getPosX(), b.getPosY()));
		} else if (Math.abs(a.getPosX()-b.getPosX()+a.getPosY()-b.getPosY()) <= 10){ // 1 Escala
			for(InfoCity aux : this.path.getCities()) 
				if (this.path.getDirect().get(flight.getSource()).contains(aux.getName()) 
						&& this.path.getDirect().get(aux.getName()).contains(flight.getDestination()))
					c = aux;
			flight.setPath(new Path(flight.getDestination(), c.getName(), 
					this.calculateTimeFlight(
							Math.abs(a.getPosX()-b.getPosX()+a.getPosY()-b.getPosY()-c.getPosX()-c.getPosY())), b.getPosX(), b.getPosY()));
		} else { // 2+ Escala
			int dist = 0;
			c = new InfoCity("",0,0);
			for(InfoCity aux : this.path.getCities()) 
				if (this.path.getDirect().get(flight.getSource()).contains(aux.getName()) && aux.getPosX() > dist) {
					dist = aux.getPosX();
					c = aux;
				}
			d = c;
			while (!this.path.getDirect().get(d.getName()).contains(flight.getDestination())) {
				dist = 0;
				for(InfoCity aux : this.path.getCities()) 
					if (this.path.getDirect().get(c.getName()).contains(aux.getName()) && aux.getPosX() > dist) {
						dist = aux.getPosX();
						d = aux;
					}
				c.setName(c.getName()+","+d.getName());
				c.setPosX(c.getPosX()+d.getPosX());
				c.setPosY(c.getPosY()+d.getPosY());
			}
			flight.setPath(new Path(flight.getDestination(), c.getName(), 
					this.calculateTimeFlight(
							Math.abs(a.getPosX()-b.getPosX()+a.getPosY()-b.getPosY()-c.getPosX()-c.getPosY())), b.getPosX(), b.getPosY()));
		} 
		
	}
	/*Placeholder*/
	public int calculateTimeFlight(int diff) {
		return diff * 1000000;
	}
	
}
