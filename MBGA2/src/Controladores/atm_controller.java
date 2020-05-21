package Controladores;

import java.sql.Timestamp;

import javax.swing.table.DefaultTableModel;
import Main.Aeropuerto;
import Observer.Observer;
import SA.atm_SA;
import Utils.NTYPE;
import Utils.NotifyData;
import Utils.Tuple;
import Datos.Flight;
import Datos.InfoCity;
import Datos.Path;

public class atm_controller {
	private Aeropuerto airport;
	private atm_SA sa;
	
	public atm_controller(Aeropuerto airport) {
		this.airport = airport;
		this.sa = new atm_SA();
	}
	
//	public void planeDamaged(Flight f) { 
//		boolean flag = false;
//		if (!f.getPlane_state().equalsIgnoreCase("Crashed") && !f.getPlane_state().equalsIgnoreCase("Waiting")){
//			f.setPlane_state("Damaged");	
//			//f.setState(FlightState.Delayed);
//			flag = true;
//			delayFlights(f);
//		}
//		if (flag) {
//			// Random a ver si se estrella
//			// Notify Delay
//			// Mirar si hay mas Delays
//			
//			;
//		}else 
//			System.out.println("El avion ya esta estrellado fiera");
//		
//	}
	
	
	/*
	public void planeLanded(Flight flight) {
		boolean flag = false;
		for (Flight aux: this.airport.getOn_going()) {
			if (aux.getDestination().equalsIgnoreCase(flight.getDestination()) && aux.getPlane().getState() != PlaneState.Damaged && aux.getPlane().getState() != PlaneState.Crashed){
				aux.getPlane().setState(PlaneState.Landing);
				List<Flight> aux2 = this.airport.getOn_going();
				aux2.remove(flight);
				this.airport.setOn_going(aux2);
				flag = true;
				break;
			}
		}
		if (!flag)
			System.out.println("Error 2: El avion no existe");
	}
	*/
	
	public void warnSAR() {
		//Timer Rescate, cuando acaba el avion se quita de lista de flights
	}

	public void addModelObserver(Observer o) {
		airport.addObserver(o);
		for (int i = 0; i <this.airport.getFligths().size();i++) {
			airport.getFligths().get(i).addObserver(o);
		}
	}
	
	public int sarPath(Flight f, Flight sar) {
		int x = -1,y = -1,mx = -1, my = -1, t = 0, ti = 60*60*1000, d = 0;
		Tuple <Integer,Integer,Timestamp> aux = this.airport.getMap().currentPos(f, this.airport.getTime());
		for (InfoCity city : this.airport.getPath().getCities()) {
			if (city.getName().equalsIgnoreCase("Madrid")) {
				x = city.getPosX(); mx = x;
				y = city.getPosY(); my = y;
				break;
			}
		}
		d = (Math.abs(x - aux.x) + Math.abs(y - aux.y)) * 2;
		while ((x < aux.x || x > aux.x) || (y < aux.y || y > aux.y)) {
			if (x < aux.x) {
				this.airport.getMap().addFlightInMap(sar, x, y, new Timestamp(this.airport.getTime().getTime() + (t*ti)), new Timestamp(this.airport.getTime().getTime() + ((t+1)*ti)));
				x++; t++;
			}
			if (x > aux.x) {
				this.airport.getMap().addFlightInMap(sar, x, y, new Timestamp(this.airport.getTime().getTime() + (t*ti)), new Timestamp(this.airport.getTime().getTime() + ((t+1)*ti)));
				x--; t++;
			}
			if (y < aux.y) {
				this.airport.getMap().addFlightInMap(sar, x, y, new Timestamp(this.airport.getTime().getTime() + (t*ti)), new Timestamp(this.airport.getTime().getTime() + ((t+1)*ti)));
				y++; t++;
			}
			if (y > aux.y) {
				this.airport.getMap().addFlightInMap(sar, x, y, new Timestamp(this.airport.getTime().getTime() + (t*ti)), new Timestamp(this.airport.getTime().getTime() + ((t+1)*ti)));
				y--; t++;
			}
		}
		while ((x < mx || x > mx) || (y < my || y > my)) {
			if (x < mx) {
				this.airport.getMap().addFlightInMap(sar, x, y, new Timestamp(this.airport.getTime().getTime() + (t*ti)), new Timestamp(this.airport.getTime().getTime() + ((t+1)*ti)));
				x++; t++;
			}
			if (x > mx) {
				this.airport.getMap().addFlightInMap(sar, x, y, new Timestamp(this.airport.getTime().getTime() + (t*ti)), new Timestamp(this.airport.getTime().getTime() + ((t+1)*ti)));
				x--; t++;
			}
			if (y < my) {
				this.airport.getMap().addFlightInMap(sar, x, y, new Timestamp(this.airport.getTime().getTime() + (t*ti)), new Timestamp(this.airport.getTime().getTime() + ((t+1)*ti)));
				y++; t++;
			}
			if (y > my) {
				this.airport.getMap().addFlightInMap(sar, x, y, new Timestamp(this.airport.getTime().getTime() + (t*ti)), new Timestamp(this.airport.getTime().getTime() + ((t+1)*ti)));
				y--; t++;
			}
		}
		this.airport.getMap().addFlightInMap(sar, x, y, new Timestamp(this.airport.getTime().getTime() + (t*ti)), new Timestamp(this.airport.getTime().getTime() + ((t+1)*ti)));
		return d;
	}
	
	public void warnSAR(Flight f) {
		for (Flight sar : this.airport.getSar()) {
			if (!sar.getFlight_state().equalsIgnoreCase("On_Going")) {
				sar.setFlight_state("On_Going");
				sar.setDeparture_time(this.airport.getTime());
				sar.setDestination("Madrid");
				sar.setPath(new Path("Madrid",f.getID(),sarPath(f,sar)));
				break;
			}
		}
	}

	public void crashPlane(String text) {
		if (this.sa.planeNotCrashed(text,this.airport.getFligths())) {
			Flight f = this.sa.planeCrashed(text,this.airport.getFligths());
			this.airport.getMap().freezeFlight(f,this.airport.getTime());
			this.warnSAR(f);
			this.airport.notifyAllO(new NotifyData(NTYPE.TOR_CRASH, f));
		}
	}

	public void delayPlane(String text) {
		Flight f = this.sa.planeDelayed(text,this.airport.getFligths());
		this.airport.getMap().delayFlight(f,this.airport.getTime(),this.sa.getDelay());
		this.airport.notifyAllO(new NotifyData(NTYPE.TOR_DELAY, f, this.sa.getDelay()));
	}

	public void addModels(DefaultTableModel tableModel) {
		this.sa.setOnGoing(tableModel);
	}

	public void addAll() {
		this.sa.addFlights(this.airport.getFligths());	
	}

}
