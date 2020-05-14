package SA;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import DAOs.atm_dao;
import Datos.Flight;

public class atm_SA {
	private atm_dao dao;
	private DefaultTableModel onGoing;
	public atm_SA() {
		this.dao = new atm_dao();
	}
	public DefaultTableModel getOnGoing() {
		return onGoing;
	}
	public void setOnGoing(DefaultTableModel onGoing) {
		this.onGoing = onGoing;
	}
	public Flight planeCrashed(String a, List<Flight> fligths) {
		Flight fli = null;
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
		int d = calcularDelay();
		for(Flight f : fligths) 
			if (f.getID().equalsIgnoreCase(text)) {
				this.dao.planeDelay(d,f);
				fli = f;
			}
		this.onGoing.fireTableDataChanged();
		return fli;
	}
	public void addFlights(List<Flight> fligths) {
		this.onGoing.setRowCount(0);
		this.dao.tableFill(this.onGoing,fligths);
		this.onGoing.fireTableDataChanged();
	}
//	private void delayFlights(Flight f, int d, List<Flight> flights) {
//		for(Flight aux : flights) 
//			//Si a lo largo de d tiempo van a estar en la misma casilla -> Retrasao
//			if (f. == aux.getPath() && !f.getID().equalsIgnoreCase(aux.getID()))
//				this.planeDelayed(aux.getID(),flights);
//		
//		
//		/*
//		long delay = calcularDelay();
//		Collections.sort(this.airport.getOn_going(), new SortByDate());
//		for (Flight aux : this.airport.getOn_going()) {
//			//if (aux.getPath().equals(f.getPath()) && f.getArrival_time().getTime() <= aux.getArrival_time().getTime()) {
//			if (aux.getPath().equals(f.getPath()) && (f.getArrival_time().before(aux.getArrival_time()) || f.getArrival_time().equals(aux.getArrival_time()))) {
//				if((int) ((Math.random()*10+1)) == 10)
//					this.airport.notifyAllO(new NotifyData(NTYPE.ATM_DELAY, new Delay(aux,delay+(long) ((int) ((Math.random()*10+1))*60*1000))));
//				else
//					this.airport.notifyAllO(new NotifyData(NTYPE.ATM_DELAY, new Delay(aux,delay)));
//				if (f.getGoing())
//					this.airport.notifyAllO(new NotifyData(NTYPE.INF_CRASH, aux));
//			}
//		}*/
//	}
	private int calcularDelay () {	
		return (int) (Math.random()*5+5);
	}
	
}
