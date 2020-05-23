package SA;

import java.sql.Timestamp;

import javax.swing.table.DefaultTableModel;

import DAOs.map_dao;
import Datos.Flight;
import Datos.map.Mapm;
import Datos.map.NumberFlights;

public class map_SA {
	private map_dao dao;
	private DefaultTableModel f;

	public map_SA() {
		this.dao = new map_dao();
	}

	public void fill(Mapm m, Timestamp timestamp) {
		f.setRowCount(0);
		this.dao.fill(f, m, timestamp);
		f.fireTableDataChanged();
	}

	public void setF(DefaultTableModel tableModel) {
		this.f = tableModel;
	}

	public void infoC(int col, int row, Mapm m, Timestamp ini) {
		String s = "";
		NumberFlights n = m.getInfoMap().get(row + "," + col);
		for (int i = 0; i < n.getInfoFlights().size(); i++) {
			if (n.getInfoFlights().get(i).getInitialD().before(ini)
					&& n.getInfoFlights().get(i).getLastD().after(ini)) {
				Flight f = n.getInfoFlights().get(i).getFlight();
				s = s + "Origen: " + f.getSource() + "(" + parseDate(f.getDeparture_time()) + ")\n" + "Destino: "
						+ f.getDestination() + "(" + parseDate(f.getArrival_time()) + ")\n" + "Estado: "
						+ f.getPlane_state() + "\n" + "--------------" + "\n";
			}
		}
		if (s != "") {
			this.dao.showPopUp(s);
		}

	}

	@SuppressWarnings("deprecation")
	private String parseDate(Timestamp date) {
		if (date == null) {
			return "";
		}
		String h = Integer.toString(date.getHours());
		String m = Integer.toString(date.getMinutes());
		if (date.getHours() < 10) {
			h = "0" + h;
		}
		if (date.getMinutes() < 10) {
			m = "0" + m;
		}

		return h + ":" + m;
	}
}
