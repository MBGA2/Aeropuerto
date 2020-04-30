package Controladores;


import DAOs.inf_dao;
import Main.Aeropuerto;
import Observer.Observer;

public class inf_controller {
	private Aeropuerto aero;
	private inf_dao dao;
	public static final long HOUR = 3600 * 1000;
	public static final long MIN = 60 * 1000;

	public inf_controller(Aeropuerto aero) {
		this.aero = aero;
		this.setDao(new inf_dao(this.aero));

	}
	//estoy probando github
	//prueba2

	public Aeropuerto getAero() {
		return aero;
	}

	public void setAero(Aeropuerto aero) {
		this.aero = aero;
	}


	public inf_dao getDao() {
		return dao;
	}

	public void setDao(inf_dao dao) {
		this.dao = dao;
	}

	public void addModelObserver(Observer o) {
		aero.addObserver(o);
		for (int i = 0; i < this.aero.getFligths().size(); i++) {
			aero.getFligths().get(i).addObserver(o);
		}
	}

	public void addAll() {
		aero.notifyAllO(new NotifyData(NTYPE.INF_ADD, this.aero.getFligths()));
	}

	public void search() {
		aero.notifyAllO(new NotifyData(NTYPE.INF_S_DEP, this.aero.getFligths()));
		aero.notifyAllO(new NotifyData(NTYPE.INF_S_ARR, this.aero.getFligths()));
	}


	public void addNewF(boolean b) {
		
	}
}
