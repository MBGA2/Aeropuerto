package Controladores;

import Main.Aeropuerto;
import Observer.Observer;
import SA.seg_SA;
import SA.seg_SA_imp;
import Transfer.seg.TCam;
import Utils.*;

public class seg_controller {

	public static final String nombreArch = "listaCamaras.txt";// rev
	private Aeropuerto data;
	private seg_SA sa;

	public seg_controller(Aeropuerto aero) {
		data = aero;
		sa = new seg_SA_imp(aero);
	}

	public void actAlrm() {
		data.notifyAllO(new NotifyData(NTYPE.ALRM_CLOSE, null));
	}

	public void init() {
		((seg_SA_imp) sa).init(nombreArch);
	}

	public boolean loadData() {
		return sa.loadData(nombreArch);
	}

	public void addModelObserver(Observer o) {
		data.addObserver(o);
	}

	public TCam searchCameraById(TCam t) {
		return sa.searchCamByID(t);
	}

	public boolean addCamToRepairs(TCam tc) {
		return sa.addCamToRepairs(tc);
	}

	public void notifyRefresh() {
		sa.notifyRefresh();
	}

}
