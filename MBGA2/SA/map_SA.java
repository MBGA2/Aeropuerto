package SA;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAOs.inf_dao;
import DAOs.map_dao;
import Datos.Flight;

public class map_SA {
	private map_dao dao;
	private JTable f;
	public map_SA() {
		this.dao = new map_dao();
	}
	public void fill() {
		this.dao.fill(f);
	}
	public void setF(JTable table) {
		this.f = table;
	}
}
