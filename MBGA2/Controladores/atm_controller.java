package Controladores;

import javax.swing.table.DefaultTableModel;
import Main.Aeropuerto;
import Observer.Observer;
import SA.atm_SA;
import Utils.NTYPE;
import Utils.NotifyData;
import Datos.Flight;

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

	public void crashPlane(String text) {
		Flight f = this.sa.planeCrashed(text,this.airport.getFligths());
		this.airport.notifyAllO(new NotifyData(NTYPE.ATM_CRASH, f));
	}

	public void delayPlane(String text) {
		Flight f = this.sa.planeDelayed(text,this.airport.getFligths());
		this.airport.notifyAllO(new NotifyData(NTYPE.ATM_DAMAGED, f, null));
	}

	public void addModels(DefaultTableModel tableModel) {
		this.sa.setOnGoing(tableModel);
	}

	public void addAll() {
		this.sa.addFlights(this.airport.getFligths());	
	}

}
