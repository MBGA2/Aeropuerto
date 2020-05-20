package DAOs;



import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import Datos.Flight;

public class atm_dao {

	private Date current;
	
	static int TCASILLA = 30;

	public atm_dao() {
	}
	
	public void planeCrash(Flight f) {
		if (!f.getPlane_state().equalsIgnoreCase("Crashed")){
			f.setPlane_state("Crashed");
			
			//Enviar Sar
//			warnSAR();
			
		}
		
	}
	public void planeDelay(int d, Flight f) {
		if(!f.getPlane_state().equalsIgnoreCase("Crashed")) {
			f.setPlane_state("Delayed");
			f.setRetarded_value(d);
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

	
}
