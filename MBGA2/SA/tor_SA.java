package SA;

import javax.swing.table.DefaultTableModel;

import DAOs.tor_dao;
import Main.Aeropuerto;

public class tor_SA {

	private tor_dao dao;
	
	public tor_SA(Aeropuerto aeropuerto) {
		dao = new tor_dao(aeropuerto);
	}
	
	public void addGates(DefaultTableModel tableModel) {
		dao.addGates();
		dao.showdelayFlights(tableModel);
	}
	
}
