package Controladores;

import java.sql.Timestamp;

import DAOs.atm_dao;
import Main.Aeropuerto;
import Observer.Observer;
import Utils.NTYPE;
import Utils.NotifyData;
import Datos.Flight;

public class atm_controller {
	private Aeropuerto airport;
	private atm_dao dao;
	
	public atm_controller(Aeropuerto airport) {
		this.airport = airport;
		this.setDao(new atm_dao(this.airport));
	}

	public atm_dao getDao() {
		return dao;
	}

	public void setDao(atm_dao dao) {
		this.dao = dao;
	}
/*
	public void addFlight(Flight flight) {
		for (Path aux: atm.getPaths()) {
			if (aux.getDestination().equalsIgnoreCase(flight.getDestination())){
				flight.setPath(aux);
				if (!this.airport.getTime().before(flight.getDeparture_time()))
					flight.setPlane_state("On_Going");
				flight.setCurrentSpace(flight.getPath().getStopover().split(",")[0]);
				flight.setArrival_time(new Date(flight.getDeparture_time().getTime() + flight.getPath().getDuration()));
				break;
			}
		}
		this.airport.getOn_going().add(flight);
	}*/
	
	public void planeCrash(Flight f) {
		boolean flag = false;
		if (!f.getPlane_state().equalsIgnoreCase("Crashed") && !f.getPlane_state().equalsIgnoreCase("Waiting")){
			f.setPlane_state("Crashed");
			flag = true;
		}
		if (flag) {
			//Enviar Sar
			warnSAR();
			
		}else 
			System.out.println("No se encuentra ese avion en vuelo");
		
	}
	
	public void planeDamaged(Flight f) { 
		boolean flag = false;
		if (!f.getPlane_state().equalsIgnoreCase("Crashed") && !f.getPlane_state().equalsIgnoreCase("Waiting")){
			f.setPlane_state("Damaged");	
			//f.setState(FlightState.Delayed);
			flag = true;
			delayFlights(f);
		}
		if (flag) {
			// Random a ver si se estrella
			// Notify Delay
			// Mirar si hay mas Delays
			
			;
		}else 
			System.out.println("El avion ya esta estrellado fiera");
		
	}
	
	public void delayFlights(Flight f) {
		/*
		long delay = calcularDelay();
		Collections.sort(this.airport.getOn_going(), new SortByDate());
		for (Flight aux : this.airport.getOn_going()) {
			//if (aux.getPath().equals(f.getPath()) && f.getArrival_time().getTime() <= aux.getArrival_time().getTime()) {
			if (aux.getPath().equals(f.getPath()) && (f.getArrival_time().before(aux.getArrival_time()) || f.getArrival_time().equals(aux.getArrival_time()))) {
				if((int) ((Math.random()*10+1)) == 10)
					this.airport.notifyAllO(new NotifyData(NTYPE.ATM_DELAY, new Delay(aux,delay+(long) ((int) ((Math.random()*10+1))*60*1000))));
				else
					this.airport.notifyAllO(new NotifyData(NTYPE.ATM_DELAY, new Delay(aux,delay)));
				if (f.getGoing())
					this.airport.notifyAllO(new NotifyData(NTYPE.INF_CRASH, aux));
			}
		}*/
	}
	
	@SuppressWarnings("unused")
	private long calcularDelay () {	
		return (long) ((int) ((Math.random()*51+10)*1000*60));
	}
	
	public void planeDelay(long d, Flight f) {
		if(!f.getPlane_state().equalsIgnoreCase("Crashed")) {
			if (!f.getPlane_state().equalsIgnoreCase("Damaged")) {
				f.setPlane_state("Delayed");
				f.setRetarded_value(5);}
			f.setBoarding_time(new Timestamp(d));
			this.airport.notifyAllO(new NotifyData(NTYPE.TOR_DELAY, f,d));
		} else {
			System.out.println("El avion esta estrellado");
		}
	}
	
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

}
