package SA;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import DAOs.inf_dao;
import Datos.Flight;

public class inf_SA{
	private inf_dao dao;
	private DefaultTableModel modelArrivals;
	private DefaultTableModel modelDepartures;
	private Boolean flag;
	public inf_SA() {
		this.setFlag(true);
	}

	public void addAllFlights(List<Flight> list) {
		if (flag) {
		modelArrivals.setRowCount(0);
		modelDepartures.setRowCount(0);
		dao.buscarVuelosTableModel(modelDepartures,modelArrivals, list);
		modelArrivals.fireTableDataChanged();
		modelDepartures.fireTableDataChanged();
		}
	}

	public void searchInArrivals(String search, List<Flight> list) {
		modelArrivals.setRowCount(0);
		dao.StringArrVuelosTableModel(modelArrivals, search, list);
		modelArrivals.fireTableDataChanged();
		this.flag = false;
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

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public void setDao(inf_dao concreteDAO) {
		this.dao = concreteDAO;
	}

}
