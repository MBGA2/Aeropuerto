package SA;

import java.sql.Timestamp;
import java.util.List;
import Datos.map.Mapm;

import javax.swing.table.DefaultTableModel;

import DAOs.atm_dao;
import Datos.Flight;
import Datos.Tuple;

public class atm_SA{
	private atm_dao dao;
	private DefaultTableModel onGoing;
	private DefaultTableModel sar;
	private int delay;
	public atm_SA() {
	}
	
	public void fillSar(List<Flight> sar) {
		this.sar.setRowCount(0);
		this.dao.tableSarFill(this.sar,sar);
		this.sar.fireTableDataChanged();
	}
	public boolean planeNotCrashed(String text, List<Flight> fligths) {
		for(Flight f : fligths) 
			if (f.getID().equalsIgnoreCase(text))
				return true;
		return false;
	}
	public Flight planeCrashed(String a, List<Flight> fligths) {
		Flight fli = new Flight();
		this.onGoing.setRowCount(0);
		for(Flight f : fligths) 
			if (f.getID().equalsIgnoreCase(a)) {
				this.dao.planeCrash(f);
				fli = f;
			}
		this.onGoing.fireTableDataChanged();
		return fli;
	}
	public Flight planeDelayed(String text, List<Flight> fligths) {
		Flight fli = null;
		this.onGoing.setRowCount(0);
		this.setDelay(this.calcularDelay());
		for(Flight f : fligths) 
			if (f.getID().equalsIgnoreCase(text)) {
				this.dao.planeDelay(this.delay,f);
				fli = f;
				break;
			}
		this.onGoing.fireTableDataChanged();
		return fli;
	}
	public void addFlights(List<Flight> fligths) {
		this.onGoing.setRowCount(0);
		this.dao.tableFill(this.onGoing,fligths);
		this.onGoing.fireTableDataChanged();
	}
	private int calcularDelay () {	
		return (int) (Math.random()*5+5);
	}
	public void infoPlane(Timestamp t, List<Flight> flights, String f) {
		String s = "";
		Flight faux = null;
		for (Flight aux : flights) 
			if (aux.getID().equalsIgnoreCase(f)) {
				faux = aux;
				break;
			}
		s = "Avion con ID: " + faux.getID() + " viaja destino a " + faux.getDestination() + ".\n"; 
		
		if (faux.getPlane_state().equalsIgnoreCase("Crashed")) {
			s = s + "Estado de la aeronave: El avion ha sufrido un accidente.\n";
		}else {
			s = s + "Le faltan " + new Timestamp(faux.getArrival_time().getTime() - t.getTime()).toString() + " horas para alcanzar su destino.\n"
					+ "Estado de la aeronave: El avion opera con normalidad.\n";
		}
		if (faux.getPlane_state().equalsIgnoreCase("Delayed")) {
			s = s + "El avion ha sufrido un retraso de " + faux.getRetarded_value() + " minutos.";
		}else {
			s = s + "El avion no ha sufrido ningun retraso.";
		}
		this.dao.showPopUp(s);
	}
	public void infoPath(Mapm map, Timestamp t, List<Flight> flights, String f) {
		String s = "", saux = "", paux = "";
		Flight faux = null;
		boolean flag = true;
		for (Flight aux : flights) 
			if (aux.getID().equalsIgnoreCase(f)) {
				faux = aux;
				break;
			}
		s = "Avion con ID: " + faux.getID() + " que viaja destino a " + faux.getDestination() + ".\n";
		paux = "El avion sigue la ruta [";
		
		for (Tuple<Integer,Integer,Timestamp> tupla : map.getflightPathList().get(faux)) {
			paux = paux + "(" + Integer.toString(tupla.x) + "," + Integer.toString(tupla.y) + "), ";
			if (t.after(tupla.z) && flag) {
				saux = "Este se encuentra en las coordenadas (" + Integer.toString(tupla.x) + "," + Integer.toString(tupla.y) + ").\n";
				flag = false;
			}
		}
		paux = paux.substring(0, paux.length() - 2) + "].\n";
		s = s + paux + saux;
		this.dao.showPopUp(s);
	}
	public int getDelay() {
		return delay;
	}
	public void setDelay(int delay) {
		this.delay = delay;
	}
	public DefaultTableModel getSar() {
		return sar;
	}
	public void setSar(DefaultTableModel sar) {
		this.sar = sar;
	}
	public DefaultTableModel getOnGoing() {
		return onGoing;
	}
	public void setOnGoing(DefaultTableModel onGoing) {
		this.onGoing = onGoing;
	}

	public void setDao(atm_dao concreteDAO) {
		this.dao = concreteDAO;
	}
	
}
