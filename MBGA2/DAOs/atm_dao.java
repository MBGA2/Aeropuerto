package DAOs;


import Main.Aeropuerto;
import Utils.NTYPE;
import Utils.NotifyData;
import Utils.atm.GeneratePath;
import Utils.atm.InfoCity;
import Utils.atm.Path;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import Datos.Flight;

public class atm_dao {

	private Date current;
	private GeneratePath path;
	
	static int TCASILLA = 30;

	public atm_dao() {
		this.path = new GeneratePath();
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
	public Path calculatePath(String source, String dest, Timestamp ini) {
//		InfoCity a = null,b = null,c = null, d = null;
//		String stopovers = "";
//		for(InfoCity aux : this.path.getCities()) 
//			if (flight.getDestination().equalsIgnoreCase(aux.getName()))
//				a = aux;
//			else if (flight.getSource().equalsIgnoreCase(aux.getName()))
//				b = aux;
//		
//		if (this.path.getDirect().get(flight.getSource()).contains(flight.getDestination())) { //Vuelo Directo
//			flight.setPath(new Path(flight.getDestination(), "Direct", 
//					this.calculateTimeFlight(
//							Math.abs(a.getPosX()-b.getPosX()+a.getPosY()-b.getPosY())), b.getPosX(), b.getPosY()));
//		} else if (Math.abs(a.getPosX()-b.getPosX()+a.getPosY()-b.getPosY()) <= 10){ // 1 Escala
//			for(InfoCity aux : this.path.getCities()) 
//				if (this.path.getDirect().get(flight.getSource()).contains(aux.getName()) 
//						&& this.path.getDirect().get(aux.getName()).contains(flight.getDestination()))
//					c = aux;
//			flight.setPath(new Path(flight.getDestination(), c.getName(), 
//					this.calculateTimeFlight(
//							Math.abs(a.getPosX()-b.getPosX()+a.getPosY()-b.getPosY()-c.getPosX()-c.getPosY())), b.getPosX(), b.getPosY()));
//		} else { // 2+ Escala
//			int dist = 0;
//			c = new InfoCity("",0,0);
//			for(InfoCity aux : this.path.getCities()) 
//				if (this.path.getDirect().get(flight.getSource()).contains(aux.getName()) && aux.getPosX() > dist) {
//					dist = aux.getPosX();
//					c = aux;
//				}
//			d = c;
//			while (!this.path.getDirect().get(d.getName()).contains(flight.getDestination())) {
//				dist = 0;
//				for(InfoCity aux : this.path.getCities()) 
//					if (this.path.getDirect().get(c.getName()).contains(aux.getName()) && aux.getPosX() > dist) {
//						dist = aux.getPosX();
//						d = aux;
//					}
//				c.setName(c.getName()+","+d.getName());
//				c.setPosX(c.getPosX()+d.getPosX());
//				c.setPosY(c.getPosY()+d.getPosY());
//			}
//			flight.setPath(new Path(flight.getDestination(), c.getName(), 
//					this.calculateTimeFlight(
//							Math.abs(a.getPosX()-b.getPosX()+a.getPosY()-b.getPosY()-c.getPosX()-c.getPosY())), b.getPosX(), b.getPosY()));
//		} 
		
		//Saber a que ciudades va a llegar (Las escalas)(8 Casillas tope)
		//Calcular el camino en el mapa
		
		//En otra funcion ir pasando el tiempo para que los aviones se muevan, y animacion aviones
		// Directo 8, 1 Escala al resto (Mas Drch, si oc, next) Excepto Reykiavic (Mas arriba)
		//xS,yS -> Pos Source in map
		//stop -> Escala o Direct
		//n -> nº casillas
		String stop = "none";
		int n = 0, xS = 0, yS = 0;
		
		InfoCity destInfo = null;
		
		for(InfoCity cityDest : this.path.getCities())
			if (cityDest.getName().equalsIgnoreCase(dest)) 
				destInfo = cityDest;
		for(InfoCity citySource : this.path.getCities()) 
			if (source.equalsIgnoreCase(citySource.getName())){
				xS = citySource.getPosX();
				yS = citySource.getPosY();
				if (this.path.getDirect().get(source).contains(dest)) {
					stop = "Direct";
					n = Math.abs(citySource.getPosX()+citySource.getPosY() - (destInfo.getPosX()+destInfo.getPosY()));
				} 
				else if (!dest.equalsIgnoreCase("Reikiavik")) {
					int dist = 0;
					for(InfoCity cityStop : this.path.getCities()) {
						if (this.path.getDirect().get(source).contains(cityStop.getName()) && (Math.abs(cityStop.getPosX())) > dist) {
							dist = cityStop.getPosX();
							stop = cityStop.getName();
							n = Math.abs(citySource.getPosX()+citySource.getPosY() - (cityStop.getPosX()+cityStop.getPosY())) + Math.abs(cityStop.getPosX()+cityStop.getPosY() - (destInfo.getPosX()+destInfo.getPosY()));
						}
					}
				}else {
					int dist = 0;
					for(InfoCity cityStop : this.path.getCities()) {
						if (this.path.getDirect().get(source).contains(cityStop.getName()) && (Math.abs(cityStop.getPosY())) > dist) {
							dist = cityStop.getPosY();
							stop = cityStop.getName();
							n = Math.abs(citySource.getPosX()+citySource.getPosY() - (cityStop.getPosX()+cityStop.getPosY())) + Math.abs(cityStop.getPosX()+cityStop.getPosY() - (destInfo.getPosX()+destInfo.getPosY()));
						}
					}
				}
			}
		return new Path(dest,stop,TCASILLA*n,xS,yS);
	}
	
	public void fillMap(Flight f) {
		InfoCity destInfo = null, sourceInfo = null, stopInfo = null;
		
		for(InfoCity aux : this.path.getCities())
			if (aux.getName().equalsIgnoreCase(f.getDestination())) 
				destInfo = aux;
			else if (aux.getName().equalsIgnoreCase(f.getSource()))
				sourceInfo = aux;
			else if (aux.getName().equalsIgnoreCase(f.getPath().getStopover()))
				stopInfo = aux;
		
		int xSource = sourceInfo.getPosX(), ySource = sourceInfo.getPosY(), 
				xDest = destInfo.getPosX(), yDest = destInfo.getPosY(),
				ti = 30*60*1000;
		Timestamp t = f.getDeparture_time();
		
		
		if (stopInfo == null) {
			for (int i = 0, j = 0; i < Math.abs(sourceInfo.getPosX()+sourceInfo.getPosY() - (destInfo.getPosX()+destInfo.getPosY())); i++, j++) {
				this.airport.getMap().addFlightInMap(f, xSource, ySource, new Timestamp(t.getTime()+(ti*j)), new Timestamp(t.getTime() + (ti*(j+1))));
				if(!destInfo.getName().equalsIgnoreCase("Reikiavik")) {
					if(xSource+1 < xDest && this.airport.getMap().checkFlightsInMap(xSource+1, ySource, new Timestamp(t.getTime()+(ti*j))) < 3)
						xSource++;
					else if (ySource+1 < yDest && this.airport.getMap().checkFlightsInMap(xSource, ySource+1, new Timestamp(t.getTime()+(ti*j))) < 3)
						ySource++;
					else 
						i--;
				}
				else {
					if (ySource+1 < yDest && this.airport.getMap().checkFlightsInMap(xSource, ySource+1, new Timestamp(t.getTime()+(ti*j))) < 3)
						ySource++;
					else if (xSource+1 < xDest && this.airport.getMap().checkFlightsInMap(xSource+1, ySource, new Timestamp(t.getTime()+(ti*j))) < 3)
						xSource++;
					else 
						i--;
				}
			}
		}
		else if (stopInfo != null) {
			int xStop = stopInfo.getPosX(), yStop = stopInfo.getPosY(), j=0;
			for (int i = 0; i < Math.abs(sourceInfo.getPosX()+sourceInfo.getPosY() - (stopInfo.getPosX()+stopInfo.getPosY())); i++, j++) {
				this.airport.getMap().addFlightInMap(f, xSource, ySource, new Timestamp(t.getTime()+(ti*j)), new Timestamp(t.getTime() + (ti*(j+1))));
				if(!destInfo.getName().equalsIgnoreCase("Reikiavik")) {
					if(xSource+1 < xStop && this.airport.getMap().checkFlightsInMap(xSource+1, ySource, new Timestamp(t.getTime()+(ti*j))) < 3)
						xSource++;
					else if (ySource+1 < yStop && this.airport.getMap().checkFlightsInMap(xSource, ySource+1, new Timestamp(t.getTime()+(ti*j))) < 3)
						ySource++;
					else 
						i--;
				} else {
					if (ySource+1 < yDest && this.airport.getMap().checkFlightsInMap(xSource, ySource+1, new Timestamp(t.getTime()+(ti*j))) < 3)
						ySource++;
					else if (xSource+1 < xDest && this.airport.getMap().checkFlightsInMap(xSource+1, ySource, new Timestamp(t.getTime()+(ti*j))) < 3)
						xSource++;
					else 
						i--;
				}
			}
			for (int i = 0; i < Math.abs(stopInfo.getPosX()+stopInfo.getPosY() - (destInfo.getPosX()+destInfo.getPosY())); i++, j++) {
				this.airport.getMap().addFlightInMap(f, xSource, ySource, new Timestamp(t.getTime()+(ti*j)), new Timestamp(t.getTime() + (ti*(j+1))));
				if(!destInfo.getName().equalsIgnoreCase("Reikiavik")) {
					if(xSource+1 < xDest && this.airport.getMap().checkFlightsInMap(xSource+1, ySource, new Timestamp(t.getTime()+(ti*j))) < 3)
						xSource++;
					else if (ySource+1 < yDest && this.airport.getMap().checkFlightsInMap(xSource, ySource+1, new Timestamp(t.getTime()+(ti*j))) < 3)
						ySource++;
					else 
						i--;
				} else {
					if (ySource+1 < yDest && this.airport.getMap().checkFlightsInMap(xSource, ySource+1, new Timestamp(t.getTime()+(ti*j))) < 3)
						ySource++;
					else if (xSource+1 < xDest && this.airport.getMap().checkFlightsInMap(xSource+1, ySource, new Timestamp(t.getTime()+(ti*j))) < 3)
						xSource++;
					else 
						i--;
				}
			}
		}
		
		
		
	}
	/*Placeholder*/
//	public int calculateTimeFlight(int diff) {
//		return diff * 1000000;
//	}
	
}
