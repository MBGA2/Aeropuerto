package SA;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import DAOs.inf_dao;
import Datos.Flight;

public class inf_SA {
	private inf_dao dao;
	private DefaultTableModel modelArrivals;
	private DefaultTableModel modelDepartures;
	public inf_SA() {
		this.dao = new inf_dao();
	}

	public void addAllFlights(List<Flight> list) {
		
		modelArrivals.setRowCount(0);
		modelDepartures.setRowCount(0);
		dao.buscarVuelosTableModel(modelDepartures,modelArrivals, list);
		modelArrivals.fireTableDataChanged();
		modelDepartures.fireTableDataChanged();
	}

	public void searchInArrivals(String search, List<Flight> list) {
		modelArrivals.setRowCount(0);
		dao.StringArrVuelosTableModel(modelArrivals, search, list);
		modelArrivals.fireTableDataChanged();
	}

	public void searchInDepartures(String search, List<Flight> list) {
		modelDepartures.setRowCount(0);
		dao.StringVuelosTableModel(modelDepartures, search, list);
		modelDepartures.fireTableDataChanged();
	}

	public DefaultTableModel getModelDepartures() {
		return modelDepartures;
	}

	public void setModelDepartures(DefaultTableModel modelDepartures) {
		this.modelDepartures = modelDepartures;
	}

	public DefaultTableModel getModelArrivals() {
		return modelArrivals;
	}

	public void setModelArrivals(DefaultTableModel modelArrivals) {
		this.modelArrivals = modelArrivals;
	}

}
